package ru.denisovmaksim.taskmanager.scheduler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReportService {
    public void generateReports() {
        log.info("Generate reports");
    }
}
