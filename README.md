# 🟡 CQRS Microservice Project

A Java Spring Boot project designed using the CQRS (Command Query Responsibility Segregation) pattern. This system simulates a basic customer and invoice management application with separate services for writing and reading operations.

## ⚙️ Architecture

- **customer-command**: Handles write operations (create/update) and publishes events via Kafka
- **customer-query**: Listens to Kafka events and updates a fast-access data store (Redis)

This separation allows for loose coupling, scalability, and performance optimization for both write-heavy and read-heavy scenarios.

## 🛠️ Technologies Used

- Java  
- Spring Boot  
- Spring Data JPA  
- Kafka  
- Redis  
- PostgreSQL  
- Docker (local environment setup)

## 📁 Project Structure

CQRS-Customer-Billing-System/
├── customer-command/
│   └── Java source, controllers, services, DTOs
├── customer-query/
│   └── Java source, Kafka consumers, Redis integration

## ▶️ How to Run
Each service can be built and run individually using Maven or inside containers. Recommended setup is via Docker Compose (not included in this repo).

## 🙋‍♂️ About the Project
This was developed during a summer internship. I worked primarily on the customer-command side and built full CRUD support, Kafka integration, and PostgreSQL persistence. I also contributed to the customer-query side, consuming events and updating Redis accordingly.

The purpose was to demonstrate understanding of:

CQRS architecture
Event-driven communication
Microservice isolation
Practical Spring Boot development
