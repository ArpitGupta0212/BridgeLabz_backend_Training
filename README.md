# Greeting Servlet Application (With Login)

A Java-based web application that provides secure user authentication and personalized greeting management using **Java Servlets**, **JSP**, **Spring Framework**, **JDBC**, and **PostgreSQL**.

This project demonstrates authentication, authorization, database connectivity, and the MVC architecture in a real-world web application.

---

## Project Overview

The application allows users to register, log in securely, and access greeting services. Passwords are securely stored using hashing techniques, and authentication is enforced through servlet filters.

---

## Features

- User Registration
- Secure User Login
- Password Hashing
- Authentication Filter
- Greeting Management
- JDBC Database Connectivity
- PostgreSQL Integration
- Spring Dependency Injection
- MVC Architecture
- Session Management

---

## Technologies Used

- Java
- Servlets
- JSP
- Spring Framework
- JDBC
- PostgreSQL
- Apache Tomcat
- HTML5
- CSS3

---

## Project Structure

```text
GreetingServletAppWithLogin
│
├── resources
│   └── schema.sql
│
├── src
│   └── com
│       └── greet
│           ├── filter
│           │   └── AuthFilter.java
│           │
│           ├── model
│           │   ├── Greeting.java
│           │   └── User.java
│           │
│           ├── repository
│           │   ├── GreetingRepository.java
│           │   ├── GreetingRepositoryImpl.java
│           │   ├── UserRepository.java
│           │   └── UserRepositoryImpl.java
│           │
│           ├── service
│           │   ├── GreetingService.java
│           │   ├── GreetingServiceImpl.java
│           │   ├── UserService.java
│           │   └── UserServiceImpl.java
│           │
│           ├── servlet
│           │   ├── AuthServlet.java
│           │   ├── GreetingServlet.java
│           │
│           └── util
│               └── HashUtil.java
│
├── WebContent
│   └── web
│       └── WEB-INF
│           └── web.xml
│
├── web
│   └── WEB-INF
│       └── images
│
└── GreetingServletAppWithLogin.iml
```

---

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/ArpitGupta0212/BridgeLabz_backend_Training.git
```

### 2. Switch to the Project Branch

```bash
git checkout Greeting_Servlet_Application2
```

### 3. Open the Project

Import the project into IntelliJ IDEA.

### 4. Configure Database

- Install PostgreSQL.
- Create a new database.
- Execute the `schema.sql` file.
- Update database credentials in the project.

### 5. Configure Apache Tomcat

- Add Apache Tomcat to IntelliJ IDEA.
- Deploy the WAR artifact.

### 6. Run the Application

Start Tomcat and open the application in your browser.

---

## Learning Outcomes

During this project, I gained hands-on experience with:

- Java Servlet Development
- Spring Framework
- JDBC
- PostgreSQL
- Authentication & Authorization
- Session Management
- Password Hashing
- MVC Architecture
- Repository Layer
- Service Layer
- Servlet Filters

---

## Author

### Arpit Gupta

**Bachelor of Technology (B.Tech)**  
**Computer Science & Engineering (CSE)**  
**GLA University, Mathura**

**Skills**

- Java
- Spring Framework
- Servlets
- JSP
- JDBC
- PostgreSQL
- MVC Architecture
- Git & GitHub

---

## License

This project is developed for educational purposes as part of the **BridgeLabz Backend Training Program**.

---

⭐ Thank you for visiting this repository. Feel free to explore the project and provide your feedback.
