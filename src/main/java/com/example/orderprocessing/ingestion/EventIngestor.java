package com.example.orderprocessing.ingestion;

import com.example.orderprocessing.events.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventIngestor {
    private final ObjectMapper objectMapper;

    public EventIngestor() {
        this.objectMapper = new ObjectMapper();
        // Register module to handle Java 8 date/time types like Instant and LocalDate
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Event> ingestEvents(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(this::parseLine).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Failed to read event file: " + e.getMessage());
            return List.of(); // Return empty list on failure
        }
    }

    private Event parseLine(String jsonLine) {
        try {
            return objectMapper.readValue(jsonLine, Event.class);
        } catch (IOException e) {
            System.err.println("Warning: Could not parse event line: " + jsonLine + ". Error: " + e.getMessage());
            return null; // Gracefully handle parsing errors
        }
    }
}