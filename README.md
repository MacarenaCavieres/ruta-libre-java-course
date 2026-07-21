# Ruta Libre

Ruta Libre is a Java project that simulates the booking process for a car rental system. The focus of this first
milestone is the implementation of the domain business logic and its validation through unit testing using Test-Driven
Development (TDD).

## Architecture

This project implements a **Pure Domain Core** following the principles of **Clean Architecture (Ports and Adapters)**.

The business logic is completely independent from external technologies:

- No Spring Boot.
- No JPA or database integration.
- No web framework dependencies.
- External dependencies are abstracted through interfaces and injected via constructor injection.

Unit tests are implemented using **JUnit 5** and **Mockito**, ensuring that the domain logic is tested in isolation.

## Technologies

- Java 21
- Maven
- JUnit 5 (Jupiter)
- Mockito
- JaCoCo

## Running the Test Suite

From the project root, execute:

```bash
mvn clean test
```

## Generating the Code Coverage Report

From the project root, execute:

```bash
mvn jacoco:report
```

Alternatively, you can generate the tests and coverage report in a single command:

```bash
mvn clean test
```

## Coverage Report

![Coverage Report](/imgs/coverage.png)

## Coverage Report Location

After generating the report, open the following file in your browser:

```text
target/site/jacoco/index.html
```

---

## Implemented Business Rules

The first milestone includes the implementation of the booking creation process through the `BookingService`, including
the following business rules:

- Validate that a client is associated with the booking.
- Validate that the selected vehicle is available.
- Validate that the booking start date is not later than the end date.
- Save the booking through a repository interface, keeping the domain independent from infrastructure.

All business rules are covered by unit tests following the **Arrange – Act – Assert (AAA)** pattern.