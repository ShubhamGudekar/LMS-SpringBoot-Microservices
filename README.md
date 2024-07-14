# LMS-SpringBoot-Mircoservices
![Java](https://img.shields.io/badge/Java-17-blue?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-green?style=flat&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat&logo=mysql)
![Kafka](https://img.shields.io/badge/Apache_Kafka-2.8.1-red?style=flat&logo=apache-kafka)

This project implements a Library Management System (LMS) using microservices architecture with Spring Boot and an event-driven approach leveraging Apache Kafka.

The project consists of the following microservices:

1. **Book Service** - Manages book information.
2. **Customer Service** - Manages customer information.
3. **Borrowing Service** - Manages book borrowing transactions.

This system is designed around Kafka to enable:

- **Publish-Subscribe Messaging**: Services communicate via Kafka topics, ensuring loose coupling and scalability.
- **Event Sourcing**: Changes in data (e.g., book borrowed, returned) are captured as events and propagated through Kafka topics.
- **Real-time Updates**: Services react to events in real-time, updating their state asynchronously.

## Tech Stack

- **Java**: Programming language used for development.
- **Spring Boot**: Framework for building microservices.
- **Docker**: Containerization platform.
- **Apache Kafka**: Event streaming platform for implementing event-driven architecture.
- **MySQL**: Relational database management system.

## Setup and Installation

### Prerequisites

- Docker and Docker Compose installed on your machine.

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/LMS-SpringBoot-Microservices.git
    cd LMS-SpringBoot-Microservices
    ```

2. Build the services:
    ```bash
    mvn clean install
    ```

3. Start the services using Docker Compose:
    ```bash
    docker-compose up --build
    ```

## Endpoints

Once the services are up and running, you can access them via the following URLs:

- Book Service: `http://localhost:9000`
- Customer Service: `http://localhost:9001`
- Borrowing Service: `http://localhost:9002`


