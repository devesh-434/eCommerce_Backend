package com.example.orderprocessing.events;

public class OrderCancelledEvent extends Event {
    private String orderId;
    private String reason;

    @Override public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    @Override public String getEventType() { return "OrderCancelled"; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}