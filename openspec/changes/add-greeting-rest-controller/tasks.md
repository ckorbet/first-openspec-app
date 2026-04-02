## 1. Dependency Management

- [x] 1.1 Add Groovy dependency to pom.xml (version 4.0.14 or later)
- [x] 1.2 Add Spock Framework dependency to pom.xml (version 2.4-M1 or later, test scope)
- [x] 1.3 Add SpringDoc OpenAPI Swagger UI starter to pom.xml (version 2.x, runtime scope)
- [x] 1.4 Add Groovy Eclipse Compiler to maven-compiler-plugin configuration
- [x] 1.5 Add gmaven-plus-plugin to plugins section for Groovy compilation
- [x] 1.6 Verify pom.xml parses without errors (mvn validate)

## 2. Service Layer Implementation

- [x] 2.1 Create directory src/main/java/com/example/greeting/
- [x] 2.2 Create GreetingService.java interface with greeting(String theName) method signature
- [x] 2.3 Create GreetingServiceImpl.java class with @Service annotation
- [x] 2.4 Implement GreetingServiceImpl as implementation of GreetingService interface
- [x] 2.5 Implement greeting(String theName) method returning formatted string
- [x] 2.6 Return message in format: "Hello {name}! Good to see you again"
- [x] 2.7 Apply copyright header to GreetingService.java (per copyright-insert-java skill)
- [x] 2.8 Apply copyright header to GreetingServiceImpl.java (per copyright-insert-java skill)

## 3. Controller Layer Implementation

- [x] 3.1 Create GreetingController.java class with @RestController annotation
- [x] 3.2 Inject GreetingService interface (not concrete implementation) via @Autowired or constructor
- [x] 3.3 Create GET endpoint method mapped to /greet path
- [x] 3.4 Add @RequestParam("theName") String parameter to method signature
- [x] 3.5 Call service.greeting(theName) and return result
- [x] 3.6 Verify method returns String directly (Spring serializes as text/plain)
- [x] 3.7 Add @Operation annotation with summary: "Get greeting message"
- [x] 3.8 Add @Operation description: "Returns a personalized greeting for the provided name"
- [x] 3.9 Add @Parameter annotation documenting theName parameter with description and example
- [x] 3.10 Add @ApiResponse annotation documenting 200 OK response with example
- [x] 3.11 Apply copyright header to GreetingController.java (per copyright-insert-java skill)

## 4. Spock Test Implementation

- [x] 4.1 Create src/test/groovy/com/example/greeting/ directory
- [x] 4.2 Create GreetingServiceImplSpec.groovy for testing GreetingServiceImpl implementation
- [x] 4.3 Implement test: greeting('Carlos') returns 'Hello Carlos! Good to see you again'
- [x] 4.4 Implement test: greeting('Alice') returns 'Hello Alice! Good to see you again'
- [x] 4.5 Implement test: greeting preserves case (e.g., 'john' stays 'john', not 'John')
- [x] 4.6 Create GreetingControllerSpec.groovy with Spring WebMvcTest and mocked interface
- [x] 4.7 Create mock of GreetingService interface in controller test setup
- [x] 4.8 Implement test: GET /greet?theName=Carlos delegates to mocked service
- [x] 4.9 Implement test: GET /greet?theName=Alice delegates to mocked service
- [x] 4.10 Implement test: GET /greet returns 200 OK with service-provided message
- [x] 4.11 Implement test: Response content-type is text/plain
- [x] 4.12 Verify controller depends on interface, not concrete implementation

## 5. OpenAPI/Swagger Configuration

- [x] 5.1 Verify SpringDoc OpenAPI dependency is declared in pom.xml (springdoc-openapi-starter-webmvc-ui)
- [x] 5.2 Configure SpringDoc in application.properties: springdoc.api-docs.path=/v3/api-docs
- [x] 5.3 Configure Swagger UI path in application.properties: springdoc.swagger-ui.path=/swagger-ui.html
- [x] 5.4 Configure Swagger UI title in application.properties: springdoc.swagger-ui.swagger-ui-name=Greeting API Docs
- [x] 5.5 Exclude Actuator endpoints from Swagger UI (if desired)
- [x] 5.6 Verify OpenAPI annotations are imported in GreetingController (io.swagger.v3.oas.annotations.*)

## 6. Build & Verification

- [x] 6.1 Run `mvn clean compile` to verify Java compilation
- [x] 6.2 Run `mvn clean test` to verify all Spock tests pass
- [x] 6.3 Verify test output shows both GreetingServiceSpec and GreetingControllerSpec passed
- [x] 6.4 Run `mvn clean package` to create executable JAR
- [x] 6.5 Start application with `java -jar target/spring-boot-java25-actuator-1.0.0.jar`
- [x] 6.6 Test endpoint manually: `curl "http://localhost:8080/greet?theName=Carlos"`
- [x] 6.7 Verify response is exactly: "Hello Carlos! Good to see you again"
- [x] 6.8 Verify Actuator endpoints still accessible (/actuator/health, /actuator/metrics)
- [x] 6.9 Verify OpenAPI spec is accessible: `curl http://localhost:8080/v3/api-docs | jq`
- [x] 6.10 Verify Swagger UI loads: Browse to `http://localhost:8080/swagger-ui.html`
- [x] 6.11 Verify greeting endpoint appears in Swagger UI with full documentation
- [x] 6.12 Test endpoint from Swagger UI: Expand `/greet` and use "Try it out" button

## 7. Documentation & Finalization

- [x] 7.1 Update README.md with new greeting endpoint documentation
- [x] 7.2 Add curl example to README.md showing greeting endpoint usage
- [x] 7.3 Document Spock testing framework integration in README.md
- [x] 7.4 Document OpenAPI/Swagger UI integration in README.md
- [x] 7.5 Add Swagger UI URL example: `http://localhost:8080/swagger-ui.html`
- [x] 7.6 Add OpenAPI spec URL example: `http://localhost:8080/v3/api-docs`
- [x] 7.7 Verify all files have proper copyright headers
- [x] 7.8 Verify no compilation warnings or errors in build output
