# Ruta Libre

Ruta Libre is a Java project that simulates the booking process for a car rental system.

This project was restructured following the principles of **Clean Architecture**, **Domain-Driven Design (DDD)**, and *
*Test-Driven Development (TDD)**. The main goal is to keep the business domain independent from frameworks and
infrastructure while organizing the application around domain entities, value objects, repository contracts, and use
cases.

## Architecture

The project follows the principles of **Clean Architecture (Ports and Adapters)**, separating the business domain from
application logic and external infrastructure.

### Package Structure

```text
src/
├── main/
│   └── java/
│       └── com/mccr/rutalibre/
│           ├── domain/
│           │   ├── model/
│           │   │   ├── Booking.java
│           │   │   ├── Client.java
│           │   │   ├── DriverLicense.java
│           │   │   └── Vehicle.java
│           │   │
│           │   └── repository/
│           │       └── BookingRepository.java
│           │
│           └── application/
│               └── usecase/
│                   └── CreateBookingUseCase.java
│
└── test/
    └── java/
        └── com/mccr/rutalibre/
            └── usecase/
                └── CreateBookingUseCaseTest.java
```

### Domain

The `domain` layer contains the core business logic of the application.

* **Entities:** `Booking`, `Client`, and `Vehicle` have unique identities and encapsulate their business rules.
* **Value Objects:** `DriverLicense` is implemented as an immutable Java `record` with defensive validation.
* **Repository contracts:** `BookingRepository` defines the persistence operations required by the domain without
  depending on any framework or infrastructure technology.

### Application

The `application` layer contains the use cases that orchestrate business flows.

* `CreateBookingUseCase` is responsible for the booking creation flow.
* Dependencies are provided through **constructor injection**.
* The use case depends only on the `BookingRepository` interface and does not instantiate concrete repository
  implementations.

### Infrastructure

No infrastructure implementation is included in this milestone. Repository implementations can be added later without
modifying the domain or application layers.

The project does not depend on:

* Spring Boot
* JPA
* Hibernate
* Web frameworks
* Database-specific implementations

This keeps the core of the application independent from external technologies.

## Technologies

* Java 21
* Maven
* JUnit 5 (Jupiter)
* Mockito
* JaCoCo

## Running the Project

### Compile

To compile and verify the project:

```bash
mvn clean compile
```

### Run Unit Tests

To execute the complete unit test suite:

```bash
mvn test
```

The tests validate the domain rules and the decoupling between the application use cases and repository implementations.

## Code Coverage

JaCoCo is used to measure test coverage.

To generate the coverage report:

```bash
mvn jacoco:report
```

The generated report can be found at:

```text
target/site/jacoco/index.html
```

## Implemented Domain Rules

The booking domain includes the following validations and business behaviors:

* Booking must have a valid unique identifier.
* A booking must have a valid client and vehicle.
* Booking dates cannot be null.
* The booking start date cannot be later than the end date.
* A client must have a valid driver license.
* A vehicle must have valid identification and information.
* A vehicle can only be reserved when its status is `AVAILABLE`.
* A booking cannot be created if a booking with the same identifier already exists.

These rules are implemented within the domain model and application use case rather than relying on external frameworks.

## Testing

Unit tests use the **Arrange – Act – Assert (AAA)** pattern and **Mockito** to isolate the application use case from the
repository.

The `CreateBookingUseCaseTest` verifies, among other behaviors:

* Successful booking creation.
* Prevention of duplicate bookings.
* Interaction with the `BookingRepository` contract.
