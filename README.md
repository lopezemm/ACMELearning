# ACME Learning APP

ACME Learning is an online education platform that helps Instructors to create Courses and make them available for Students to enroll.

## Installation and Running application

- Clone the application
- Implement mvn clean package command
- In target folder ACME-v1.0.jar will be created
- Open Windows explorer and navigate to target (..cloned_path\acme\target) in address bar type cmd and press enter
- A command window will open, there type java -jar ACME-v1.0.jar and press enter
- Application must start

## Postman collection

- Once the application is in your local go to the Postman folder in app root
- Import to postman the file ACME.postman_collection.json

## Usage

ACME learning app is divided in 4 service sections:
- SignUp: This is the first place to go, you have to register as an Instructor user role or as a Student role.
- Students: These are the services available for student roles
- Instructors: These are the services related to instructors
- Courses: These are the services to manage courses in the application

**Roles:**

ACME learning app has 2 roles:

- STD: This role is set for students and only gives access to Students services
- INSTR: This is instructor role and have access to all services in the application

**Courses:**

ACME learning app handles 3 different status for courses, that you have to provide when creating your courses.
These statuses indicate if a course is _**N**ot started_, _**S**arted_ or _**C**ancelled_:

- N: Stands for NotStarted
- S: Stands for Started 
- C: Stands for Cancelled 

Remember when creating a new course set the course status to those values any not recognized values will set the course as N
by default. 

**Services:**

SignUp

First you have to signup to the application using the register service. If you don't signup you wont be able to use any of the services.
After signup you have to use basic authentication for all your request using your username and password


Students

As a student you will be able to manage all related to students, see all courses, enroll and drop courses.
You cannot enroll to a course that is canceled or already started

Instructor

As an instructor you will be able to manage all related to instructors and courses, will be able to see all courses, the courses you created
start a course or cancel a course and see which students are enrolled to a course

