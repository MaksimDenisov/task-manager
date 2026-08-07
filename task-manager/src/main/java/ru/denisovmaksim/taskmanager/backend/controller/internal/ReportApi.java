package ru.denisovmaksim.taskmanager.backend.controller.internal;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.denisovmaksim.taskmanager.backend.dto.internal.UserInternalResp;
import ru.denisovmaksim.taskmanager.backend.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Report", description = "Get report for all users")
@SecurityRequirement(name = "internalToken")
public interface ReportApi {

    @Operation(summary = "Get all users tasks",
            description = """
                    Finds all users who have tasks completed within
                    the last 24 hours and/or have incomplete tasks remaining
                    """)
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Get list of user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserInternalResp.class)
                    ))
    })
    ResponseEntity<List<UserInternalResp>> findUsersWithIncompleteTasksOrCompletedBetween(
            LocalDateTime dateTime, LocalDateTime endDateTime);


    ResponseEntity<User> getUserWithIncompleteTasksOrCompletedBetween(
            Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
