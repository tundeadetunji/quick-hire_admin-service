![CI](https://github.com/tundeadetunji/quick-hire_admin-service/actions/workflows/ci.yml/badge.svg)

# 🛡️ Admin Service – QuickHire+ Microservices MVP  
With QuickHire+, system-wide notifications are logged and viewed here for observability and demo purposes.

📄 API Docs: View <a href="https://quick-hire-admin-service.onrender.com/swagger-ui/index.html">Swagger UI</a>

<br/>
<br/>

```  
+-------------------+       RabbitMQ        +-------------------+
|  Candidate Service|  ───────────────▶     |  Recruiter Service|
|                   |       🔔 Notify       |                   |
| - Apply to jobs   |◀───────────────       | - Manage Jobs     |
+-------------------+                      ◀| - Notify Admin    |
                                            +-------------------+
                                                   │
                                                   ▼
                                       +------------------------+
                                       |    Admin Service       |
                                       | - Logs notifications   |
                                       | - In-memory store      |
                                       +------------------------+
```

---

🧰 Tech Stack:
- ☕ Java 17
- 🌱 Spring Boot 3.x
- 📬 RabbitMQ (Messaging)
- 🛡️ Resilience4j (Observability)
- 🧪 JUnit 5, 🎭 Mockito (Testing)
- 🗄️ In-memory store (no persistent DB)
- ⚙️ CI/CD via GitHub Actions

---

📬 Messaging  
This service **only listens** to RabbitMQ messages on the `admin.notify` queue.

- Logs received notifications in-memory  
- Receives messages forwarded from `recruiter-service`, including those triggered by candidate actions

🔍 Endpoints:
- `GET /admin/messages` – View messages  
- `DELETE /admin/messages` – Clear all messages

---

🧪 Testing  
Uses JUnit 5 and Spring’s `@WebMvcTest`.  
CI powered by GitHub Actions.

✅ What’s Covered
- GET /admin/messages  
- DELETE /admin/messages

---

⚙️ Concurrency & Transactions  
This service is passive:
- No @Transactional methods  
- No database persistence  
- Uses a thread-safe in-memory store

---

📊 Resilience4j Observability  
Supports:

✅ Circuit Breakers  
🔁 Retry Policies  
⏱️ Rate Limiting  

Actuator endpoints (not exposed publicly):
- /actuator/resilience4j.circuitbreakers  
- /actuator/resilience4j.retries  
- /actuator/resilience4j.ratelimiters

🧪 To test locally:
- Provide valid environment variables  
- Or switch to H2 using `.env.local` / `application-local.yml`

---

📘 Pagination  
Not applicable — all messages are returned at once from memory.
