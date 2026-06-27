# violin-calendar

日历事件服务。

## 技术栈

- Spring Boot 3.5 + Java 17
- PostgreSQL + Flyway（migration 位于 `db/migration-calendar/V200__init_calendar.sql`）
- MyBatis Plus
- 依赖 [violin-core](../violin-core)

## 业务模块

| URL | 说明 |
|---|---|
| `GET /api/v1/events` | 当前客户所有事件 |
| `GET /api/v1/events/{id}` | 单个事件 |
| `POST /api/v1/events` | 创建 |
| `PUT /api/v1/events/{id}` | 更新 |
| `DELETE /api/v1/events/{id}` | 软删除 |

## 数据库

- 业务表：`calendar_events`（snake_case + 审计 + `is_deleted` + `deleted_at`）
- 多租户隔离：每张表有 `customer_id` 列

## 本地启动

```powershell
$env:DB_PASSWORD = "postgres"
$env:VIOLIN_JWT_SECRET = "violin-calendar-local-secret-32bytes-aaa!"
mvn -f E:\Project\violin-calendar spring-boot:run "-Dspring-boot.run.profiles=local"
```

端口：`8085` + context-path `/calendar` → API 前缀 `http://localhost:8085/calendar/api/v1/events`

## K8s 部署

- Namespace：`violin-dev` / `violin-prod`
- 镜像：`registry.violin-work.online/violin-calendar:2.1`