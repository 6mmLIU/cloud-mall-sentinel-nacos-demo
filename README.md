# Cloud Mall Sentinel Nacos Demo
[中文版](README.zh.md)


This project demonstrates a simple microservice mall built with **Spring Boot** and
**Spring Cloud Alibaba**.  The modules are aggregated by the Maven parent
`shop-parent-65` and showcase service discovery with Nacos, traffic
control with Sentinel and a Spring Cloud Gateway entry point.

## Modules

| Module                | Description |
| --------------------- | ----------- |
| `shop-product-api`    | POJOs and interfaces shared by the product service. |
| `shop-product-server` | Product service using MyBatis‑Plus and registered in Nacos. |
| `shop-order-api`      | Entities for orders shared with other services. |
| `shop-order-server`   | Order service, uses OpenFeign to call the product service and integrates Sentinel for traffic limiting. |
| `shop-api-gateway`    | Spring Cloud Gateway application with Sentinel gateway filters and custom filters. |

## Building

The project is organized as a Maven multi‑module build. To compile all modules run:

```bash
mvn clean package
```

All modules require Java 17.

## Running Services

Each server module contains a Spring Boot `main` class under
`src/main/java` and an `application.yml` defining service ports and
Nacos/Sentinel addresses. Start Nacos and Sentinel locally, then launch
`ProductApplication`, `OrderApplication`, and `GatewayApplication` to
bring the system online.

## Features

- Service registration and discovery via **Nacos**.
- **Sentinel** integration for flow control, degradation and parameter
  hotspot rules (see `SentinelController` in the order service).
- **Spring Cloud Gateway** routes incoming requests to the product and
  order services while applying custom filters such as
  `TimeGatewayFilterFactory`.

This project serves as a starting point for experimenting with Spring
Cloud Alibaba components in a small mall scenario.
