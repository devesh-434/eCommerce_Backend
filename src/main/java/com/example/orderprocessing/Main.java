package com.example.orderprocessing;

import com.example.orderprocessing.events.Event;
import com.example.orderprocessing.ingestion.EventIngestor;
import com.example.orderprocessing.observer.AlertObserver;
import com.example.orderprocessing.observer.LoggerObserver;
import com.example.orderprocessing.processing.EventProcessor;
import com.example.orderprocessing.processing.OrderRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Order Processing System ---");

        // 1. Setup core components
        OrderRepository orderRepository = new OrderRepository();
        EventProcessor eventProcessor = new EventProcessor(orderRepository);
        EventIngestor eventIngestor = new EventIngestor();

        // 2. Setup observers and register them
        LoggerObserver logger = new LoggerObserver();
        AlertObserver alerter = new AlertObserver();
        eventProcessor.registerObserver(logger);
        eventProcessor.registerObserver(alerter);
        
        // 3. Ingest events from the source file
        System.out.println("\n--- Ingesting Events from events.json ---");
        List<Event> events = eventIngestor.ingestEvents("events.json");
        System.out.println("Found " + events.stream().filter(java.util.Objects::nonNull).count() + " valid events to process.");

        // 4. Process each event in sequence
        System.out.println("\n--- Processing Events ---");
        for (Event event : events) {
            eventProcessor.process(event);
        }

        // 5. Display the final state of the orders for verification
        System.out.println("\n--- Final Order States ---");
        orderRepository.findById("ORD001").ifPresent(System.out::println);
        orderRepository.findById("ORD002").ifPresent(System.out::println);

        System.out.println("\n--- System Finished ---");
    }
}