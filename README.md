https://zhukovsd.github.io/java-backend-learning-course/projects/task-tracker/

https://t.me/zhukovsd_projects_it_chat

## Development
---
### Run all services
```sh
make run-dev
```
### Stop all services
```sh
make stop-dev
```

### Run one service
```sh
make run-task-manager
make run-scheduler
make run-mail-sender
```

## URLs

| Service | URL |
|---------|-----|
| Frontend | http://localhost:8080 |
| Task Manager API | http://localhost:8081 |
| Scheduler API | http://localhost:8082 |
| Mail Sender API | http://localhost:8083 |
| Kafka UI | http://localhost:8084 |

## Development Ports
| Service      | Host Port | Description |
|--------------|-----------|-------------|
| web          | 8080      | Frontend (Nginx) |
| task-manager | 8081      | Main backend service |
| scheduler    | 8082      | Task scheduler service |
| mail-sender  | 8083      | Email sending service |
| PostgreSQL   | 5432      | Database |
| Kafka        | 9092      | Kafka broker |
| Kafka UI     | 8084      | Kafka management UI |






