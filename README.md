# Flight Booking System (Spring Boot Backend)

##  Project Overview
The Flight Booking System is a backend application developed using **Java and Spring Boot**.  
It provides RESTful APIs for managing flight bookings, passengers, and reservations.

The system simulates a real-world airline reservation workflow and demonstrates backend development using Spring Boot architecture.


##  Features
- Flight search and management
- Ticket booking system
- Passenger details management
- Reservation handling
- REST API implementation
- Database integration
- Exception handling


## Tech Stack

# Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- REST APIs

# Database
- MySQL

# Tools & IDE
- Eclipse / VS Code
- Postman (API Testing)
- Git & GitHub
- Maven


# Concepts Used
- Object-Oriented Programming (OOP)
- MVC Architecture
- Dependency Injection
- RESTful Web Services
- JPA Repository Pattern
- Exception Handling
- Layered Architecture


# Project Architecture
The project follows Spring Boot layered architecture:

- **Controller** : Handles API requests
- **Service** : Business logic
- **Repository** : Database operations
- **Entity** : Database models

## Project Structure

src/main/java/
├── controller/
├── service/
├── repository/
├── dao/
├── entity/
└── FlightBookingApplication.java


---

##  Setup & Run

### 1. Clone Repository


### 2. Open Project
Open using Eclipse or VS Code.

### 3. Configure Database
Update `application.properties`:

### 4️. Run Application
Run:FlightBookingApplication.java

Server starts at:http://localhost:8080


##  API Testing
Use **Postman** to test REST endpoints.

GET /flights
POST /bookings
PUT /flights/{id}
DELETE /flights/{id}



---

##  Project Objective
To design and implement a scalable backend system using Spring Boot and REST APIs that handles airline booking operations efficiently.

---

##  Author
**Manikantha Hanagal**

- Java & Spring Boot Developer
- Backend Development Enthusiast

---

##  Future Enhancements
- JWT Authentication
- Role-based access
- Frontend integration (React/Angular)
- Payment gateway integration
- Cloud deployment (AWS)

