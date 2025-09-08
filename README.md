# Job Portal System (Spring Boot + Thymeleaf + MySQL)

## Objective
Allow employers to post jobs and users (applicants) to apply & track their application status.

## Tech
- Java 17, Spring Boot 3, Spring MVC, Spring Security, Spring Data JPA
- Thymeleaf
- MySQL (MariaDB driver)

## Quick Start

1. Create database:
   ```sql
   CREATE DATABASE job_portal DEFAULT CHARACTER SET utf8mb4;
   ```

2. Update DB credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=your_mysql_password
   ```

3. Build & run:
   ```bash
   ./mvnw spring-boot:run
   ```
   or, with Maven installed:
   ```bash
   mvn spring-boot:run
   ```

4. Open http://localhost:8080

## Test Credentials
- Employer: `employer@example.com` / `password`
- Applicant: `applicant@example.com` / `password`

> Note: On first registration the password is encoded with BCrypt. Seeded users use `{noop}password` just for demo. For production, register fresh accounts so passwords are hashed.

## Features
- Registration/login with roles: EMPLOYER, APPLICANT
- Employers: Post & view job listings
- Applicants: Browse, apply to jobs, view "My Applications"
- Search & filter by keyword and location

## DB Schema & Dump
- See `src/main/resources/schema.sql` and `data.sql`.
  - `Users(id, email, password, name, role)`
  - `Jobs(id, title, description, location, company, employer_id)`
  - `Applications(id, status, cover_letter, applicant_id, job_id, applied_at)`

## Notes
- This starter favors clarity over completeness (e.g., no employer admin dashboard yet).
- Add validation, pagination, file uploads (resume), etc. as needed.
