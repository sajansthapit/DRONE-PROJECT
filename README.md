# Drone Project (Java Spring Boot)

---

## GETTING STARTED

---
A service via REST API that allows clients to communicate with the drones.

These instructions will get you a copy of the project up and running in your local machine

### Technologies Used
- Java 11
- Spring Boot (2.7.12)
- Maven
- Postgresql Database
- Docker

---

### Installation Using Docker
```sh
 $ git clone https://github.com/sajansthapit/DRONE-PROJECT
 $ cd drone-project
 $ mvn clean
 $ mvn package
 $ docker compose up -d
```

**Ports Used**
```
Database (Postgres): 5432
PgAdmin: 15432
ServiceDiscovery: 8761
API-Gateway: 9090
Client: 8080
Medication: 8081
Drone: 8082
Shipment: 8083
Notification: 8084
RabitMQ: 5672, 15672
```

---