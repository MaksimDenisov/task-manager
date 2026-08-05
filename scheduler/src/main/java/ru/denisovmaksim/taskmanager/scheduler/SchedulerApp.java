package ru.denisovmaksim.taskmanager.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class SchedulerApp {
    public static void main(String[] args) {
        SpringApplication.run(SchedulerApp.class, args);
        log.info("scheduler started");
    }
}
