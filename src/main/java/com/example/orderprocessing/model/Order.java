package com.example.orderprocessing.model;

import com.example.orderprocessing.events.Event;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private List<Item> items;
    private double totalAmount;
    private OrderStatus status;
    private List<Event> eventHistory = new ArrayList<>();

    public Order(String orderId, String customerId, List<Item> items, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PENDING; // Initial status
    }

    public void addEventToHistory(Event event) {
        this.eventHistory.add(event);
    }
    
    // Getters and Setters
    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public double getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                ", eventHistorySize=" + eventHistory.size() +
                '}';
    }
}