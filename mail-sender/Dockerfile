FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /usr/app

COPY ../gradlew .
COPY gradle gradle

RUN chmod +x gradlew

COPY build.gradle settings.gradle ./

RUN ./gradlew --no-daemon dependencies || true
COPY . /usr/app

RUN ./gradlew :mail-sender:bootJar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /usr/app

COPY --from=builder /usr/app/mail-sender/build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]