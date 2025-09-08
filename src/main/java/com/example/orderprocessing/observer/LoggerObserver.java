package com.example.orderprocessing.observer;

import com.example.orderprocessing.events.Event;
import com.example.orderprocessing.model.Order;

public class LoggerObserver implements Observer {
    @Override
    public void update(Event event, Order order) {
        System.out.println("[LOG] Event processed: " + event.getEventType() +
                " (ID: " + event.getEventId() +
                ") for Order ID: " + order.getOrderId() +
                ". New status: " + order.getStatus());
    }
}