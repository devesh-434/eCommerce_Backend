package com.example.orderprocessing.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "eventType"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = OrderCreatedEvent.class, name = "OrderCreated"),
    @JsonSubTypes.Type(value = PaymentReceivedEvent.class, name = "PaymentReceived"),
    @JsonSubTypes.Type(value = ShippingScheduledEvent.class, name = "ShippingScheduled"),
    @JsonSubTypes.Type(value = OrderCancelledEvent.class, name = "OrderCancelled")
})
public abstract class Event {
    private String eventId;
    private Instant timestamp;

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public abstract String getOrderId();
    public abstract String getEventType();
}