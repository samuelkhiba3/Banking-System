# Banking System

## Overview

Banking system(Simple) built with Spring Boot, React, and MySQL. It includes user and account management, secured with JWT authentication. 

## Features

- User Registration and Authentication
- Account Creation and Management
- JWT-based Authentication
- Secure Endpoints with Spring Security
- DTO Mapping for Clean Code
- Utility Classes for Business Logic

## Technologies Used

- Spring Boot
- Spring Security
- JWT
- MySQL
- Lombok
- Maven

## Getting Started

### Prerequisites

- Java 11+
- Maven
- MySQL
- Postman (Optional)

### Installation

1. Clone the repository:
   git clone https://github.com/samuelkhiba3/banking-system.git
   
2. Navigate to the project directory:
   cd banking-system
   
3. Update MySQL configuration in src/main/resources/application.properties:
    spring.datasource.url=jdbc:mysql://localhost:3306/your_schema_name
    spring.datasource.username=root
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
  
5. Create the database schema:
    CREATE DATABASE banking_system;
  
6. Build the project:
    mvn clean install
   
7.Run the application:
    mvn spring-boot:run
   
-API Endpoints- 
Postman(Optional)
User Registration
  URL: /api/auth
  Method: POST
  Request Body:
    {
      "firstName": "John",
      "lastName": "Doe",
      "email": "samuelkhiba3@gmail.com",
      "password": "password",
      "role": "USER"
   }
   
  Response:
  {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "samuelkhiba3@gmail.com",
      "role": "USER"
  }

User Authentication
  URL: /api/auth
  Method: POST
  Request Body:
    {
      "username": "samuelkhiba3@gmail.com",
      "password": "password"
    }

  Response:
    {
      "jwt": "your_generated_jwt_token"
    }

Create Account
  URL: /api/accounts
  Method: POST
  Request Header: Authorization: Bearer your_jwt_token
  Request Body:
    {
      "userId": 1,
      "initialDeposit": 1000
    }

  Response:
    {
      "id": 1,
      "accountNumber": "1234567890",
      "balance": 1000,
      "createdDate": "2023-07-12T00:00:00",
      "updatedDate": "2023-07-12T00:00:00"
    }

##Caution
-Make sure to update any placeholder text (like `your-password`) 
-This project is under-development, more fetures to be added.

!!Have wonderful day!!


