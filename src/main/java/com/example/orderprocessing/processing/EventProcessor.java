package com.example.orderprocessing.processing;

import com.example.orderprocessing.events.*;
import com.example.orderprocessing.model.Order;
import com.example.orderprocessing.model.OrderStatus;
import com.example.orderprocessing.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class EventProcessor {
    private final OrderRepository orderRepository;
    private final List<Observer> observers = new ArrayList<>();

    public EventProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void process(Event event) {
        if (event == null) return; // Skip null events from parsing errors

        if (event instanceof OrderCreatedEvent) {
            handleOrderCreated((OrderCreatedEvent) event);
        } else if (event instanceof PaymentReceivedEvent) {
            handlePaymentReceived((PaymentReceivedEvent) event);
        } else if (event instanceof ShippingScheduledEvent) {
            handleShippingScheduled((ShippingScheduledEvent) event);
        } else if (event instanceof OrderCancelledEvent) {
            handleOrderCancelled((OrderCancelledEvent) event);
        } else {
            System.out.println("Warning: Unknown event type received: " + event.getEventType());
        }
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        Order order = new Order(
            event.getOrderId(),
            event.getCustomerId(),
            event.getItems(),
            event.getTotalAmount()
        );
        updateAndNotify(order, event);
    }

    private void handlePaymentReceived(PaymentReceivedEvent event) {
        orderRepository.findById(event.getOrderId()).ifPresentOrElse(order -> {
            if (event.getAmountPaid() >= order.getTotalAmount()) {
                order.setStatus(OrderStatus.PAID);
            } else if (event.getAmountPaid() > 0) {
                order.setStatus(OrderStatus.PARTIALLY_PAID);
            }
            updateAndNotify(order, event);
        }, () -> logOrderNotFound(event));
    }

    private void handleShippingScheduled(ShippingScheduledEvent event) {
        orderRepository.findById(event.getOrderId()).ifPresentOrElse(order -> {
            if (order.getStatus() == OrderStatus.PAID || order.getStatus() == OrderStatus.PARTIALLY_PAID) {
                order.setStatus(OrderStatus.SHIPPED);
                updateAndNotify(order, event);
            } else {
                System.out.println("Warning: Cannot ship order " + order.getOrderId() + " because it is not paid.");
            }
        }, () -> logOrderNotFound(event));
    }

    private void handleOrderCancelled(OrderCancelledEvent event) {
        orderRepository.findById(event.getOrderId()).ifPresentOrElse(order -> {
            order.setStatus(OrderStatus.CANCELLED);
            updateAndNotify(order, event);
        }, () -> logOrderNotFound(event));
    }

    private void updateAndNotify(Order order, Event event) {
        order.addEventToHistory(event);
        orderRepository.save(order);
        notifyObservers(event, order);
    }
    
    private void notifyObservers(Event event, Order order) {
        for (Observer observer : observers) {
            observer.update(event, order);
        }
    }

    private void logOrderNotFound(Event event) {
        System.err.println("Error: Order with ID " + event.getOrderId() + " not found for event " + event.getEventType());
    }
}