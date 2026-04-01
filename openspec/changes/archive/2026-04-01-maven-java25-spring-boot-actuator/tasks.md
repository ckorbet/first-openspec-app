## 1. Project Setup

- [x] 1.1 Create Maven project root directory
- [x] 1.2 Create pom.xml with Spring Boot parent POM and basic project metadata
- [x] 1.3 Create standard directory structure (src/main/java, src/main/resources, src/test/java, src/test/resources)
- [x] 1.4 Configure Java 25 as source and target in Maven compiler plugin
- [x] 1.5 Add Spring Boot Maven plugin for executable JAR packaging

## 2. Dependency Management

- [x] 2.1 Add spring-boot-starter dependency to pom.xml
- [x] 2.2 Add spring-boot-starter-actuator dependency to pom.xml
- [x] 2.3 Add spring-boot-devtools dependency with optional scope to pom.xml
- [x] 2.4 Verify SLF4J and Logback are available (included transitively by spring-boot-starter)
- [x] 2.5 Verify dependency resolution (mvn dependency:tree)
- [x] 2.6 Ensure no version conflicts in transitive dependencies
- [x] 2.7 Configure DevTools exclusion in spring-boot-maven-plugin (so it doesn't ship in production JAR)

## 3. Application Structure

- [x] 3.1 Create main application class at src/main/java/com/example/Application.java
- [x] 3.2 Annotate main class with @SpringBootApplication
- [x] 3.3 Implement main method to call SpringApplication.run()
- [x] 3.4 Create application.properties or application.yml in src/main/resources

## 4. Actuator Configuration

- [x] 4.1 Enable HTTP endpoints in application properties (management.endpoints.web.exposure.include=*)
- [x] 4.2 Configure health endpoint with detailed response mode
- [x] 4.3 Ensure /actuator/health endpoint returns application status
- [x] 4.4 Ensure /actuator/metrics endpoint exposes JVM and application metrics
- [x] 4.5 Configure /actuator/info endpoint with application metadata

## 5. Logging Configuration

- [x] 5.1 Verify SLF4J is configured as the logging facade
- [x] 5.2 Verify Logback is active as the SLF4J implementation
- [x] 5.3 Confirm logs output to console without custom logback.xml (use Spring Boot defaults)
- [x] 5.4 Test logging output during application startup
- [x] 5.5 Verify DevTools provides hot reload capability during development

## 6. Build and Verification

- [x] 6.1 Build project with Maven (mvn clean package)
- [x] 6.2 Verify executable JAR is created in target/ directory
- [x] 6.3 Start application with java -jar target/*.jar
- [x] 6.4 Verify application starts without errors (check logs)
- [x] 6.5 Test health endpoint: curl http://localhost:8080/actuator/health
- [x] 6.6 Test metrics endpoint: curl http://localhost:8080/actuator/metrics
- [x] 6.7 Test info endpoint: curl http://localhost:8080/actuator/info
- [x] 6.8 Verify Actuator discovery endpoint: curl http://localhost:8080/actuator

## 7. Documentation

- [x] 7.1 Create README.md with project description and purpose
- [x] 7.2 Document how to build the project (mvn commands)
- [x] 7.3 Document how to run the application
- [x] 7.4 Document available Actuator endpoints and their usage
- [x] 7.5 Add notes on DevTools usage and hot reload capabilities
- [x] 7.6 Document logging behavior and how to customize if needed
- [x] 7.7 Add notes on how to extend the project with new features
