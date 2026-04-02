## Context

This change extends the existing Spring Boot 3.3.1 baseline project (maven-java25-spring-boot-actuator) with a new REST endpoint for greeting users. The greeting feature is a simple but important capability that demonstrates:

- Clean separation between HTTP concerns (controller) and business logic (service)
- Integration of Spock Framework for behavior-driven testing in a Spring context
- Extension of the Maven project to include Groovy-based tests alongside Java main code

The project currently has a solid foundation with Spring Boot, Actuator monitoring enabled, and DevTools for development. This change adds the first user-facing feature while establishing patterns for future endpoint additions.

**Current State:**
- Single Spring Boot application with only the main Application.java entry point
- Actuator endpoints for monitoring and health checks
- Maven build pipeline successfully producing executable JAR
- DevTools configured for development convenience
- Logging infrastructure set up with SLF4J/Logback

**Key Constraint:** The greeting endpoint must receive the name as a request parameter (`theName`) and return a formatted greeting string, maintaining exact case of the provided name.

## Goals / Non-Goals

**Goals:**
- Implement a simple REST GET endpoint `/greet` that accepts a `theName` query parameter
- Return greeting response in format: `"Hello {name}! Good to see you again"`
- Create reusable GreetingService layer that handles greeting logic independent of HTTP transport
- Introduce Spock Framework for behavior-driven unit testing
- Demonstrate clean architecture patterns (controller → service → business logic)
- Establish testing conventions for future endpoint development

**Non-Goals:**
- Request validation, error handling, or exception mapping (simple happy-path only)
- Database integration or persistence of greetings
- Authentication, authorization, or security mechanisms
- API documentation or OpenAPI/Swagger integration
- Internationalization or localization of greeting messages
- Performance optimization or caching
- Microservice communication or inter-process greeting coordination

## Decisions

### Decision 1: Use @RequestParam for Parameter Binding

**Choice:** Use Spring's `@RequestParam` annotation to bind the `theName` query parameter, not `@PathVariable`.

**Rationale:**
- User specification explicitly requested "request param named 'theName'"
- Query parameters are semantically appropriate for optional data (name variants)
- Path variables are better for resource identifiers; greeting names are input data, not resource IDs
- Request parameters allow for extensibility (e.g., future ?language=es parameter)

**Alternatives Considered:**
- `@PathVariable` (e.g., `/greet/{theName}`): More RESTful for resource patterns, but doesn't match user requirement
- Form data via `@RequestBody`: Unnecessarily complex for single parameter
- Custom argument resolver: Overengineering for simple parameter binding

### Decision 2: Service Layer Separation

**Choice:** Implement greeting logic in a separate `GreetingService` class, with the controller delegating to this service.

**Rationale:**
- Decouples HTTP semantics from business logic
- Service layer becomes reusable (could be called from scheduled jobs, message handlers, other controllers)
- Enables independent testing of greeting generation without Spring context
- Aligns with Spring best practices and architectural patterns established in the baseline project
- Makes controller focused solely on HTTP concerns (parse request, invoke service, format response)

**Alternatives Considered:**
- Put logic directly in controller: Faster initially, but mixes concerns; harder to test and reuse
- Utility class with static methods: Less consistent with Spring patterns; misses dependency injection opportunities

### Decision 3: Spock Framework for Unit Tests

**Choice:** Use Spock Framework (Groovy-based BDD) for all unit tests in this feature, not JUnit.

**Rationale:**
- Spock's given-when-then syntax directly maps to behavior-driven development and specification requirements
- Cleaner, more readable test code compared to JUnit assertions
- Groovy's dynamic features reduce boilerplate (e.g., no need for `.istrue()` chains)
- Spock's mock/stub support is more concise than Mockito for controller testing
- Aligns with the specification scenarios already defined in BDD format
- Community success with Spring Boot projects; no version conflicts expected

**Alternatives Considered:**
- JUnit 5 + AssertJ + Mockito: Traditional Java approach, more familiar, but more verbose
- Cucumber: Acceptance-level testing, overkill for unit tests
- TestNG: Feature parity with JUnit 5; less Spring ecosystem integration

### Decision 4: Groovy Dependency and Version Management

**Choice:** Add Groovy 4.0.x and Spock 2.4-M1 (compatible with Java 21, Spring Boot 3.3.1, and Gradle/Maven build systems).

**Rationale:**
- Groovy 4.0+ fully supports Java 21 language features and bytecode
- Spock 2.4 provides compatibility with Spring Boot 3.3.x test integration
- Both libraries are mature, well-documented, and widely used in Spring projects
- Maven-groovy-eclipse-compiler correctly integrates Groovy compilation with Maven lifecycle
- Test scope dependencies are excluded from production JAR, keeping binary lean

**Alternatives Considered:**
- Java 8 compatible Groovy 3.0: Outdated; misses Java 21 optimizations
- Spock 2.3: Slightly older; less guarantee of Spring 6 / Boot 3.3 compatibility
- Kotlin for tests: Reduces Groovy surface area but adds Kotlin compiler complexity

### Decision 5: Package Structure and Naming

**Choice:** Place controller and service in `com.example.greeting` package; tests in `src/test/groovy/com/example/greeting/`.

**Rationale:**
- Mirrors baseline project convention (all code under `com.example.*`)
- Feature-based package structure (greeting feature gets its own package)
- Scales cleanly for adding more features (com.example.user, com.example.store, etc.)
- Tests colocated by feature, not by type (all greeting tests together)
- Maven/Groovy tooling expects test source in `src/test/groovy` by default

**Alternatives Considered:**
- Package per layer (com.example.controller, com.example.service): Harder to navigate and understand feature scope
- Tests in `src/test/java`: Works but mixes Groovy and Java sources; Groovy tools expect .groovy source folder

### Decision 6: Controller Response Format

**Choice:** Return String directly from controller method; Spring will serialize to text/plain response.

**Rationale:**
- User requirement is a simple greeting string, not complex JSON structure
- Simplest possible HTTP response (200 OK with text/plain content-type)
- Reduces need for response DTOs, mappers, or message converters
- Matches the greeting service's natural string output

**Alternatives Considered:**
- Return ResponseEntity<String>: Unnecessary boilerplate when default 200 is correct
- Return custom GreetingResponse DTO: Over-engineering for single string field
- Return JSON object {greeting: "..."}: Adds serialization complexity not required by spec

### Decision 7: Testing Strategy - Controller vs Service Isolation

**Choice:** Create separate test classes for controller and service; mock the service in controller tests.

**Rationale:**
- Tests controller HTTP binding and parameter handling independently
- Tests service greeting logic without Spring context (faster, no framework overhead)
- Follows single-responsibility principle: each test class validates one component
- Enables testing failure scenarios (e.g., null parameter) at service level
- Spock mocks easily integrate with Spring `@WebMvcTest` for controller testing

**Alternatives Considered:**
- Integration test covering full stack: Slower, harder to isolate causes of failures
- Only service tests: Misses HTTP binding bugs and contract validation
- Single test class mixing concerns: Harder to maintain; unclear intent of each test

### Decision 8: OpenAPI 3.0 Documentation with SpringDoc OpenAPI

**Choice:** Use SpringDoc OpenAPI 2.x (springdoc-openapi-starter-webmvc-ui) to generate OpenAPI 3.0 specification and expose Swagger UI.

**Rationale:**
- SpringDoc is the recommended successor to Springfox; tightly integrated with Spring Boot 3.x
- Automatic code-first approach: reads Spring annotations to generate specification
- Provides both `/v3/api-docs` (machine-readable OpenAPI JSON) and `/swagger-ui.html` (interactive UI)
- Zero-configuration defaults work out-of-box; minimal pom.xml setup required
- Users can test endpoints interactively from Swagger UI without curl or Postman
- Reduces need for external API documentation; single source of truth in code

**Alternatives Considered:**
- Springfox: Older library; less actively maintained; not optimized for Spring Boot 3
- Manual OpenAPI annotation: More control, but verbose; requires maintaining spec alongside code
- Swagger 2.0: Legacy format; less expressive than OpenAPI 3.0; missing modern features
- No API documentation: Users must infer endpoints from source code or curl examples

### Decision 9: Service Layer Interface Abstraction

**Choice:** Define a `GreetingService` interface and implement it with `GreetingServiceImpl` class, rather than a concrete service class.

**Rationale:**
- Follows Dependency Inversion Principle (DIP): controller depends on abstraction, not concrete implementation
- Improves testability: controller can be tested with mock/stub implementations of the service interface
- Enables alternative implementations: future greeting strategies (e.g., template-based, multi-language) can implement the interface
- Aligns with Spring best practices: inject by interface type, not implementation class
- Makes integration points explicit: the `greeting(String)` contract is discoverable in the interface
- Prepares codebase for future features like decorators, proxies, or alternative strategies

**Alternatives Considered:**
- Concrete GreetingService class only: Simpler initially, but less flexible; harder to mock in tests
- Abstract base class instead of interface: More coupling; less suitable for single-method service
- No abstraction layer: Works for MVP but violates SOLID principles; harder to extend or test


## Risks / Trade-offs

### Risk 1: Groovy Compilation Complexity
**Risk:** Adding Groovy test source alongside Java may cause build-path confusion or slow compilation.

**Mitigation:**
- Maven groovy-eclipse-compiler plugin configured in pom.xml to handle Groovy/Java mixed source
- Tests run only during `mvn test`, not during packaging, keeping JAR creation fast
- Clear separation: `src/main` is 100% Java, `src/test/groovy` is 100% Groovy
- Can add Java tests later if needed without conflicts

### Risk 2: Spring Test Context Overhead
**Risk:** Creating Spring context for controller tests (via `@WebMvcTest`) adds startup time per test.

**Mitigation:**
- Spock allows lightweight mocking without requiring Spring context for service tests
- Controller tests are minimal and focused; only one or two test classes
- DevTools' class reloading makes rapid iteration for fixing test failures quick
- If test suite grows, can be split into fast unit tests (service) + slower integration tests (controller)

### Risk 3: Parameter Validation Edge Cases
**Risk:** No explicit validation of `theName` parameter (null, empty string, special characters).

**Mitigation:**
- Design explicitly excludes validation (non-goal); simple happy-path
- Tests document current behavior (name is used as-is, no trimming or sanitization)
- Service method clearly assumes valid, non-null input; contract visible in code
- Future validation feature can be added in Decision 8 when scope grows

### Risk 4: DevTools and Test Class Reloading Interaction
**Risk:** DevTools enabled at runtime may interfere with Groovy test class reloading.

**Mitigation:**
- DevTools configured as `runtime` scope + `optional=true` → excluded from tests
- Test classpath does not include DevTools, avoiding classloader conflicts
- Manual test runs via IDE or `mvn test` do not trigger DevTools
- Only production JAR runs with DevTools; controlled environment

### Risk 5: SpringDoc Dependency Overhead
**Risk:** Adding SpringDoc OpenAPI (springdoc-openapi-starter-webmvc-ui) increases JAR size and startup time.

**Mitigation:**
- SpringDoc is lightweight (~1-2 MB); acceptable trade-off for interactive API documentation
- Swagger UI is optional; can be disabled via application.properties if needed (`springdoc.swagger-ui.enabled=false`)
- No runtime performance impact on endpoint execution; only affects startup and discovery
- Future versions can optimize or move to build-time OpenAPI generation if needed

## Migration Plan

**Deployment Steps:**
1. Add Groovy and Spock dependencies to existing pom.xml
2. Implement `GreetingService.java` (single public method: greeting(String theName) → String)
3. Implement `GreetingController.java` with GET `/greet` endpoint, @RequestParam binding
4. Create Spock test classes: `GreetingServiceSpec.groovy` and `GreetingControllerSpec.groovy`
5. Build with `mvn clean test` to verify tests pass
6. Build with `mvn clean package` to verify executable JAR still works
7. Start JAR with `java -jar spring-boot-java25-actuator-1.0.0.jar`
8. Test endpoint manually: `curl "http://localhost:8080/greet?theName=Carlos"` → `"Hello Carlos! Good to see you again"`

**Rollback Strategy:**
- This is purely additive; no existing code modified
- If issues arise, can remove new files (GreetingController, GreetingService, tests) and revert pom.xml dependencies
- No database changes or migrations; no state to roll back

**No Breaking Changes:**
- Existing Actuator endpoints continue operating
- Existing Application.java entry point unchanged
- Existing DevTools functionality unaffected
- Can coexist with other features without interference

## Open Questions

1. **Future Error Handling:** Should endpoint return 400 if `theName` is missing, or use a default? (Deferred: outside scope of MVP)
2. **Greeting Customization:** Should service accept a greeting template? (Deferred: add in future feature)
3. **Response Media Type:** Should return application/json with {greeting: "..."} object? (Decided: No, text/plain is simpler)
4. **Internationalization:** Should greeting messages be translatable? (Deferred: outside scope; hardcoded English)
5. **Rate Limiting:** Should endpoint have rate limits? (Deferred: outside scope; no throttling)
