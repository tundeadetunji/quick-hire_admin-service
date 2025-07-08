![CI](https://github.com/tundeadetunji/quick-hire_admin-service/actions/workflows/ci.yml/badge.svg)

# Admin Service of QuickHire+ microservices MVP
View it <a href="https://quick-hire-admin-service.onrender.com/swagger-ui/index.html">Live</a>

<br />
<br />

In this readme:

#### 📬 Messaging
#### 🧪 Testing
#### ⚙️ Concurrency & Transactions
#### 📊 Resilience4j Observability
#### 📘 Pagination

<br />
<br />
<br />

## 📬 Messaging

This service **only listens to messages** via RabbitMQ and logs them in memory.

- It listens to `admin.notify` queue using `@RabbitListener`.
- Received messages are stored in a temporary in-memory list for testing/demo visibility.

You can manage message logs via:

- `GET /admin/messages` – List all received messages
- `DELETE /admin/messages` – Clear the in-memory log

Messages flow here from both:

- `recruiter-service` (e.g., job created/updated)
- `candidate-service` (indirectly via recruiter-service forwarding)

<br />
<br />
<br />

## 🧪 Testing

The admin service is tested using **JUnit 5** and Spring’s `@WebMvcTest` for controller endpoints.

CI runs via GitHub Actions on every push to `master`.

### ✅ What’s Covered

- Retrieving all messages (`GET /admin/messages`)
- Clearing messages (`DELETE /admin/messages`)

<br />
<br />
<br />

## ⚙️ Concurrency & Transactions

This service operates passively by listening to RabbitMQ and storing messages in memory. It **does not use `@Transactional` methods or database persistence**.

Because it doesn’t modify shared data or persist state, concurrency is not a concern here. Messages are stored in a simple in-memory list and can be retrieved or cleared via REST endpoints.

<br />
<br />
<br />

## 📊 Resilience4j Observability

This service uses **Resilience4j** to handle transient failures with:

- Circuit Breakers
- Retry Policies
- Rate Limiting

You can observe real-time Resilience4j metrics using the built-in Spring Boot Actuator endpoints:

- `/actuator/resilience4j.circuitbreakers`
- `/actuator/resilience4j.retries`
- `/actuator/resilience4j.ratelimiters`

> ⚠️ These endpoints are **internal** and not exposed publicly on Render.

### 🧪 Local Testing

If testing locally, ensure:

- You provide valid environment variables **or**
- You temporarily switch to an in-memory H2 database (e.g. via `application-local.yml` or `.env.local`)

This allows the app to boot and actuator endpoints to respond.

<br />
<br />
<br />

## 📘 Pagination

Pagination is not applicable in this service.

Messages are stored in a temporary in-memory list (not persisted, for brevity sake in this mock), and the `/admin/messages` endpoint returns the full list.
