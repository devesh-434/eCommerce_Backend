package com.example.orderprocessing.observer;

import com.example.orderprocessing.events.Event;
import com.example.orderprocessing.model.Order;

public class AlertObserver implements Observer {
    @Override
    public void update(Event event, Order order) {
        // We can define which statuses are "critical"
        switch (order.getStatus()) {
            case SHIPPED:
            case CANCELLED:
            case PAID:
                System.err.println("[ALERT] Critical update for Order " + order.getOrderId() +
                        ": Status changed to " + order.getStatus());
                break;
            default:
                // Do nothing for non-critical changes
                break;
        }
    }
}