# OpenAPI Greeting Documentation Specification

## Overview

The greeting endpoint must be fully documented in OpenAPI 3.0 format and exposed through an interactive Swagger UI. This enables API consumers to discover, understand, and test the greeting endpoint without consulting external documentation.

## Requirements

### ADDED: SpringDoc OpenAPI Dependency

Requirement: The project must include SpringDoc OpenAPI Swagger UI starter dependency in pom.xml.

GIVEN a Maven project with Spring Boot 3.3.1
WHEN springdoc-openapi-starter-webmvc-ui 2.x is added to dependencies
THEN the dependency is declared as runtime scope in pom.xml

### ADDED: OpenAPI Specification Endpoint

Requirement: Machine-readable OpenAPI 3.0 specification must be available at `/v3/api-docs`.

GIVEN the application is running
WHEN a GET request is made to `/v3/api-docs`
THEN the response is HTTP 200 with `application/json` content-type
AND the body contains a valid OpenAPI 3.0 specification document
AND the specification includes all exposed endpoints (greet, actuator endpoints)

### ADDED: Swagger UI Endpoint

Requirement: Interactive Swagger UI must be available at `/swagger-ui.html`.

GIVEN the application is running
WHEN a browser navigates to `http://localhost:8080/swagger-ui.html`
THEN an interactive Swagger UI is displayed
AND the greeting endpoint `/greet` is listed with full documentation
AND users can expand the endpoint to see details and test it

### ADDED: Greeting Endpoint OpenAPI Annotations

Requirement: GreetingController.greet() method must have OpenAPI annotations documenting operation details.

GIVEN the GreetingController.greet() method
WHEN @Operation annotation is applied with summary and description
THEN the operation appears in Swagger UI with human-readable documentation

### ADDED: Request Parameter Documentation

Requirement: The `theName` query parameter must be documented with type, description, and example.

GIVEN the GreetingController.greet() method with @RequestParam("theName")
WHEN @Parameters and @Parameter annotations document the parameter
THEN Swagger UI displays:
  - Parameter name: theName
  - Parameter type: String
  - Parameter location: query
  - Parameter description: "Name of the person to greet"
  - Parameter example: "Carlos"
  - Parameter required indicator

### ADDED: Response Documentation

Requirement: HTTP 200 response must be documented with description and example.

GIVEN the GreetingController.greet() method
WHEN @ApiResponse annotation documents the success response
THEN Swagger UI displays:
  - HTTP status: 200 OK
  - Response description: "Successful greeting message"
  - Response media type: text/plain
  - Response example: "Hello Carlos! Good to see you again"

### ADDED: Swagger UI Configuration

Requirement: Actuator endpoints must be excluded from Swagger UI to focus on application endpoints.

GIVEN the application is configured for SpringDoc
WHEN springdoc.swagger-ui.path is configured to `/swagger-ui.html`
AND springdoc.api-docs.path is configured to `/v3/api-docs`
AND springdoc's actuator configuration is set appropriately
THEN Swagger UI displays application-specific endpoints
AND Actuator management endpoints appear in a separate section or are hidden by default

## Acceptance Criteria

- ✅ SpringDoc OpenAPI 2.x dependency is in pom.xml (test by: mvn dependency:tree | grep springdoc)
- ✅ `/v3/api-docs` endpoint returns valid OpenAPI 3.0 JSON (test by: curl http://localhost:8080/v3/api-docs | jq)
- ✅ `/swagger-ui.html` page loads in browser (test by: navigate to http://localhost:8080/swagger-ui.html)
- ✅ Greeting endpoint appears in Swagger UI with operation summary and description
- ✅ `theName` parameter documentation includes description, type, example, and required indicator
- ✅ Response documentation shows HTTP 200 with example response body
- ✅ Swagger UI is accessible without external dependencies or configuration
- ✅ No compilation errors or warnings related to OpenAPI annotations
