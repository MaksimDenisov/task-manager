.PHONY: checkstyle report test unit it up run-dev deploy

checkstyle:
	./gradlew checkstyleMain checkstyleTest
report:
	./gradlew jacocoTestReport

run-docker-task-manager:
	docker run -it task-manager
run-docker-scheduler:
	docker run -it scheduler
run-docker-mail-sender:
	docker run -it mail-sender

up:
	docker compose -f compose.yml -f compose.env.yml up

run-dev:
	docker compose -f compose.yml -f compose.env.yml -f compose.dev.yml up

run-prod:
	docker compose -f compose.yml -f compose.env.yml -f compose.prod.yml up