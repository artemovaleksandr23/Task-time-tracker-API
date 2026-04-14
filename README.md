# ⏱️ Task Time Tracker API

Spring Boot REST API for tracking tasks and time spent by employees.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot 3.x
- MyBatis
- PostgreSQL (Docker)
- Maven

---

## 📦 Project Features

### Task management:
- Create task
- Get task by ID
- Update task status (NEW / IN_PROGRESS / DONE)

### Time tracking:
- Create time record for task
- Get time records by employee and time period

---

## 🐳 How to run project

### 1. Start PostgreSQL (Docker)

```bash
docker compose up -d