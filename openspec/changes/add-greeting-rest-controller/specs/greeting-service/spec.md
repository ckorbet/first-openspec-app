## ADDED Requirements

### Requirement: Service interface definition
The system SHALL define a clear service contract via an interface.

#### Scenario: Interface declares greeting method
- **WHEN** the GreetingService interface is examined
- **THEN** it declares a public method `greeting(String theName): String`

#### Scenario: Controller depends on interface
- **WHEN** the GreetingController is implemented
- **THEN** it declares a dependency of type GreetingService (the interface, not the implementation)

#### Scenario: Implementation is autowired to interface type
- **WHEN** Spring application starts
- **THEN** it automatically injects the GreetingServiceImpl instance where GreetingService interface is required

### Requirement: Greeting message generation
The system SHALL provide a service implementation that generates personalized greeting messages.

#### Scenario: Generate greeting with name
- **WHEN** the greeting service is called with name "Carlos"
- **THEN** it returns "Hello Carlos! Good to see you again"

#### Scenario: Preserve name formatting
- **WHEN** the greeting service receives a name with specific casing
- **THEN** the name is preserved exactly as provided in the output

#### Scenario: Implementation is stateless
- **WHEN** the greeting service implementation is called multiple times with the same name
- **THEN** it returns the same greeting message each time

### Requirement: Service integration with controller
The system SHALL allow the controller to delegate greeting generation to the service layer.

#### Scenario: Controller calls greeting service
- **WHEN** the REST controller receives a request with theName parameter
- **THEN** it delegates to the greeting service for message generation

#### Scenario: Service returns to controller
- **WHEN** the greeting service generates a message
- **THEN** the controller receives the generated message and returns it in the HTTP response
