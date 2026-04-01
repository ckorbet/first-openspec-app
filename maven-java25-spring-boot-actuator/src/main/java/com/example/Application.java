package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Spring Boot Java 25 with Actuator.
 *
 * This application provides:
 * - Health checks via /actuator/health
 * - Metrics via /actuator/metrics
 * - Application info via /actuator/info
 * - Logging via SLF4J with Logback
 * - Hot reload support via Spring Boot DevTools in development
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
