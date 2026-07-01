.PHONY: checkstyle report test unit it up run-dev deploy

checkstyle:
	./gradlew checkstyleMain checkstyleTest
report:
	./gradlew jacocoTestReport

run-task-manager:
	docker compose -f compose.yml -f compose.dev.yml up task-manager
run-scheduler:
	docker compose -f compose.yml -f compose.dev.yml up scheduler
run-mail-sender:
	dockerdocker compose -f compose.yml -f compose.dev.yml up mail-sender

run-dev:
	docker compose -f compose.yml -f compose.dev.yml up
stop-dev:
	docker compose -f compose.yml -f compose.dev.yml down

run-prod:
	docker compose -f compose.yml -f compose.prod.yml up

