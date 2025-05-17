# 🎓 Student Management System

## 📌 Project Overview

The **Student Management System** is a sample web-based application developed as part of the *Object-Oriented Programming* course assignment. It allows users to manage student records through CRUD operations (Create, Read, Update, Delete) using **file-based storage (JSON)** instead of a traditional database.

This application is built using:

- Java
- Spring Boot
- JSP
- HTML/CSS (with Bootstrap)
- JavaScript

It adheres to core **Object-Oriented Programming (OOP)** principles: **encapsulation**, **inheritance**, and **polymorphism**.

---

## ✅ Assignment Objectives Fulfilled

- User-friendly interface with at least **three UIs**
- File read/write operations for **data persistence**
- OOP in layered architecture (**Entity, Repository, Service, Controller**)
- **GitHub** version control

---

## 🚀 Features

- **Create:** Register a new student with first name, last name, and email
- **Read:** View all students or fetch by ID
- **Update:** Modify existing student details
- **Delete:** Remove a student from the system
- **Responsive UI:** Built using **JSP** and **Bootstrap**
- **File Storage:** Data stored in `students.json`
- **OOP Implementation:**  
  - *Encapsulation:* private fields, getters/setters  
  - *Inheritance:* service implementation  
  - *Polymorphism:* interface-based service layer

---

## 📁 Project Structure
![image](https://github.com/user-attachments/assets/3e4b5bd5-3df7-40d0-8907-ade9094ba452)



markdown
Copy
Edit

---

## 🧰 Technologies Used

- **Java:** 17
- **Spring Boot:** 3.1.0 (MVC, DI)
- **JSP:** For dynamic views
- **Bootstrap:** 5.3.0
- **JavaScript:** For interactivity (e.g., delete confirmations)
- **Jackson:** For JSON handling
- **Lombok:** Reduce boilerplate
- **Maven:** Dependency management
- **IntelliJ IDEA**
- **Git & GitHub**

---

## ⚙️ Setup Instructions

### 1. Clone the Repository


- git clone [<your-github-repo-url>](https://github.com/Mithun-Dilshan/Student-managment-system.git)
cd student-management
### 2. Open in IntelliJ IDEA
- File > Open > Select student-management folder

- Allow Maven to auto-import

### 3. Verify Dependencies
bash
 - mvn clean install
### 4. Configure application.properties
- properties


### File storage path


app.data.file=classpath:data/students.json

### JSP view resolver
- spring.mvc.view.prefix=/WEB-INF/views/
- spring.mvc.view.suffix=.jsp
### 5. Initialize Data File
Ensure src/main/resources/data/students.json contains:

json
[]
### 6. Run the Application

 - mvn spring-boot:run
- Visit: http://localhost:8090/students

---

## 🧪 Usage Guide


- Create: Click “Add New Student” and enter details

- Read: View the student list

- Update: Click “Edit,” modify fields, and save

- Delete: Click “Delete” and confirm

### Sample Data (students.json)
json
Copy
Edit
[
  { "id": 1, "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com" },
  { "id": 2, "firstName": "Jane", "lastName": "Smith", "email": "jane.smith@example.com" }
]

---


## 🔍 OOP Concepts Implemented



- Encapsulation: Private fields + Lombok-generated getters/setters

- Inheritance: StudentServiceImpl implements StudentService

- Polymorphism: Interface-based service logic

- Class & Object: Student.java defines class; JSON entries are instances

---


## 📄 Deliverables


- Source Code

- Java Classes: Student.java, StudentRepository.java, StudentService.java, etc.

- JSP Files: student-list.jsp, create-student.jsp, update-student.jsp

- Config: application.properties, students.json









---



## 🛠️ Troubleshooting


- JSP 404 Errors
- Ensure JSP files are in: src/main/webapp/WEB-INF/views/

- Add tomcat-embed-jasper & jstl in pom.xml

- Set src/main/webapp as web resource in IntelliJ

- File Access Issues
- Ensure students.json exists and is writable

- JSTL Errors
- Verify JSTL taglib URIs and dependencies

- Browser Caching
- Use incognito or clear cache

- 🐞 Known Issues
- ❌ JSP Not Found
Fix:

- Moved JSP files to correct directory

- Added tomcat-embed-jasper

- Commit: "Fixed JSP 404 error"


---


## 📂 GitHub Version Control


Repository: (https://github.com/Mithun-Dilshan/Student-managment-system.git)
View history:

bash
Copy
Edit
git log --oneline


- git init  (initialize a new Git repository)
- 
- git add . (add updated code)
- git commit -m "Youre commit"
- git push 

---


## 📜 License


This project is licensed under the MIT License. See the LICENSE file for details.


---

## 📬 Contact

For questions or support, contact the team via [Insert your contact email or open GitHub issues].

yaml
Copy
Edit

---

Let me know if you'd like to automatically fill in your GitHub URL, author name, or contact info in the above Markdown!






