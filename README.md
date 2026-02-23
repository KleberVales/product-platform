# 📦 product-platform

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
<img src="https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=Apache%20Kafka&logoColor=white" alt="Apache Kafka" />
<img src="https://img.shields.io/badge/REST_APIs-6E6E6E?style=for-the-badge&logo=api&logoColor=white" alt="REST APIs" />

---

A microservices platform built in Java and Spring Boot, organized with various services representing common functionalities in e-commerce applications (such as login service, shopping cart, products, orders, etc.). This project serves both as a study and as a basis for developing distributed architectures with Spring Boot and communication between services.

---

## Overview

This repository contains microservices that implement separate functionalities independently, using:

- Java with Spring Boot
- Microservices organized by domain
- PostgreSQL as a database
- Kafka for communication between services
- Architecture designed for scalability and maintenance.

---

## Services and Features

| Serviço | Descrição |
|---------|-----------|
| `auth-service` | Authentication validation (test) |
| `cart-service` | Shopping cart operations |
| `order-service` | Order management |
| `product-service` | CRUD of products |
| `user-service` | User testing / validations |
| `payment-service` |   |
| ìnventory-service`  |    |  

Each service is a standalone Spring Boot project, with its own business logic and configuration.

---

## Technologies

The project uses the following main technologies:

- 🔹 **Java 17+**
- 🔹 **Spring Boot** (Spring Web, Spring Data, etc.)
- 🔹 **PostgreSQL**
- 🔹 **Apache Kafka**
- 🔹 **Docker**
- 🔹 **Gradle**

---

## How to Run the Project

**Clone the repository**
```bash
git clone https://github.com/KleberVales/product-platform.git
cd product-platform
```

## Flowchart


```mermaid
flowchart TD
    subgraph Frontend
        UI[Client Application]
    end
    
    subgraph Gateway
        API[API Gateway]
    end
    
    subgraph Core_Services
        AUTH[Auth Service]
        USER[User Service]
    end
    
    subgraph Message_Broker
        BROKER[Message Broker<br/>RabbitMQ/Kafka]
    end
    
    subgraph Business_Services
        CART[Cart Service]
        PRODUCT[Product Service]
        ORDER[Order Service]
        PAYMENT[Payment Service]
        INVENTORY[Inventory Service]
    end
    
    UI -->|HTTP/HTTPS| API
    API -->|Authentication| AUTH
    API -->|User Management| USER
    
    AUTH -.->|Events/Messages| BROKER
    USER -.->|Events/Messages| BROKER
    
    BROKER <-->|Pub/Sub| CART
    BROKER <-->|Pub/Sub| PRODUCT
    BROKER <-->|Pub/Sub| ORDER
    BROKER <-->|Pub/Sub| PAYMENT
    BROKER <-->|Pub/Sub| INVENTORY
    
    CART -.->|Async Communication| BROKER
    PRODUCT -.->|Async Communication| BROKER
    ORDER -.->|Async Communication| BROKER
    PAYMENT -.->|Async Communication| BROKER
    INVENTORY -.->|Async Communication| BROKER
    
    classDef frontend fill:#e1f5fe,stroke:#01579b
    classDef gateway fill:#fff3e0,stroke:#e65100
    classDef core fill:#e8f5e8,stroke:#1b5e20
    classDef broker fill:#f3e5f5,stroke:#4a148c
    classDef business fill:#fff0f0,stroke:#b71c1c
    
    class UI frontend
    class API gateway
    class AUTH,USER core
    class BROKER broker
    class CART,PRODUCT,ORDER,PAYMENT,INVENTORY business

```

--- 

Kleber Vales

Java & Spring Software Engineer

Cloud | DevOps | Microservices | Docker | Kubernetes | Git/Github | 


