.PHONY: checkstyle report test unit it up run-dev deploy

checkstyle:
	./gradlew checkstyleMain checkstyleTest
report:
	./gradlew jacocoTestReport

start-all-service:
	./gradlew clean build bootRun
start-task-manager:
	./gradlew :task-manager:clean :task-manager:build :task-manager:bootRun
start-mail-sender:
	./gradlew :mail-sender:clean :mail-sender:build :mail-sender:bootRun
start-scheduler:
	./gradlew :scheduler:clean :scheduler:build :scheduler:bootRun

