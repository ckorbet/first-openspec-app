## ADDED Requirements

### Requirement: REST endpoint for personalized greeting
The system SHALL provide an HTTP GET endpoint that accepts a user name parameter and returns a personalized greeting message.

#### Scenario: Endpoint accepts name parameter
- **WHEN** a GET request is made to `/greet?theName=Carlos`
- **THEN** the endpoint receives and processes the "theName" parameter

#### Scenario: Endpoint returns greeting message
- **WHEN** a GET request is made to `/greet?theName=John`
- **THEN** the response body contains "Hello John! Good to see you again"

#### Scenario: Endpoint returns HTTP 200
- **WHEN** a GET request is successfully processed
- **THEN** the HTTP response status code is 200 (OK)

#### Scenario: Endpoint returns text response
- **WHEN** a GET request is made to `/greet`
- **THEN** the response Content-Type is "text/plain" or "application/json" depending on configuration

### Requirement: Request parameter validation
The system SHALL validate that the theName parameter is a valid string.

#### Scenario: Non-empty name parameter
- **WHEN** a GET request includes `theName=Alice`
- **THEN** the parameter is accepted and used in the greeting

#### Scenario: Name parameter with spaces
- **WHEN** a GET request includes `theName=John Smith`
- **THEN** the full name is preserved in the greeting output
