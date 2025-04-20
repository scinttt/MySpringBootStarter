package com.creaturelove;

import com.creaturelove.service.DefaultGreetingService;
import com.creaturelove.service.GreetingProperties;
import com.creaturelove.service.GreetingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(GreetingService.class) // Ensures GreetingService is on the classpath
@EnableConfigurationProperties(GreetingProperties.class) // Enables binding of GreetingProperties
public class GreetingAutoConfiguration {
    private final GreetingProperties properties;

    public GreetingAutoConfiguration(GreetingProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean // Only create bean if no other GreetingService is defined
    public GreetingService greetingService() {
        return new DefaultGreetingService(properties.getPrefix()); // Use the prefix from properties
    }
}