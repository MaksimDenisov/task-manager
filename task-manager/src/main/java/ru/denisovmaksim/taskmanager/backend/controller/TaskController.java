package ru.denisovmaksim.taskmanager.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.denisovmaksim.taskmanager.backend.dto.TaskRequest;
import ru.denisovmaksim.taskmanager.backend.dto.TaskResponse;
import ru.denisovmaksim.taskmanager.backend.model.CustomUserDetails;
import ru.denisovmaksim.taskmanager.backend.service.TaskService;

import java.net.URI;
import java.util.Set;

@Tag(name = "Tasks", description = "Operation with tasks")
@RestController
@RequestMapping(ApiPath.TASKS)
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Get all tasks", description = "Return list of user's tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get list of tasks"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping
    public ResponseEntity<Set<TaskResponse>> getAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(taskService.getAll(userDetails.getUser().getId()));
    }

    @Operation(summary = "Create new task", description = "Create task and return created task with Location header")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created"),
            @ApiResponse(responseCode = "400", description = "Incorrect data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                   @Valid @RequestBody TaskRequest request) {
        Long userId = userDetails.getUser().getId();
        TaskResponse response = taskService.create(userId, request);
        URI location = URI.create(ApiPath.TASKS + "/" + response.id());
        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(summary = "Replace task", description = "Return updated task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "400", description = "Incorrect data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                   @Parameter(description = "Task ID")
                                                   @PathVariable Long taskId, @Valid @RequestBody TaskRequest request) {
        Long userId = userDetails.getUser().getId();
        TaskResponse response = taskService.update(userId, taskId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete task", description = "Delete task")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@AuthenticationPrincipal CustomUserDetails userDetails,
                           @Parameter(description = "Task ID") @PathVariable long id) {
        Long userId = userDetails.getUser().getId();
        taskService.delete(userId, id);
    }
}
