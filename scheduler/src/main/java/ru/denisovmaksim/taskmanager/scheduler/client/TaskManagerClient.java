package ru.denisovmaksim.taskmanager.scheduler.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import ru.denisovmaksim.taskmanager.scheduler.dto.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskManagerClient {

    @GetExchange("/internal/report/users-tasks")
    List<UserResponse> getUsersWithTasks(
            @RequestParam("startDateTime") LocalDateTime startDateTime,
            @RequestParam("endDateTime") LocalDateTime endDateTime
    );
}
