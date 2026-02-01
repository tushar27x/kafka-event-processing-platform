# Kafka Event Processing Platform with Observability

A production-style **Kafka-based event processing system** built with Spring Boot, PostgreSQL, Redis, Prometheus, and Grafana.

The project demonstrates **event-driven architecture**, **reliable consumer design**, and **end-to-end observability**, including **Kafka consumer lag monitoring**.

---

## ✨ Key Highlights

- REST-based Kafka producer

- Idempotent Kafka consumer with Redis

- PostgreSQL-backed aggregations

- Dead Letter Queue (DLQ) handling

- Prometheus metrics for applications and Kafka

- Grafana dashboards for real-time visibility

- Kafka consumer lag monitoring using Kafka Exporter


---

## 🏗 Architecture Overview

```
Producer Service (Spring Boot)
        |
        | REST API
        v
Kafka Topic (user.activity.events)
        |
        v
Consumer Service (Spring Boot)
        ├── Redis (deduplication / idempotency)
        ├── PostgreSQL (aggregated storage)
        └── DLQ (failed events)
        |
        v
Prometheus (metrics scraping)
        |
        v
Grafana (dashboards & alerts)

```

---

## 🧩 Services

|Service|Description|
|---|---|
|**Producer**|Accepts REST events and publishes to Kafka|
|**Consumer**|Consumes Kafka events and performs processing|
|**Kafka**|Event streaming backbone|
|**Redis**|Idempotency & duplicate detection|
|**PostgreSQL**|Aggregated event storage|
|**Prometheus**|Metrics collection|
|**Grafana**|Metrics visualization|
|**Kafka Exporter**|Kafka consumer lag & broker metrics|

---

## 📊 Observability & Metrics

### Application Metrics (Spring Boot)

|Metric|Description|
|---|---|
|`events_processed_total`|Successfully processed events|
|`events_failed_total`|Failed events|
|`events_duplicate_total`|Duplicate events detected|
|`events_dlq_total`|Events sent to DLQ|

### Kafka Metrics

- Consumer group lag

- Topic partition offsets

- Consumer group members

- Broker availability


---

## 📈 Kafka Consumer Lag (PromQL)

Kafka lag is derived using **topic offsets vs committed consumer offsets**:

```
sum by (consumergroup) (   
    kafka_topic_partition_current_offset   
    -   
    on (topic, partition)   
    kafka_consumergroup_current_offset 
)
```

This allows:

- Detecting slow consumers

- Visualizing backlog growth

- Setting alert thresholds


---

## 📊 Grafana Dashboards

The Grafana dashboard includes:

- Total events processed

- Events processed per minute

- Failed events per minute

- DLQ events (rolling window)

- Kafka consumer lag over time

---

## 🚀 Running the Project

### Prerequisites

- Docker
- Docker Compose

### Start all services

```
docker compose up -d
```

---

## 🔗 Service Endpoints

|Service|URL|
|---|---|
|Producer API|[http://localhost:8081](http://localhost:8081)|
|Consumer Metrics|[http://localhost:8082/actuator/prometheus](http://localhost:8082/actuator/prometheus)|
|Prometheus|[http://localhost:9090](http://localhost:9090)|
|Grafana|[http://localhost:3000](http://localhost:3000)|

**Grafana default login**

`username: admin password: admin`

---

## 🧪 Sample Event

```
curl -X POST http://localhost:8081/events \
  -H "Content-Type: application/json" \
  -d '{
    "eventId": "e1",
    "userId": "u1",
    "eventType": "CLICK",
    "timestamp": 1710000000000
  }'
```

---

## 📸 Screenshots

### Grafana Dashboard
![Grafana Dashboard](docs/grafana-dashboard.png)

>Grafana dashboard JSON is available under `docs/grafana/dashboard.json`


### Prometheus Targets
![Prometheus Targets](docs/prometheus-targets.png)


