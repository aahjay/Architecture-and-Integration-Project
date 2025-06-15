# AI-Project Distributed Student Services System

## Overview

This system simulates a distributed academic services platform, supporting student data management, help request handling, and thesis application processes. The architecture consists of several independent services (HIS, Middleware, Peregos, WyseFlow) communicating asynchronously via RabbitMQ.

## Prerequisites

- Java 17 or newer
- Maven
- RabbitMQ (running locally on default port)
- Modern web browser

## Setup Instructions

### 1. Start RabbitMQ

Ensure RabbitMQ is running locally.  
Default management UI: [http://localhost:15672](http://localhost:15672)  
Default credentials: `guest` / `guest`

### 2. Build All Services

Open a terminal in the project root and run:

```bash
mvn clean install
```

### 3. Start Each Service

Open a separate terminal for each service and run:

```bash
# HIS Service
cd his
mvn spring-boot:run

# Middleware Service
cd ../middleware
mvn spring-boot:run

# Peregos Service
cd ../peregos
mvn spring-boot:run

# WyseFlow Service
cd ../wyseflow
mvn spring-boot:run
```

Each service will start on its configured port (see each `application.properties`).

### 4. Access the Web UIs

- **HIS:** [http://localhost:8080/his/form](http://localhost:8084/his/form)
- **Peregos:** [http://localhost:8081/peregos/form](http://localhost:8081/peregos/form)
- **WyseFlow:** [http://localhost:8082/wyseflow/form](http://localhost:8082/wyseflow/form)

### 5. Using the System

- **HIS:** Log in with a student ID and password (see `InitData.java` for test users), then view and select study programs.
- **Peregos:** Enter a student ID to fetch data, then create and submit a support request.
- **WyseFlow:** Enter a student ID to check thesis eligibility and view related data.

### 7. Stopping the System

To stop all services, press `Ctrl+C` in each terminal window.

## Troubleshooting

- **Port conflicts:** Make sure each service uses a unique port (see `server.port` in each `application.properties`).
- **RabbitMQ errors:** Ensure RabbitMQ is running and accessible.
- **Template errors:** Verify that all HTML templates are in `src/main/resources/templates/`.

## Project Structure

- `his/` – Student data and authentication service
- `middleware/` – Message transformation and routing
- `peregos/` – Support ticket service
- `wyseflow/` – Thesis application and eligibility service

## Contact

For questions or issues, please contact the project maintainers or refer to the code comments for further