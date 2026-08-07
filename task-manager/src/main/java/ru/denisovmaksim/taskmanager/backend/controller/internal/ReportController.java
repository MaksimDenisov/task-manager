package ru.denisovmaksim.taskmanager.backend.controller.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.denisovmaksim.taskmanager.backend.config.InternalPath;
import ru.denisovmaksim.taskmanager.backend.dto.internal.UserInternalResp;
import ru.denisovmaksim.taskmanager.backend.model.User;
import ru.denisovmaksim.taskmanager.backend.service.ReportService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(InternalPath.REPORT)
@RequiredArgsConstructor
public class ReportController implements ReportApi {

    private final ReportService reportService;

    @Override
    @GetMapping("/users-tasks")
    public ResponseEntity<List<UserInternalResp>> findUsersWithIncompleteTasksOrCompletedBetween(
            @RequestParam(required = false) LocalDateTime startDateTime,
            @RequestParam(required = false) LocalDateTime endDateTime) {
        List<UserInternalResp> users =
                reportService.findUsersWithIncompleteTasksOrCompletedBetween(startDateTime, endDateTime);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}/tasks")
    @Override
    public ResponseEntity<User> getUserWithIncompleteTasksOrCompletedBetween(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) LocalDateTime startDateTime,
            @RequestParam(required = false) LocalDateTime endDateTime) {
        User user = reportService.getUserWithIncompleteTasksOrCompletedBetween(userId, startDateTime, endDateTime);
        return ResponseEntity.ok(user);
    }
}
