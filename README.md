# Subscription Management Service POC

## Overview
This is a Spring Boot service that provides a Proof of Concept (POC) for a Subscription Management System. The service handles the core subscription operations, including:

- **Product Creation**: Ability to create and manage subscription products.
- **User Registration and Authentication**: Secure user registration and authentication using JWT tokens.
- **Subscription Management**: Perform CRUD operations on user subscriptions.
- **Webhook Notification Handlers**: Receive and handle subscription-related events (creation, renewal, cancellation).
- **Storage Plan Management**: Manage storage plans associated with subscription products.
- **RESTful APIs**: Provide a set of RESTful APIs for all the above functionalities.

The project follows a modular design with separate components for handling user management, subscription management, product management, and webhook notification processing. It also includes comprehensive API documentation using Swagger.


## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Built With](#built-with)

## Prerequisites
1. **Java**: Make sure you have Java Development Kit (JDK) installed. This project was developed using Java 21.
2. **Database**: The application uses PostgreSQL as the database. (Compatible with MySQL, just modify the dependencies in `pom.xml` and `application.properties`).

## Installation
1. Clone the repository:

	git clone https://github.com/SachinKSuresh/IAPDemoPOC 

	cd IAPDemoPOC

2. Build the project using Maven:

	./mvnw clean install

## Configuration
1. Database Configuration:
- Edit the `src/main/resources/application.properties` file to configure the database connection.
- Example properties:

  
	  spring.datasource.url=jdbc:postgresql://localhost:5432/IAPSubscriptionManagementDemo
	  spring.datasource.username=abcd // Replace with your username
	  spring.datasource.password=**** // Replace with your password
	  spring.jpa.hibernate.ddl-auto=update
	  spring.jpa.show-sql=false
  

## Running the Application
1. Run the application as a Spring Boot app:

	./mvnw spring-boot:run

2. Once the application is running, it will be accessible at `http://localhost:8080`.

## API Documentation
The Swagger UI is available at `http://localhost:8080/swagger-ui/index.html`.

## Built With
The primary technologies, frameworks, and libraries used in this project are:
- **Spring Boot** - Java framework for building web applications
- **Maven** - Dependency management
- **PostgreSQL** - Database
- **Swagger** - API documentation
- **JWT** - Authentication and Authorization
- **Webhook** - Notification Handlers


