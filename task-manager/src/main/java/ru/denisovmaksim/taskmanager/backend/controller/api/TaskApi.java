package ru.denisovmaksim.taskmanager.backend.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.denisovmaksim.taskmanager.backend.dto.ErrorResponse;
import ru.denisovmaksim.taskmanager.backend.dto.TaskRequest;
import ru.denisovmaksim.taskmanager.backend.dto.TaskResponse;
import ru.denisovmaksim.taskmanager.backend.model.CustomUserDetails;

import java.util.List;

@Tag(name = "Tasks", description = "Operation with tasks")
@SecurityRequirement(name = "bearerAuth")
public interface TaskApi {
    @Operation(summary = "Get all tasks", description = "Return list of user's tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Get list of tasks",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
                    )),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    ResponseEntity<List<TaskResponse>> getAll(CustomUserDetails userDetails);

    @Operation(summary = "Create new task", description = "Create task and return created task with Location header")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Task created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
                    )),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    ResponseEntity<TaskResponse> createTask(CustomUserDetails userDetails, TaskRequest request);

    @Operation(summary = "Update task", description = "Return updated task")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Task updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)
                    )),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    ResponseEntity<TaskResponse> updateTask(CustomUserDetails userDetails,
                                            @Parameter(description = "Task ID") Long taskId,
                                            TaskRequest request);

    @Operation(summary = "Complete task", description = "Mark task as completed")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task marked as completed"),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    ResponseEntity<TaskResponse> completeTask(CustomUserDetails userDetails,
                    @Parameter(description = "Task ID") Long taskId);

    @Operation(summary = "Reopen task", description = "Mark task as incompleted")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task marked as incompleted"),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    ResponseEntity<TaskResponse> reopenTask(CustomUserDetails userDetails,
                    @Parameter(description = "Task ID") Long taskId);

    @Operation(summary = "Delete task", description = "Delete task")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Task not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    void deleteTask(CustomUserDetails userDetails,
                    @Parameter(description = "Task ID") Long taskId);
}
