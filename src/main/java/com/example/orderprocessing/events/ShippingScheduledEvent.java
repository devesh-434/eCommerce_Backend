package com.example.orderprocessing.events;

import java.time.LocalDate;

public class ShippingScheduledEvent extends Event {
    private String orderId;
    private LocalDate shippingDate;

    @Override public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    @Override public String getEventType() { return "ShippingScheduled"; }

    public LocalDate getShippingDate() { return shippingDate; }
    public void setShippingDate(LocalDate shippingDate) { this.shippingDate = shippingDate; }
}