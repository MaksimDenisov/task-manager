.PHONY: checkstyle report test unit it up run-dev deploy

checkstyle:
	./gradlew checkstyleMain checkstyleTest
report:
	./gradlew jacocoTestReport

run-task-manager:
	docker compose -f compose.yml -f compose.dev.yml up task-manager --build
run-scheduler:
	docker compose -f compose.yml -f compose.dev.yml up scheduler --build
run-summarization:
	docker compose -f compose.yml -f compose.dev.yml up summarization --build
run-mail-sender:
	docker compose -f compose.yml -f compose.dev.yml up mail-sender


run-dev:
	docker compose -f compose.yml -f compose.dev.yml up
stop-dev:
	docker compose -f compose.yml -f compose.dev.yml down

run-prod:
	docker compose -f compose.yml -f compose.prod.yml up

