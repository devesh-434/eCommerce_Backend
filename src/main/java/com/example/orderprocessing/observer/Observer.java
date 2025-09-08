package com.example.orderprocessing.observer;

import com.example.orderprocessing.events.Event;
import com.example.orderprocessing.model.Order;

public interface Observer {
    void update(Event event, Order order);
}