package com.creaturelove.service;

public class DefaultGreetingService implements GreetingService {

    private final String prefix; // The configurable greeting prefix

    public DefaultGreetingService(String prefix) {
        // Handle null prefix gracefully, provide a default
        this.prefix = (prefix != null && !prefix.trim().isEmpty()) ? prefix : "Hello";
    }

    @Override
    public String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "World"; // Default name if none provided
        }
        return String.format("%s, %s!", this.prefix, name);
    }
}
