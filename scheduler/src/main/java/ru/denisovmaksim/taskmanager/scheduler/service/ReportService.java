package ru.denisovmaksim.taskmanager.scheduler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.denisovmaksim.taskmanager.scheduler.client.TaskManagerClient;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportService {

    private final TaskManagerClient taskManagerClient;

    public void generateReports() {
        log.info("Generate reports");
        LocalDateTime startDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime endDateTime = LocalDateTime.now();
        taskManagerClient.getUsersWithTasks(startDateTime, endDateTime)
                .forEach(u -> log.info(u.toString()));
    }
}
