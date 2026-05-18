# MiniDoodle Backend API

MiniDoodle is a Spring Boot backend application for interview and meeting scheduling. It allows users to create accounts, publish available time slots, book meetings into free slots, and track slot availability using `FREE` and `BUSY` status values.

The project is designed as a backend take-home style application with REST APIs, MySQL persistence, layered architecture, Swagger documentation, Docker Compose support, logging, exception handling, overlap validation, and JUnit service-layer tests.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Database Design](#database-design)
- [API Endpoints](#api-endpoints)
- [Recommended API Testing Flow](#recommended-api-testing-flow)
- [Example API Payloads](#example-api-payloads)
- [Run Locally Without Docker](#run-locally-without-docker)
- [Run With Docker Compose](#run-with-docker-compose)
- [Swagger API Documentation](#swagger-api-documentation)
- [Testing](#testing)
- [Useful Docker and MySQL Commands](#useful-docker-and-mysql-commands)
- [Project Structure](#project-structure)
- [Future Improvements](#future-improvements)

---

## Project Overview

MiniDoodle is a backend scheduling system built using Java 21 and Spring Boot. The application supports three main business areas:

- **Users**: Users can be created, updated, deleted, and retrieved.
- **Time Slots**: Users can create available time windows for scheduling.
- **Meetings**: Meetings can be created and booked into available slots.

The application follows a clean layered architecture:

```text
Client / Postman / Swagger
        ↓ HTTP
Controller Layer
        ↓ DTO
Service Layer
        ↓ Business Logic
Repository Layer
        ↓ JPA / Hibernate
MySQL Database
```

---

## Key Features

- User creation and user management
- Time slot creation, update, retrieval, and deletion
- Meeting creation and meeting management
- Book a meeting using a selected available slot
- Automatic slot status update from `FREE` to `BUSY` after booking
- Availability endpoint for fetching free slots
- Overlap prevention for time slots belonging to the same owner
- DTO-based request/response handling
- MySQL database persistence using Spring Data JPA
- Swagger/OpenAPI documentation
- Docker Compose setup with Spring Boot and MySQL containers
- JUnit service-layer tests
- Logging and centralized exception handling

---

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Main programming language |
| Spring Boot | Backend application framework |
| Spring MVC / Spring Web | REST API and controller layer |
| Spring Data JPA | Repository and database access layer |
| Hibernate | ORM mapping between Java entities and MySQL tables |
| MySQL 8.0 | Relational database |
| Maven | Build and dependency management |
| Docker | Containerization |
| Docker Compose | Running backend and MySQL together |
| Swagger / OpenAPI | API documentation and testing |
| JUnit 5 | Automated service-layer testing |
| ModelMapper | DTO and entity object mapping |
| Lombok | Boilerplate code reduction |
| SLF4J Logger | Application logging |
| Postman | Manual API testing |

---

## Architecture

MiniDoodle uses a layered backend architecture.

| Layer | Responsibility | Example Components |
|---|---|---|
| Controller Layer | Receives HTTP requests and returns HTTP responses | `UserController`, `TimeSlotController`, `MeetingController` |
| DTO Layer | Transfers API request and response data | `UserDto`, `TimeSlotDto`, `MeetingDto` |
| Service Layer | Contains business logic and validation rules | `UserServiceImpl`, `TimeSlotServiceImpl`, `MeetingServiceImpl` |
| Repository Layer | Handles database operations using Spring Data JPA | `UserRepository`, `TimeSlotRepository`, `MeetingRepository` |
| Entity Layer | Maps Java objects to database tables | `AppUser`, `TimeSlot`, `Meeting` |
| Database Layer | Stores persistent application data | MySQL database `minidoodle` |

---

## Database Design

The application uses four main database tables.

| Table | Purpose | Important Fields |
|---|---|---|
| `users` | Stores application users who can own slots, organize meetings, or participate in meetings | `id`, `username`, `email` |
| `time_slots` | Stores available or booked time windows for users | `id`, `start_time`, `end_time`, `status`, `owner_id`, `meeting_id` |
| `meetings` | Stores scheduled meeting details | `id`, `title`, `description`, `start_time`, `end_time`, `organizer_id` |
| `meetings_participants` | Join table for the many-to-many relationship between meetings and participants | `meeting_id`, `participants_id` |

### Relationship Summary

- One user can own many time slots.
- One user can organize many meetings.
- One meeting can have many participants.
- One user can participate in many meetings.
- One booked time slot can be linked to one meeting.
- `meetings_participants` manages the many-to-many relationship between meetings and users.

---

## API Endpoints

Base URL:

```text
http://localhost:8080/api/v1
```

### User APIs

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/users` | Fetch all users |
| GET | `/users/{userId}` | Fetch user by ID |
| POST | `/users/add-user` | Create a new user |
| PUT | `/users/{userId}` | Update user by ID |
| DELETE | `/users/{userId}` | Delete user by ID |
| DELETE | `/users` | Delete all users |

### Time Slot APIs

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/time-slots` | Fetch all time slots |
| GET | `/time-slots/{slotId}` | Fetch time slot by ID |
| GET | `/time-slots/availability` | Fetch all available/free slots |
| POST | `/time-slots/add-time-slot` | Create a new time slot |
| PUT | `/time-slots/{slotId}` | Update time slot by ID |
| PUT | `/time-slots/{slotId}` | Update slot status |
| DELETE | `/time-slots/{slotId}` | Delete slot by ID |
| DELETE | `/time-slots` | Delete all time slots |

### Meeting APIs

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/meetings` | Fetch all meetings |
| GET | `/meetings/{meetingId}` | Fetch meeting by ID |
| GET | `/meetings/organizer/{organizerId}` | Fetch meetings by organizer ID |
| POST | `/meetings/addMeeting` | Create a meeting manually |
| POST | `/meetings/book-slot/{slotId}` | Book a meeting using a selected slot |
| PUT | `/meetings/updateMeeting/{meetingId}` | Update meeting by ID |
| DELETE | `/meetings/{meetingId}` | Delete meeting by ID |
| DELETE | `/meetings` | Delete all meetings |

---

## Recommended API Testing Flow

Use Postman or Swagger in this order:

1. Create recruiter / organizer using `POST /api/v1/users/add-user`
2. Create candidate / participant using `POST /api/v1/users/add-user`
3. Create free time slot using `POST /api/v1/time-slots/add-time-slot`
4. Book selected slot using `POST /api/v1/meetings/book-slot/{slotId}`
5. Verify slot becomes `BUSY` using `GET /api/v1/time-slots/{slotId}`
6. Check available slots using `GET /api/v1/time-slots/availability`
7. View meetings using `GET /api/v1/meetings`

---

## Example API Payloads

### 1. Create Organizer User

```http
POST /api/v1/users/add-user
Content-Type: application/json
```

```json
{
  "username": "recruiter",
  "email": "recruiter@test.com"
}
```

### 2. Create Participant User

```http
POST /api/v1/users/add-user
Content-Type: application/json
```

```json
{
  "username": "candidate",
  "email": "candidate@test.com"
}
```

### 3. Create Time Slot

```http
POST /api/v1/time-slots/add-time-slot
Content-Type: application/json
```

```json
{
  "startTime": "2026-05-26T10:00:00",
  "endTime": "2026-05-26T11:00:00",
  "ownerId": 1
}
```

### 4. Book Slot

```http
POST /api/v1/meetings/book-slot/1
Content-Type: application/json
```

```json
{
  "title": "Java Backend Interview",
  "description": "Spring Boot technical interview",
  "organizerId": 1,
  "participantIds": [2]
}
```

### 5. Update Time Slot

```http
PUT /api/v1/time-slots/1
Content-Type: application/json
```

```json
{
  "startTime": "2026-05-16T12:00:00",
  "endTime": "2026-05-16T13:00:00",
  "status": "FREE",
  "ownerId": 1
}
```

### 6. Update Meeting

```http
PUT /api/v1/meetings/updateMeeting/1
Content-Type: application/json
```

```json
{
  "title": "Updated Java Interview",
  "description": "Updated Spring Boot interview discussion",
  "startTime": "2026-05-26T10:00:00",
  "endTime": "2026-05-26T11:00:00",
  "organizerId": 1,
  "participantIds": [2]
}
```

---

## Run Locally Without Docker

### Prerequisites

- Java 21
- Maven
- MySQL running locally -- only if need to test testcases
- Database named `minidoodle`

### Create Local Database

```sql
CREATE DATABASE minidoodle;
```

### Application Configuration

The application uses environment-variable-based configuration with local defaults:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:mysql://localhost:3306/minidoodle}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:root}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:root}
```

### Run Application

```bash
mvn clean package -DskipTests
mvn spring-boot:run
```

Then open:

```text
http://localhost:8080/api/v1/swagger-ui.html
```

---

## Run With Docker Compose

This is the recommended way to run the project because Docker Compose starts both the Spring Boot backend and MySQL database.

### Prerequisites

- Docker Desktop installed and running

### Build the Spring Boot JAR

```bash
mvn clean package -DskipTests
```

### Start Containers

```bash
docker compose up --build
```

### Run Containers in Background

```bash
docker compose up -d
```

### Verify Running Containers

```bash
docker ps
```

Expected containers:

```text
minidoodle-backend-container
minidoodle-database-container
```

### Docker Services

| Service | Description | Port |
|---|---|---|
| `app` | Spring Boot backend container | `8080:8080` |
| `mysql` | MySQL 8.0 database container | `3307:3306` |

### Docker Database Connection

Inside Docker, the Spring Boot application connects to MySQL using:

```text
jdbc:mysql://mysql:3306/minidoodle
```

From your host machine, the MySQL container is exposed at:

```text
localhost:3307
```

---

## Swagger API Documentation

After starting the application, open Swagger UI:

```text
http://localhost:8080/api/v1/swagger-ui.html
```

Swagger allows you to:

- View all REST endpoints
- Execute API requests from the browser
- Inspect request and response formats
- Test the full booking workflow

---

## Testing

JUnit tests are included for service and entity layers.

| Test Class | Purpose |
|---|---|
| `UserServiceTest` | Tests user creation, retrieval, update, deletion, and list operations |
| `TimeSlotTest` | Tests time slot creation, update, retrieval, and owner association |
| `MeetingServiceTest` | Tests meeting creation, retrieval, and update logic |
| `UserEntityTest` | Tests user entity getter/setter behavior |
| `TimeSlotEntityTest` | Tests time slot entity getter/setter behavior |
| `MeetingEntityTest` | Tests meeting entity getter/setter behavior |

### Run Tests

```bash
mvn clean test
```

### Build Without Running Tests

```bash
mvn clean package -DskipTests
```

The test profile uses:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:mysql://localhost:3306/minidoodle_test}
```

If running tests locally without Docker, create the test database first:

```sql
CREATE DATABASE minidoodle_test;
```

---

## Useful Docker and MySQL Commands

```bash
# Build Spring Boot JAR
mvn clean package -DskipTests

# Start Docker containers
docker compose up --build

# Start containers in background
docker compose up -d

# Check running containers
docker ps

# View backend logs
docker logs minidoodle-backend-container

# Follow backend logs live
docker logs -f minidoodle-backend-container

# View MySQL logs
docker logs minidoodle-database-container

# Stop containers
docker compose down

# Stop containers and remove database volume
docker compose down -v

# Open MySQL shell inside container
docker exec -it minidoodle-database-container mysql -u root -p
```

Inside MySQL:

```sql
SHOW DATABASES;
USE minidoodle;
SHOW TABLES;
SELECT * FROM users;
SELECT * FROM time_slots;
SELECT * FROM meetings;
SELECT * FROM meetings_participants;
```

---

## Project Structure

```text
MiniDoodle/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/minidoodle/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── MiniDoodleApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/com/minidoodle/
│       └── resources/
│           └── application-test.properties
```

---

## Important Implementation Details

| Area | Implementation Detail |
|---|---|
| Logging | Service and controller methods log create, update, delete, and error events |
| Validation | Time slot creation checks that start time is before end time |
| Overlap Prevention | Repository query checks for overlapping slots using `startTime < requestedEnd` and `endTime > requestedStart` |
| Exception Handling | Missing resources throw `ResourceNotFoundException` and are handled through global exception handling |
| Status Control | Slot status is managed using the `SlotStatus` enum with values `FREE` and `BUSY` |
| DTO Mapping | DTOs are used to avoid exposing entity objects directly through APIs |
| Docker Support | Docker Compose runs the backend and MySQL database together |

---

## Future Improvements

- Add JWT-based authentication and authorization
- Build frontend using React
- Add email notifications for meeting booking
- Integrate Google Calendar or Outlook Calendar
- Add Spring Boot Actuator health checks and metrics
- Add CI/CD pipeline using Jenkins
- Deploy the application to AWS
- Improve concurrency handling for simultaneous slot booking
- Add pagination, sorting, and filtering for large datasets


---

## Author

Swapnil Lawande
