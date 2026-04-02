## Why

Users need a simple greeting endpoint to demonstrate REST API capabilities in the Spring Boot application. This provides a basic but realistic example of handling request parameters and returning formatted responses, serving as a foundation for more complex API endpoints.

## What Changes

- Add a new REST controller class `GreetingController` in the `com.example.greeting` package
- Implement a `GreetingService` interface and `GreetingServiceImpl` implementation for greeting generation
- Implement a GET endpoint `/greet` that accepts a `theName` query parameter
- Return a personalized greeting message in format: "Hello {name}! Good to see you again"
- Add corresponding unit tests using Spock Framework to verify endpoint behavior
- Add Spock Framework dependencies to pom.xml for testing
- Integrate OpenAPI 3.0 specification via SpringDoc OpenAPI Swagger UI
- Add OpenAPI annotations (`@Operation`, `@Parameters`, `@ApiResponses`) to greet endpoint
- Expose interactive Swagger UI at `/swagger-ui.html` for API documentation and testing

## Capabilities

### New Capabilities
- `rest-greeting-endpoint`: GET endpoint for personalized greeting messages
- `greeting-service`: Service layer for greeting generation logic
- `greeting-unit-tests`: Spock Framework unit tests for greeting functionality
- `openapi-greeting-documentation`: OpenAPI 3.0 specification and Swagger UI integration for greeting endpoint

### Modified Capabilities

## Impact

- **Code**: New `com.example.greeting` package with controller and service classes
- **APIs**: New HTTP endpoint: `GET /greet?theName=<name>` → returns greeting string
- **API Documentation**: New `/swagger-ui.html` endpoint exposes interactive Swagger UI with OpenAPI 3.0 specification
- **Dependencies**: Spock Framework 2.x, Groovy for testing; SpringDoc OpenAPI 2.x for API documentation
- **Systems**: Spring application startup loads new controller and SpringDoc integration, endpoints discoverable via Swagger UI
