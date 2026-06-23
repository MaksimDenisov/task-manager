https://zhukovsd.github.io/java-backend-learning-course/projects/task-tracker/

https://t.me/zhukovsd_projects_it_chat

---
## Run Dev Mode
```sh
run-dev:
	docker compose -f compose.yml -f compose.dev.yml up
```
### Development Ports
# Ports

| Service      | Host Port | Description |
|--------------|-----------|-------------|
| web          | 8080      | Frontend (Nginx) |
| task-manager | 8081      | Main backend service |
| scheduler    | 8082      | Task scheduler service |
| mail-sender  | 8083      | Email sending service |
| PostgreSQL   | 5432      | Database |
| Kafka        | 9092      | Kafka broker |
| Kafka UI     | 8084      | Kafka management UI |

## URLs

- Frontend: http://localhost:8080
- Task Manager API: http://localhost:8081
- Scheduler API: http://localhost:8082
- Mail Sender API: http://localhost:8083
- Kafka UI: http://localhost:8084

---
### Run environment
```sh
make up
```
---
### Run docker services
```sh
make run-docker-task-manager
```
```sh
make run-docker-scheduler
```
```sh
make run-docker-mail-sender
```
---
#### Start Task Manager Service
```sh
make start-task-manager
```
#### Start Mail Sender Service
```sh
make start-mail-sender
```
#### Start Scheduler Service
```sh
make start-scheduler
```
