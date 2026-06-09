package ru.denisovmaksim.taskmanager.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TaskManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApp.class, args);
        log.info("task-manager started");
    }
}
