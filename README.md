# Multi-Factor Authentication System (Java)

A modular multi-factor authentication system built in Java, designed to simulate real-world secure login flows using Microsoft Authenticator (TOTP) and email-based verification.

## Features
- Password-based login
- Microsoft Authenticator (TOTP) verification
- Email-based verification
- Modular authentication architecture
- GUI-based authentication flow
- Unit testing with JUnit
- Mutation testing with PIT

## Project Overview
This project was developed as part of a Software Systems Design and Testing module. The aim was to design, implement and test a secure authentication system with a strong focus on scalability, maintainability and software quality.

The system was built using a modular design so that new authentication methods can be added with minimal changes to the existing codebase. It also separates the user interface, authentication logic and user data handling to support cleaner architecture and easier maintenance.

## Technologies Used
- Java
- Maven
- JUnit
- PIT Mutation Testing
- Microsoft Authenticator (TOTP)
- Email verification

## Engineering Concepts Applied
- SOLID principles
- Design patterns
- Separation of concerns
- Unit testing
- Mutation testing

## Repository Structure
- `src/` – source code
- `pom.xml` – Maven configuration
- `mfa-authentication-system-report.pdf` – project report and documentation

## How to Run
1. Clone the repository
2. Open the project in NetBeans or another Java IDE
3. Ensure Maven dependencies are installed
4. Run the main application file

## Notes
This repository contains the source code and supporting documentation for the project. Build output folders such as `target/` have been excluded to keep the repository clean.
