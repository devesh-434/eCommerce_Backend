package com.example.orderprocessing.processing;

import com.example.orderprocessing.model.Order;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderRepository {
    private final Map<String, Order> orderStore = new HashMap<>();

    public void save(Order order) {
        orderStore.put(order.getOrderId(), order);
    }

    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orderStore.get(orderId));
    }
}