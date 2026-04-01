# Spring Boot Java 21 with Actuator

A Maven-based Spring Boot 3.3.1 application demonstrating minimal configuration with Java 21, Spring Boot Actuator for monitoring, SLF4J logging with Logback, and Spring Boot DevTools for development support.

## Overview

This project provides a production-ready baseline for microservices and REST API development with:
- **Java 21 LTS**: Modern Java version with long-term support
- **Spring Boot 3.3.1**: Production-ready defaults and simplified configuration
- **Spring Boot Actuator**: HTTP endpoints for health checks, metrics, and application information
- **SLF4J + Logback**: Industry-standard logging with sensible defaults
- **Spring Boot DevTools**: Automatic restart and live reload during development
- **Maven**: Build automation and dependency management

## Prerequisites

- **Java 21** or later (LTS version recommended)
- **Maven 3.8.1** or later
- **curl** or any HTTP client for testing endpoints

## Project Structure

```
maven-java25-spring-boot-actuator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       └── Application.java       # Main Spring Boot application class
│   │   └── resources/
│   │       └── application.properties     # Application configuration
│   └── test/
│       └── java/
│           └── (test sources would go here)
├── pom.xml                               # Maven build configuration
└── target/
    └── spring-boot-java25-actuator-1.0.0.jar  # Executable JAR
```

## Building the Project

Build the project and create the executable JAR:

```bash
mvn clean package
```

This command will:
1. Clean previous build artifacts
2. Compile Java source code with Java 21
3. Run tests (skipped by default with `-DskipTests`)
4. Create an executable JAR with Spring Boot packaging

**Output**: `target/spring-boot-java25-actuator-1.0.0.jar`

## Running the Application

### Start the Application

```bash
java -jar target/spring-boot-java25-actuator-1.0.0.jar
```

### With DevTools (Development)

DevTools are included in the dependencies and enabled during development. The application will automatically restart when source files change (when running from IDE or with watch mode).

### With Custom Port

To run on a different port (default is 8080):

```bash
java -jar target/spring-boot-java25-actuator-1.0.0.jar --server.port=9090
```

## Actuator Endpoints

Spring Boot Actuator provides monitoring and observability endpoints. All endpoints are exposed on `/actuator/*` paths.

### Health Endpoint

Check application health status:

```bash
curl http://localhost:8080/actuator/health
```

**Response**:
```json
{
  "status": "UP"
}
```

With detailed mode enabled, you can see component health:
```json
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP",
      "details": {...}
    },
    "livenessState": {
      "status": "UP"
    },
    "readinessState": {
      "status": "UP"
    }
  }
}
```

### Metrics Endpoint

List all available metrics:

```bash
curl http://localhost:8080/actuator/metrics
```

Get specific metric details:

```bash
curl http://localhost:8080/actuator/metrics/jvm.memory.used
```

**Common Metrics**:
- `jvm.memory.used`: JVM heap memory usage
- `jvm.threads.live`: Number of live threads
- `process.uptime`: Application uptime in seconds
- `http.server.requests`: HTTP request metrics

### Info Endpoint

Display application metadata:

```bash
curl http://localhost:8080/actuator/info
```

**Response**:
```json
{
  "app": {
    "name": "Spring Boot Java 21 with Actuator",
    "description": "A Maven + Java 21 + Spring Boot application with Actuator starter",
    "version": "1.0.0",
    "encoding": "UTF-8",
    "java": {
      "version": "21"
    }
  }
}
```

### Discovery Endpoint

List all available Actuator endpoints:

```bash
curl http://localhost:8080/actuator
```

## Logging

The application uses **SLF4J** as the logging abstraction with **Logback** as the implementation.

### Default Configuration

- **Pattern**: `YYYY-MM-DD HH:mm:ss - [Logger] - Message`
- **Level**: 
  - Root: `INFO`
  - Application (`com.example`): `DEBUG`
- **Output**: Console (stdout)

### Example Log Output

```
2026-04-01 12:07:22 - com.example.Application - Application started in 1.234 seconds
2026-04-01 12:07:22 - org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes - Loggers endpoint is available via HTTP.
```

### Customize Logging

Edit `src/main/resources/application.properties`:

```properties
# Set root logging level to WARN
logging.level.root=WARN

# Set application package to DEBUG
logging.level.com.example=DEBUG

# Custom log file output
logging.file.name=logs/application.log
```

For advanced logging configuration, create `src/main/resources/logback.xml`.

## Development with DevTools

**Spring Boot DevTools** enhances the development experience:

### Automatic Restart

When files change, DevTools automatically restarts the application:
```bash
java -jar target/spring-boot-java25-actuator-1.0.0.jar
```

### Using Maven Plugin

Run directly with automatic restart:
```bash
mvn spring-boot:run
```

### IDE Integration (IntelliJ IDEA / VS Code)

1. Enable "Build project automatically" in IDE settings
2. DevTools will detect changes and restart automatically

### Exclude DevTools from Production

DevTools are configured with `scope=runtime` and `optional=true`. The Spring Boot Maven plugin automatically excludes them from the production JAR.

Verify DevTools are excluded:
```bash
jar tf target/spring-boot-java25-actuator-1.0.0.jar | grep devtools
# (no output = successfully excluded)
```

## Dependency Management

### Spring Boot Parent POM

The project inherits from `spring-boot-starter-parent`, which provides:
- Dependency version management
- Default Maven configuration
- Plugin defaults and versions
- Java compiler settings

### Key Dependencies

```xml
<!-- Spring Boot Starter (core + logging) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>

<!-- Spring Boot Actuator (monitoring endpoints) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<!-- Spring Boot Web (HTTP endpoints) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Boot DevTools (development only) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

**SLF4J and Logback** are included transitively by `spring-boot-starter` (no explicit dependency needed).

## Extending the Project

### Add a REST Controller

Create `src/main/java/com/example/HelloController.java`:

```java
package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello from Spring Boot!");
    }
}
```

### Add a Service

Create `src/main/java/com/example/GreetingService.java`:

```java
package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    private static final Logger LOG = LoggerFactory.getLogger(GreetingService.class);
    
    public String greet(String name) {
        LOG.debug("Greeting request for user: {}", name);
        return "Hello, " + name + "!";
    }
}
```

### Enable Logging in Custom Code

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MyController {
    private static final Logger LOG = LoggerFactory.getLogger(MyController.class);
    
    @GetMapping("/api/data")
    public ResponseEntity<?> getData() {
        LOG.info("Data request received");
        LOG.debug("Processing request details...");
        return ResponseEntity.ok().build();
    }
}
```

## Configuration Profiles

Add environment-specific configuration files:

- `src/main/resources/application.properties` (default)
- `src/main/resources/application-dev.properties` (development)
- `src/main/resources/application-prod.properties` (production)

Run with a specific profile:

```bash
java -jar target/spring-boot-java25-actuator-1.0.0.jar --spring.profiles.active=dev
```

## Troubleshooting

### Port Already in Use

```bash
# Use a different port
java -jar target/spring-boot-java25-actuator-1.0.0.jar --server.port=9090
```

### Java Version Mismatch

Ensure Java 21 is on your PATH:

```bash
java -version
# openjdk version "21..." or later
```

### Application Won't Start

Check logs for errors:
- Spring Boot logs output to console by default
- Look for exception stack traces in the startup output
- Enable debug logging: `--debug` or `logging.level.root=DEBUG`

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Boot Actuator Guide](https://spring.io/guides/gs/actuator-service/)
- [Logback Documentation](http://logback.qos.ch/)
- [SLF4J Manual](http://slf4j.org/manual.html)
- [Spring Boot DevTools Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)

## License

This project is provided as-is for learning and reference purposes.
