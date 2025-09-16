
```
  ____             _                  _ 
 | __ )  __ _  ___| | _____ _ __   __| |
 |  _ \ / _` |/ __| |/ / _ \ '_ \ / _` |
 | |_) | (_| | (__|   <  __/ | | | (_| |
 |____/ \__,_|\___|_|\_\___|_| |_|\__,_|
                                        
     BACKEND API
```


<!-- Title Section -->
<h1 align="center">✨ Kitsu Backend — URL Shortener API ✨</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.1.4-brightgreen?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/REST%20API-json-orange?style=for-the-badge&logo=fastapi" />
  <img src="https://img.shields.io/badge/Docker-ready-0db7ed?style=for-the-badge&logo=docker" />
</p>

<p align="center">
  <i>A fast, minimal & production-friendly backend for shortening URLs — built with love using Spring Boot.</i>
</p>

---

## 🎬 Demo Preview
<p align="center">
  <img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExaWZpYnlwOG1rYjFzaXRnZnB5M21wMHFxYTZ6YWJmNHRvY3k1cDhoMCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/9J7tdYltWyXIY/giphy.gif" width="500" alt="Demo animation"/>
</p>

---

## 🚀 Features at a Glance
- 🔒 **Secure short-code generation** with retries on collision
- 📊 **Stats API** to track click counts
- 🐳 **Dockerized** for cloud or container-native deployments
- ⚡ **Fast setup** with H2 in-memory DB (perfect for demos)
- 🛡️ **Validation ready** with Jakarta annotations

---

## ⚡ Quick Start

```bash
# build & run
mvn clean package -DskipTests
java -jar target/url-shortener-1.0.0.jar
```

Or Docker:

```bash
docker build -t kitsu-backend:latest .
docker run -p 8080:8080 kitsu-backend:latest
```

Visit 👉 `http://localhost:8080`

---

## 📡 API Endpoints
| Method | Endpoint                  | Description                     |
|--------|---------------------------|---------------------------------|
| POST   | `/shorten`                | Create new short URL            |
| GET    | `/shorten/{code}`         | Retrieve metadata               |
| PUT    | `/shorten/{code}`         | Update destination URL          |
| DELETE | `/shorten/{code}`         | Delete short URL                |
| GET    | `/shorten/r/{code}`       | Redirect to long URL            |
| GET    | `/shorten/{code}/stats`   | Stats: count + timestamps       |

---

## 🏗️ Project Layout

```
src/main/java/com/example/urlshortener/
├── controller/       # REST Controllers
├── dto/              # Data Transfer Objects
├── entity/           # JPA Entity
├── repository/       # JPA Repositories
├── service/          # Business Logic
└── util/             # Helpers (SecureRandom ShortCode)
```

---

## 🌱 Future Roadmap
- [ ] 📈 Dashboard UI for analytics
- [ ] 🌍 Postgres + Flyway migrations
- [ ] 🔑 Custom aliases per user
- [ ] 🚦 Rate limiting for API abuse

---

## 🤝 Contribution Guide
PRs welcome! Please:
- Follow code conventions
- Add/extend tests
- Write clear commit messages

---

<h3 align="center">🖤 MIT License | Built for Developers by Developers 🖤</h3>
