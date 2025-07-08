![CI](https://github.com/tundeadetunji/quick-hire_admin-service/actions/workflows/ci.yml/badge.svg)

## 📬 Messaging and Observability
## 🧪 Testing
## ⚙️ Concurrency & Transactions
## 📘 Pagination



## 📬 Messaging and Observability

This service **only listens to messages** on RabbitMQ and logs them in memory.

- It listens to queues like `adminQueue` using `@RabbitListener`.
- Received messages are stored in a temporary in-memory list.

You can view or clear the message log via:

- `GET /admin/messages` – list all received messages.
- `DELETE /admin/messages` – clear the log (useful for testing).

All messages from `recruiter-service` flow through here.

## 🧪 Testing

The admin service is tested using **JUnit 5** and Spring’s `@WebMvcTest` for controller endpoints.

CI runs via GitHub Actions on every push to `master`.

### ✅ What’s Covered

- Retrieving all messages (`GET /admin/messages`)
- Clearing messages (`DELETE /admin/messages`)

## ⚙️ Concurrency & Transactions

This service operates passively by listening to RabbitMQ and storing messages in memory. It **does not use `@Transactional` methods or database persistence**.

Because it doesn’t modify shared data or persist state, concurrency is not a concern here. Messages are stored in a simple in-memory list and can be retrieved or cleared via REST endpoints.

## 📘 Pagination

Pagination is not applicable in this service.

Messages are stored in a temporary in-memory list (not persisted, for brevity sake in this mock), and the `/admin/messages` endpoint returns the full list.
