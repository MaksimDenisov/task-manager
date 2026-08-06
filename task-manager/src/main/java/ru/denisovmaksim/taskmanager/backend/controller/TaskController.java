package ru.denisovmaksim.taskmanager.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.denisovmaksim.taskmanager.backend.config.ApiPath;
import ru.denisovmaksim.taskmanager.backend.controller.api.TaskApi;
import ru.denisovmaksim.taskmanager.backend.dto.TaskRequest;
import ru.denisovmaksim.taskmanager.backend.dto.TaskResponse;
import ru.denisovmaksim.taskmanager.backend.model.CustomUserDetails;
import ru.denisovmaksim.taskmanager.backend.service.TaskService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiPath.TASKS)
@RequiredArgsConstructor
public class TaskController implements TaskApi {
    private final TaskService taskService;

    @GetMapping
    @Override
    public ResponseEntity<List<TaskResponse>> getAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(taskService.getAll(userDetails.getUser().getId()));
    }

    @PostMapping
    @Override
    public ResponseEntity<TaskResponse> createTask(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                   @Valid @RequestBody TaskRequest request) {
        Long userId = userDetails.getUser().getId();
        TaskResponse response = taskService.create(userId, request);
        URI location = URI.create(ApiPath.TASKS + "/" + response.id());
        return ResponseEntity
                .created(location)
                .body(response);
    }

    @PutMapping("/{taskId}")
    @Override
    public ResponseEntity<TaskResponse> updateTask(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                   @PathVariable Long taskId, @Valid @RequestBody TaskRequest request) {
        Long userId = userDetails.getUser().getId();
        TaskResponse response = taskService.update(userId, taskId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{taskId}/complete")
    @Override
    public ResponseEntity<TaskResponse> completeTask(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                     @PathVariable Long taskId) {
        Long userId = userDetails.getUser().getId();
        TaskResponse response = taskService.complete(userId, taskId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{taskId}/reopen")
    @Override
    public ResponseEntity<TaskResponse> reopenTask(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                   @PathVariable Long taskId) {
        Long userId = userDetails.getUser().getId();
        TaskResponse response = taskService.reopen(userId, taskId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void deleteTask(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        Long userId = userDetails.getUser().getId();
        taskService.delete(userId, id);
    }
}
