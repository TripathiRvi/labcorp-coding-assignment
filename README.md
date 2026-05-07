# Employee Vacation Service

Production-ready Spring Boot application for managing employee vacation and usage using  Java & Spring Boot and clean architecture principles.

---

## Tech Stack

- Java 17
- Spring Boot 3.x
- Maven
- Spring Web
- Bean Validation
- Swagger/OpenAPI
- JUnit 5
- Mockito
- SLF4J Logging

---

## Modern Java Features Used

- Java 17 sealed classes
- Records for immutable DTOs
- Stream API
- Enhanced exception handling

---

# Features

- Supports:
  - Hourly Employees
  - Salaried Employees
  - Managers

- Vacation accumulation rules:
  - Hourly: 10 days/year
  - Salaried: 15 days/year
  - Manager: 30 days/year

- Validations:
  - Employee cannot work more than 260 days/year
  - Vacation balance cannot become negative

- Global exception handling
- Structured logging using SLF4J
- Thread-safe in-memory repository
- DTO-based API responses
- Unit & integration tests
- OpenAPI documentation

---

# Architecture

Layered architecture used:

controller → service → repository → model

The application follows clean layered architecture with proper separation of concerns and encapsulated domain logic.

Business rules are handled within the domain model to ensure valid state transitions and maintainability.

---

# Design Decisions

## Sealed Classes

Used Java 21 sealed classes for controlled inheritance hierarchy and improved type safety.

## Thread Safety

- ConcurrentHashMap used for repository storage
- Synchronized methods used for employee state updates

## Validation

Implemented using:
- Bean Validation
- Business validation exceptions
- Global exception handling

## DTO-Based API Design

DTOs are used to avoid exposing internal domain models directly through REST APIs.

## Logging

Structured logging implemented using SLF4J for observability and operational debugging.

## No Database

As per assignment requirement, in-memory storage is used.

---

# Error Handling

The application uses centralized global exception handling with:

- Validation error handling
- Business exception mapping
- Malformed JSON request handling
- Generic fallback exception handling

All exceptions are mapped to meaningful HTTP responses.

---

# Running the Application

## Build

```bash
mvn clean install
```

## Run

```bash
mvn spring-boot:run
```

---

# Swagger / OpenAPI

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# APIs

## Get All Employees

```http
GET /api/v1/employees
```

---

## Get Employee By Id

```http
GET /api/v1/employees/{id}
```

---

## Employee Work API

```http
POST /api/v1/employees/{id}/work
```

Request:

```json
{
  "days": 20
}
```

---

## Employee Vacation API

```http
POST /api/v1/employees/{id}/vacation
```

Request:

```json
{
  "days": 2
}
```

---

# Testing

The application includes:

- Unit tests
- Integration tests
- Validation tests
- Boundary condition tests
- Error handling tests

Run tests using:

```bash
mvn test
```

---

# Future Enhancements

Possible production enhancements:

- Database persistence
- Authentication & authorization
- Distributed caching
- Metrics & monitoring
- Kubernetes deployment
- CI/CD pipeline integration

---

# Author

Ravi Tripathi