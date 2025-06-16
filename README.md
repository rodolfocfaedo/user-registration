
# User Registration API

[![Java](https://img.shields.io/badge/Java-24-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring--Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()
[![License](https://img.shields.io/badge/license-MIT-lightgrey.svg)]()

A simple and clean **REST API** for user registration and management. Developed with **Spring Boot**, **Spring Data JPA**, and **H2 Database**.

---

## 📖 Overview

This project demonstrates **clean code principles** and a **layered architecture** approach in Java backend development.  
It provides **CRUD operations** for users, with clearly separated responsibilities:

- **Controller Layer**
- **Business (Service) Layer**
- **Repository Layer**
- **DTOs and Converters**
- **Custom Exception Handling**

---

## 🛠️ Technologies Used

- Java 24
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 In-Memory Database
- Swagger OpenAPI (Springdoc)
- JUnit (Unit Testing)

---

## ✅ Features

- ✅ Create User
- ✅ Retrieve User by Email
- ✅ Update User by Email
- ✅ Delete User by Email
- ✅ API Documentation with Swagger
- ✅ In-Memory Database Console (H2)
- ✅ Unit Tests for Business Logic

---

## 📂 Project Structure
```
  src/main/java
    ├── config -> Swagger configuration
    ├── controller -> REST controllers
    ├── business -> Business logic layer
    ├── repository -> Spring Data JPA repositories
    ├── entity -> JPA entity classes
    ├── dto -> Data Transfer Objects (DTOs)
    ├── converter -> Entity-DTO converters
    ├── exception -> Custom exception classes
```
---

## 🎯 API Endpoints

| Method | Endpoint             | Description                  |
|------  |--------------------- |----------------------------- |
| POST   | `/api/users`          | Create a new user            |
| GET    | `/api/users/{email}`  | Retrieve user by email       |
| PUT    | `/api/users/{email}`  | Update user by email         |
| DELETE | `/api/users/{email}`  | Delete user by email         |

---


## 🧪 Testing

Unit tests were written with **JUnit 5** to ensure the reliability of business rules.

Run tests:

```bash
mvn test
```
---

🌐 Swagger UI
API documentation is available at:
```
  http://localhost:8080/swagger-ui/index.html
```
---

💾 H2 Database Console
For in-memory database access and inspection:
```
  http://localhost:8080/h2-console

```
Default database configuration:

  - JDBC URL: jdbc:h2:mem:testdb

   - User: sa

   - Password: (empty)

Configuration can be found in:
```
src/main/resources/application.properties
```

---

🏗️ How to Run Locally
Requirements:

Java 17+
Maven 3.x

Steps:
```
git clone https://github.com/rodolfocfaedo/user-registration.git
cd user-registration
mvn spring-boot:run
```
---

📌 Build & Run Status

Stage	Status
Build	✅ Passing
Unit Tests	✅ Passing
API Endpoints	✅ Operational
Swagger Config	✅ Working

---

👤 Author
Rodolfo Cunhasque Faedo
- https://github.com/rodolfocfaedo

📃 License
Distributed under the MIT License.
