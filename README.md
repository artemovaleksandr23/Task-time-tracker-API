# Task Time Tracker API

## 📌 Описание проекта

REST-сервис для учета времени, затраченного сотрудниками на выполнение задач.

### Реализованный функционал:

* Создание задачи
* Получение задачи по ID
* Изменение статуса задачи (NEW / IN_PROGRESS / DONE)
* Создание записи о затраченном времени
* Получение записей о времени сотрудника за период

---

## 🛠️ Технологии

* Java 17
* Spring Boot 3
* MyBatis
* PostgreSQL
* Docker
* JUnit 5 + Mockito

---

## 🚀 Как запустить проект

### 1. Запуск базы данных (PostgreSQL через Docker)

```bash
docker compose up -d
```

---

### 2. Запуск приложения

```bash
mvn spring-boot:run
```

---

### 3. Приложение будет доступно по адресу:

```
http://localhost:8080
```

---

## 🧪 Проверка API

### 📌 Task API

#### Создание задачи

```
POST /tasks
```

Body:

```json
{
  "title": "Test task",
  "description": "My task"
}
```

---

#### Получение задачи

```
GET /tasks/{id}
```

---

#### Обновление статуса

```
PUT /tasks/{id}/status?status=DONE
```

---

### 📌 TimeRecord API

#### Создание записи времени

```
POST /time-records
```

Body:

```json
{
  "employeeId": 1,
  "taskId": 1,
  "startTime": "2026-04-17T10:00:00",
  "endTime": "2026-04-17T12:00:00",
  "description": "Work"
}
```

---

#### Получение записей за период

```
GET /time-records?employeeId=1&start=2026-04-17T00:00:00&end=2026-04-18T00:00:00
```

---

## ⚠️ Обработка ошибок

Сервис возвращает ошибки в формате:

```json
{
  "message": "Описание ошибки",
  "status": 400,
  "timestamp": "2026-04-17T10:00:00"
}
```

Примеры:

* 404 — задача не найдена
* 400 — некорректный диапазон времени

---

## 📦 Postman коллекция

В проекте приложены готовые запросы:

```
postman/task-tracker.postman_collection.json
postman/task-tracker.postman_environment.json
```

---

### Как использовать:

1. Открыть Postman
2. Нажать **Import**
3. Импортировать оба файла из папки `postman`
4. Выбрать Environment: **Task Tracker Environment**
5. Выполнять запросы

---

## ✅ Тестирование

Проект покрыт unit-тестами (JUnit + Mockito).

Запуск тестов:

```bash
mvn test
```

---

## 📌 Дополнительно

* Используется слоистая архитектура (Controller → Service → Mapper)
* Реализована обработка исключений через `@RestControllerAdvice`
* Используется валидация входных данных

---
