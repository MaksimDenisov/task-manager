package ru.denisovmaksim.taskmanager.scheduler.job;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.denisovmaksim.taskmanager.scheduler.service.ReportService;

@Component
@RequiredArgsConstructor
public class ReportScheduler {

    private final ReportService reportService;

    @Scheduled(cron = "${app.scheduler.report-cron}")
    public void generateReports() {
        reportService.generateReports();
    }
}
