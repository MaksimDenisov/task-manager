package ru.denisovmaksim.taskmanager.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.denisovmaksim.taskmanager.backend.config.JwtProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class TaskManagerApp {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TaskManagerApp.class, args);
        log.info("task-manager started");
        JwtProperties jwtProperties = context.getBean(JwtProperties.class);
        log.info("Secret: {}, Timeout: {}", jwtProperties.secret(), jwtProperties.accessTokenExpiration());
    }
}

