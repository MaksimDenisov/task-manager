package ru.denisovmaksim.taskmanager.summarization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SummarizationApp {
    public static void main(String[] args) {
        SpringApplication.run(SummarizationApp.class, args);
        log.info("SummarizationApp started");
    }
}
