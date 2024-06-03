# Neosurge Investment Insight Backend App

## Overview

This project is part of the Neosurge Investment Insight Backend App Development Challenge. The objective of this assignment is to develop a robust backend system that integrates the GPT-4 API to generate investment insights for the Indian capital market. The system is built using Java, Spring Boot, and RESTful APIs, with MongoDB for database management.

## Features

1. **Insert Company Data**: API to insert company data and generate investment insights using GenAI.
2. **Compare Companies**: API to compare multiple companies based on provided criteria given in the prompt.

## Technologies Used

- **Java**: The main programming language used for the backend logic.
- **Spring Boot**: Framework for building the backend application.
- **GPT-4 API**: Used for generating insights and processing financial data.
- **MySql**: Database used for storing user preferences and generated insights.
- **REST API**: For handling user input and interaction between frontend and backend.

## API Endpoints

### 1. Insert Company Data
- **URL**: `/api/company/insert-company-data`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "companyName": "string",
    "data": "string",
    "prompt": "string"
  }
  ```
- **Response**:
  ```json
  {
    "response": "string",
    "details": "string"
  }
  ```

### 2. Compare Companies
- **URL**: `/api/company/compare`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "companyA": "string",
    "companyB": "string",
    "criteria": "string"
  }
  ```
- **Response**:
  ```json
  {
    "comparisonResult": "string",
    "details": "string"
  }
  ```

## Project Structure

- `controller`: Contains the `CompanyController` class, which handles the API requests.
- `pojo.request`: Contains request classes like `CompanyDataInsertionForm`, `ComparsionForm`, and `AIPrompt`.
- `pojo.response`: Contains response classes like `GenAIResponse` and `ComparisionResponse`.
- `service`: Contains service classes `ChatGPTService` and `CompanyService` for business logic.

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Maven
- MySql

### Steps

1. **Clone the repository**:
   ```bash
   git clone <repository_url>
   ```
2. **Navigate to the project directory**:
   ```bash
   cd neosurge-investment-insight-backend
   ```
3. **Build the project**:
   ```bash
   mvn clean install
   ```
4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

## Design Choices

1. **Spring Boot**: Chosen for its simplicity and rapid development capabilities.
2. **MongoDB**: Selected for its flexibility in handling unstructured data.
3. **GPT-4 Integration**: Used to leverage advanced AI capabilities for generating financial insights.

   
Please find the GitHub repository [here](https://github.com/cs-mshr/Neosurge-Investment-Insight-challenge).

---
