# 云商城 Sentinel Nacos 示例

该项目演示了一个使用 **Spring Boot** 和 **Spring Cloud Alibaba** 构建的简单微服务商城。所有模块由 Maven 父工程 `shop-parent-65` 管理，展示了基于 Nacos 的服务发现、基于 Sentinel 的流量控制以及以 Spring Cloud Gateway 为入口的架构。

## 模块说明

| 模块 | 简介 |
|-----------------------|----------------|
| `shop-product-api`    | 产品服务共享的 POJO 和接口 |
| `shop-product-server` | 使用 MyBatis-Plus 实现并注册到 Nacos 的产品服务 |
| `shop-order-api`      | 供其他服务共享的订单实体 |
| `shop-order-server`   | 调用产品服务的订单服务，结合 Sentinel 实现流量限流 |
| `shop-api-gateway`    | 集成 Sentinel 网关过滤器和自定义过滤器的 Spring Cloud Gateway 应用 |

## 构建项目

本项目采用 Maven 多模块管理，编译所有模块执行：

```bash
mvn clean package
```

所有模块需要 Java 17 环境。

## 运行服务

每个服务模块都在 `src/main/java` 下包含 Spring Boot `main` 类，并在 `application.yml` 中配置了服务端口以及 Nacos/Sentinel 地址。先启动本地的 Nacos 与 Sentinel，再依次运行 `ProductApplication`、`OrderApplication` 和 `GatewayApplication` 即可启动整个系统。

## 功能特性

- 通过 **Nacos** 实现服务注册与发现
- 集成 **Sentinel** 实现限流、熔断以及参数点点规则（详见订单服务中的 `SentinelController`）
- 通过 **Spring Cloud Gateway** 将外部请求路由到产品服务和订单服务，并应用 `TimeGatewayFilterFactory` 等自定义过滤器

该项目适合用作在小型商城场景下体验 Spring Cloud Alibaba 组件的起点。
