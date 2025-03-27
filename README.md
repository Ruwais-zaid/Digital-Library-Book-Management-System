# Library Management System

## 📌 Project Overview
The **Library Management System** is a web-based application that allows users to manage books, members, and transactions efficiently. It provides functionalities such as adding, updating, deleting, and searching for books and members. The system ensures smooth borrowing and returning processes while maintaining proper records.

---
## 🚀 Tech Stack
### **Backend:**
- Java (Spring Boot)
- Hibernate (ORM)
- PostgreSQL (Database)
- Maven (Build Tool)



### **Infrastructure & Deployment:**
- Docker & Docker Compose
- Render (Hosting)
- GitHub Actions (CI/CD)

---
## 🔧 Setup & Installation
### **1️⃣ Clone the Repository**
```sh
  git clone https://github.com/Ruwais-zaid/library-management.git
  cd library-management
```

### **2️⃣ Setup the Database (PostgreSQL)**
Create a database named `library_db` and configure the connection in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### **3️⃣ Build and Run**
```sh
  mvn clean install
  mvn spring-boot:run
```

### **4️⃣ Running with Docker**
```sh
  docker-compose up --build
```

---
## 🧪 Running Unit Tests
Unit tests are written using JUnit and Mockito.
### **Run tests using Maven**
```sh
  mvn test
```

### **Run specific test class**
```sh
  mvn -Dtest=BookServiceTest test
```

---
## ⚠️ Challenges Faced
1. **Database Connection Issues:** Faced errors while connecting PostgreSQL on Render. Fixed by properly setting up environment variables.
2. **Docker Build Errors:** `COPY --from=build` failed due to missing files. Resolved by ensuring the correct target directory.
3. **Pagination & Sorting in APIs:** Implemented efficient pagination to handle large datasets.

---
## 🌟 Future Improvements
- Implement Role-Based Access Control (RBAC)
- Add GraphQL API for flexible data queries
- Enhance UI with better UX features
- Integrate Elasticsearch for fast search functionality

---
## 🤝 Contribution
1. Fork the repository.
2. Create a new branch: `git checkout -b feature-branch`
3. Commit changes: `git commit -m 'Added a new feature'`
4. Push to the branch: `git push origin feature-branch`
5. Open a Pull Request.

---
## 📜 License
This project is licensed under the **MIT License**.

---
## 📞 Contact
- **Author:** Zaid Nasim
- **Email:** xxxyz96989@gmail.com
- **GitHub:** [Ruwais-zaid](https://github.com/Ruwais-zaid)
