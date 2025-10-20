# 🧩 Task API – Software Effort Estimation Project

*Author:* Sivaganga KM 
*Date:* October 20, 2025  
*Environment:* Windows 10, IntelliJ IDEA, MongoDB 8.2, Java 20, Spring Boot 3.x  

---

## 📘 Overview

This project is a *Task Management REST API* built with *Spring Boot* and *MongoDB*.  

### Features
- ✅ Create new tasks with system commands
- ✅ Retrieve all tasks from the database
- ✅ Execute tasks and store execution results
- ✅ Persistent storage in MongoDB

---

## ⚙ Technologies Used

| Component | Technology |
|-----------|------------|
| Backend Framework | Spring Boot 3.x |
| Database | MongoDB 8.2 |
| Language | Java 20 |
| IDE | IntelliJ IDEA |
| HTTP Client | curl / Postman |
| Build Tool | Maven |

---

## 🚀 How to Run

### Prerequisites
- Java 20 installed
- MongoDB 8.2 running on localhost:27017
- Maven installed

### Steps
1. Clone the repository
2. Start MongoDB service
3. Open project in IntelliJ IDEA
4. Run TaskApiApplication.java
5. Application starts on http://localhost:8080

---

## 🧩 API Endpoints

### 1️⃣ Create a Task

*Endpoint:* PUT /tasks

*Request:*
bash
curl -X PUT "http://localhost:8080/tasks" -H "Content-Type: application/json" -d "{\"id\":\"1\",\"name\":\"Say Hello\",\"owner\":\"siva\",\"command\":\"echo Hello World\"}"


*Response:*
json
{
  "id": "1",
  "name": "Say Hello",
  "owner": "Sivaganga",
  "command": "echo Hello World",
  "taskExecutions": []
}


---

### 2️⃣ Get All Tasks

*Endpoint:* GET /tasks

*Request:*
bash
curl "http://localhost:8080/tasks"


*Response:*
json
[
  {
    "id": "1",
    "name": "Say Hello",
    "owner": "Sivaganga",
    "command": "echo Hello World",
    "taskExecutions": []
  }
]


---

### 3️⃣ Execute a Task

*Endpoint:* PUT /tasks/{id}/execute

*Request:*
bash
curl -X PUT "http://localhost:8080/tasks/1/execute"


*Response:*
json
{
  "id": "1",
  "name": "Say Hello",
  "owner": "sivaganga",
  "command": "echo Hello World",
  "taskExecutions": [
    {
      "startTime": "2025-10-20T10:30:00",
      "endTime": "2025-10-20T10:30:01",
      "output": "Hello World\n"
    }
  ]
}


---

## 🏗 Architecture


┌─────────────────┐
│ TaskController  │  (REST endpoints)
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ TaskRepository  │  (MongoDB operations)
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│    MongoDB      │  (tasksdb.tasks)
└─────────────────┘


*Flow:*
1. Client sends HTTP request to Controller
2. Controller processes request and calls Repository
3. Repository performs CRUD operations on MongoDB
4. Response is returned to client

---

## 🖼 Screenshots

All screenshots include system date/time (visible in taskbar) and username (visible in terminal/IDE).

### Screenshot 1: MongoDB Running
![MongoDB Running](screenshots/01_mongodb_running.png)

Shows MongoDB service running in terminal with username and timestamp visible

---

### Screenshot 2: Application Started in IntelliJ
![Application Started](screenshots/02_app_started.png)

Shows IntelliJ IDEA with application successfully started, console showing "Started TaskApiApplication"

---

### Screenshot 3: Creating a Task (PUT /tasks)
![Create Task](screenshots/03_create_task.png)

Shows curl command execution and JSON response with task creation confirmation

---

### Screenshot 4: Retrieving All Tasks (GET /tasks)
![Get Tasks](screenshots/04_get_tasks.png)

Shows curl command to fetch all tasks and JSON array response

---

### Screenshot 5: Executing a Task (PUT /tasks/1/execute)
![Execute Task](screenshots/05_execute_task.png)

Shows task execution request and response with execution results

---

### Screenshot 6: MongoDB Data Verification
![MongoDB Verification](screenshots/06_mongodb_verification.png)

*Shows mongosh terminal with db.tasks.find().pretty() output displaying stored tasks*

---

## 🗄 Database Schema

*Database:* tasksdb  
*Collection:* tasks

*Document Structure:*
json
{
  "_id": "1",
  "name": "Say Hello",
  "owner": "Sivaganga",
  "command": "echo Hello World",
  "taskExecutions": [
    {
      "startTime": "ISO-8601 timestamp",
      "endTime": "ISO-8601 timestamp",
      "output": "command output or error message"
    }
  ]
}


---

## ✅ Testing Results

| Test Case | Status | Notes |
|-----------|--------|-------|
| Create Task | ✅ Pass | Task successfully created and stored |
| Get All Tasks | ✅ Pass | All tasks retrieved from database |
| Execute Task | ✅ Pass | Task execution recorded with output |
| MongoDB Persistence | ✅ Pass | Data persists after restart |

---

## 🐛 Known Issues

- Command execution on Windows may require full path to executables
- Some commands may fail due to Windows security policies
- API functionality works correctly despite command execution errors

---

## 📝 Project Structure


task-api/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/taskapi/
│       │       ├── TaskApiApplication.java
│       │       ├── Task.java
│       │       ├── TaskExecution.java
│       │       ├── TaskController.java
│       │       └── TaskRepository.java
│       └── resources/
│           └── application.properties
├── screenshots/
│   ├── 01_mongodb_running.png
│   ├── 02_app_started.png
│   ├── 03_create_task.png
│   ├── 04_get_tasks.png
│   ├── 05_execute_task.png
│   └── 06_mongodb_verification.png
├── pom.xml
└── README.md


---

## 🧑‍💻 Author

*Sivaganga KM*  
B.Tech CSE (2026)  
Email: sivagangakm@gmail.com

---

## 📄 License

This project is created for educational purposes as part of Software Effort Estimation coursework.
