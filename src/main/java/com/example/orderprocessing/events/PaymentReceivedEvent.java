package com.example.orderprocessing.events;

public class PaymentReceivedEvent extends Event {
    private String orderId;
    private double amountPaid;

    @Override public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    @Override public String getEventType() { return "PaymentReceived"; }

    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
}