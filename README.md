# Spring Boot + Thymeleaf + HTMX + Font Awesome Demo

This is a demo project showcasing how to use Spring Boot with Thymeleaf templates enhanced by HTMX for dynamic UI interactions and Font Awesome for icons.

## Features

- Spring Boot 3.2.3 with Java 17
- Thymeleaf as the templating engine
- HTMX for dynamic UI updates without writing JavaScript
- Font Awesome for beautiful icons
- Bootstrap for responsive design
- PostgreSQL for database storage
- Flyway for database migrations
- Task Manager application with database persistence
- Product Manager application with PostgreSQL storage and external API integration
- Scheduled job to fetch product data from an external API (runs immediately at startup)

## Prerequisites

- Java 17 or higher
- Gradle (or use the included Gradle wrapper)
- PostgreSQL database

## Database Setup

Before running the application, make sure PostgreSQL is installed and running. Then create a database:

```sql
CREATE DATABASE productdb;
```

## Running the Application

You can run the application using the following command:

```bash
# Using Gradle wrapper
./gradlew bootRun
```

Or on Windows:

```bash
gradlew.bat bootRun
```

The application will be available at http://localhost:8080

## Project Structure

- `src/main/java/com/example/demo/DemoApplication.java` - Spring Boot application entry point
- `src/main/java/com/example/demo/controller/` - Controllers for handling requests
- `src/main/java/com/example/demo/model/` - Entity models
- `src/main/java/com/example/demo/service/` - Business logic services
- `src/main/java/com/example/demo/repository/` - Data access repositories
- `src/main/java/com/example/demo/dto/` - Data transfer objects
- `src/main/java/com/example/demo/config/` - Configuration classes
- `src/main/resources/templates/` - Thymeleaf templates
- `src/main/resources/templates/fragments/` - Reusable Thymeleaf fragments

## Main Features

### Task Manager
- Create, read, update, and delete tasks
- Mark tasks as completed
- Dynamic UI updates with HTMX

### Product Manager
- Fetch products from an external API (https://famme.no/products.json)
- Store products in PostgreSQL database
- Display products in a responsive grid
- Add new products manually
- JSONB column for storing product variants

## Technologies

- **Spring Boot**: Framework for creating stand-alone, production-grade Spring applications
- **Thymeleaf**: Server-side Java template engine
- **HTMX**: Lightweight JavaScript library that allows you to access AJAX directly in HTML
- **Font Awesome**: Icon library and toolkit
- **Bootstrap**: Front-end framework for responsive design
- **PostgreSQL**: Advanced open source relational database
- **Flyway**: Database migration tool for version control of your database schema
- **Spring Data JPA**: Simplifies data access with JPA
- **Spring WebFlux**: For reactive web client to fetch external API data
- **Jackson**: JSON processing library
- **Spring Scheduling**: For scheduled tasks that run at specified intervals
