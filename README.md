# Event Management System

## Overview
This is an Event Management System that allows users to register, log in, and create events. Events can be viewed publicly by anyone. The backend is built using Java Spring Boot, and the frontend is developed with React. Authentication is handled using JWT tokens, which are stored in the local storage of the client's browser.

## Technologies Used
- **Backend**: Java, Spring Boot
- **Frontend**: React
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Tokens)

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Apache Maven
- Node.js and npm
- PostgreSQL

### Backend Setup

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd event-management-backend

Configure Database:

Ensure PostgreSQL is running.
Create a database named event_management.
Update the application.properties file in the src/main/resources directory with your PostgreSQL database credentials.
properties.

spring.datasource.url=jdbc:postgresql://localhost:5432/event_management
spring.datasource.username=your_username
spring.datasource.password=your_password


Install Dependencies:

bash
mvn install
Run the Spring Boot Application:

bash
mvn spring-boot:run
The backend will run on http://localhost:8080.

Frontend Setup
Navigate to the Frontend Directory:

bash

cd event-management-frontend
Install Dependencies:

npm install
Run the React Application:

npm start

The frontend will run on http://localhost:3000.
Authentication
User Registration: Users must register to create events. Registration requires a valid email and password.
JWT Token: Upon successful login, a JWT token will be generated and must be stored in local storage on the client side for subsequent authenticated requests.
API Endpoints
Public Endpoints:
GET /events: View all public events.
Protected Endpoints:
POST /events/** Create a new event (requires JWT token).
User Authentication:
POST /auth/register: Register a new user.
POST /auth/login: Log in and retrieve JWT token.
Local Storage
The JWT token is stored in the browser's local storage for persistent authentication. Ensure that it is retrieved and included in the headers for protected routes.
License
This project is licensed under the MIT License. See the LICENSE file for more information.

Acknowledgments
Spring Boot Documentation
React Documentation
PostgreSQL Documentation
