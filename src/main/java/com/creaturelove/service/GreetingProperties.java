package com.creaturelove.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "greeting.service") // Binds properties starting with "greeting.service"
public class GreetingProperties {

    /**
     * The prefix to use in the greeting message. Defaults to "Hello".
     * Corresponds to the property: greeting.service.prefix
     */
    private String prefix = "Hello"; // Default value

    // Standard Getter and Setter are required for binding
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}