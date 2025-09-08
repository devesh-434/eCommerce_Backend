package com.example.orderprocessing.events;

import com.example.orderprocessing.model.Item;
import java.util.List;

public class OrderCreatedEvent extends Event {
    private String orderId;
    private String customerId;
    private List<Item> items;
    private double totalAmount;

    @Override public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    @Override public String getEventType() { return "OrderCreated"; }

    // Getters and Setters needed for JSON parsing
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}