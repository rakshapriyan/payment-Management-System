# 💰 Payments Management System 
This is a secure Spring Boot REST API system for managing internal payment workflows within a fintech startup. It supports user management, payment categorization, and traceable payment status updates, along with unit-tested service and controller layers.

## 🧰 Features

### ✅ User Management
- **Create** and **list** users
- User roles: `ADMIN`, `FINANCE_MANAGER`, `VIEWER`

### 💸 Payment Management
- Create, retrieve, update, delete payments
- Fields: amount, type (INCOMING/OUTGOING), category (SALARY/VENDOR/INVOICE), status (PENDING/PROCESSING/COMPLETED)
- Payments associated with a user (`createdBy`)

---

## 🗃️ Entities

### User
| Field    | Type     | Description                    |
|----------|----------|--------------------------------|
| id       | Long     | Primary key                    |
| name     | String   | Full name                      |
| email    | String   | Unique email                   |
| role     | Enum     | `ADMIN`, `FINANCE_MANAGER`, `VIEWER` |

### Payment
| Field        | Type     | Description                          |
|--------------|----------|--------------------------------------|
| id           | Long     | Primary key                          |
| amount       | BigDecimal | Payment amount                    |
| paymentType  | Enum     | `INCOMING`, `OUTGOING`               |
| category     | Enum     | `SALARY`, `VENDOR`, `INVOICE`        |
| status       | Enum     | `PENDING`, `PROCESSING`, `COMPLETED` |
| date         | LocalDate| Payment date                         |
| createdBy    | Long     | FK to User.id                        |

---

## 🚀 API Endpoints

### User APIs
- `POST /users` – Create a new user
- `GET /users` – Get all users

### Payment APIs
- `POST /payments` – Create new payment
- `GET /payments` – List all payments
- `GET /payments/{id}` – Get payment by ID
- `PUT /payments/{id}` – Update payment
- `DELETE /payments/{id}` – Delete payment

---

## 🧪 Unit Tests

### ✅ PaymentServiceTest

### ✅ PaymentControllerTest


---

## ⚙️ Tech Stack

- Java 
- Spring Boot 
- Spring Data JPA 
- JUnit , Mockito

---
