# Engralit Website — Setup Guide

## What's in this project
- Spring Boot backend (controller, entities, repository — no service layer yet, keeping it simple)
- Thymeleaf + Bootstrap frontend (no separate frontend build needed)
- MySQL database with 3 tables: `courses`, `syllabus_units`, `course_features`
- Seed data already includes the real Engralit "Target NET/SET English" course

## How to run this (step by step)

### 1. Install prerequisites
- Java 17+ (`java -version` to check)
- MySQL Server running locally
- Maven (usually bundled with IntelliJ/Eclipse)

### 2. Create the database
Open MySQL and run:
```sql
CREATE DATABASE engralit_db;
```
(Or skip this — `application.properties` has `createDatabaseIfNotExist=true` so it auto-creates)

### 3. Set your MySQL password
Open `src/main/resources/application.properties` and replace:
```
spring.datasource.password=YOUR_MYSQL_PASSWORD
```
with your actual MySQL root password.

### 4. Open in IntelliJ / Eclipse / VS Code
Import as a Maven project. Wait for dependencies to download.

### 5. Run
Run `WebsiteApplication.java` (has the `main` method).
Visit: **http://localhost:8080**

You should see the homepage with the Target NET/SET English course card.
Click "View Details" to see the full syllabus page.

## What's already working
- Homepage lists all courses from the database (currently 1 course seeded)
- Course detail page shows all 10 syllabus units + 6 highlights, pulled from DB
- Floating WhatsApp button (links to 7799691771)
- Responsive (works on mobile) — Bootstrap handles this

## What's NOT done yet (next phases)
1. **Login/Signup** — Spring Security setup
2. **Razorpay payment** — the "Enroll Now" button currently just shows an alert
3. **User Dashboard** — "my enrolled courses" after login
4. **Video access control** — restricting lecture videos to paid users
5. **Admin panel** — so the client (or you) can add new courses without touching code

## Adding a new course later (once client wants a 2nd course)
Just insert a new row into `courses` table (plus its `syllabus_units` and `course_features`)
— no code changes needed. The homepage `th:each` loop will automatically show it.

## Adding a real banner image
Drop an image into `src/main/resources/static/images/course-banner.jpg`
(referenced in `data.sql` as thumbnail_url, not yet displayed on the card — you can add
an `<img>` tag using `course.thumbnailUrl` in `index.html` once you have the actual banner from the client)
