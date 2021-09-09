# ACME Learning APP

ACME Learning is an online education platform that helps Instructors to create Courses and make them available for Students to enroll.

## Installation and Running application

- Clone the application
- Implement a maven package command
- In target folder ACME-v1.0.jar will be created
- Open console and type java -java ACME-v1.0.jar and press enter

## Postman collection

- Once the application is in your local go to the Postman folder in app root
- Import to postman the file ACME.postman_collection.json

## Usage

ACME learning app is divided in 4 service sections:
- SignUp: This is the first place to go, you have to register as an Instructor user role or as a Student role.
- Students: These are the services available for student roles
- Instructors: These are the services relate dto instructors
- Courses: These are the services to manage courses in the application

**Roles:**

ACME learning app has 2 roles:

- STD: This role is set for students and only gives access to Students services
- INSTR: This is instructor role and have access to all services in the application

**Services:**

SignUp

First you have to signup to the application using the register service. If you don't signup you wont be able to use any of the services

Students

As a student you will be able to manage all related to students, see all courses, enroll and drop courses.
You cannot enroll to a course that is canceled or already started

Instructor

As an instructor you will be able to manage all related to instructors and courses, will be able to see all courses, the courses you created
start a course or cancel a course and see which students are enrolled to a course