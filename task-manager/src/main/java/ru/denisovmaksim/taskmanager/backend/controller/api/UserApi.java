package ru.denisovmaksim.taskmanager.backend.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.denisovmaksim.taskmanager.backend.config.ApiPath;
import ru.denisovmaksim.taskmanager.backend.dto.ErrorResponse;
import ru.denisovmaksim.taskmanager.backend.dto.UserRequest;
import ru.denisovmaksim.taskmanager.backend.dto.UserResponse;
import ru.denisovmaksim.taskmanager.backend.model.CustomUserDetails;

@Tag(name = "Users", description = "Operation with user (sign up, sign in, get user info)")
public interface UserApi {
    @Operation(summary = "Login user", description = "Return JWT if authenticated")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful", content = {}),
            @ApiResponse(responseCode = "401",
                    description = "Incorrect email or password",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    ResponseEntity<Void> loginUser(UserRequest request);

    @Operation(summary = "Register user", description = "Return JWT if registration successful")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Register  successful", content = {}),
            @ApiResponse(responseCode = "409",
                    description = "This email is already taken",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    ResponseEntity<Void> registerUser(UserRequest request);

    @Operation(summary = "Get user info", description = "Return user's email and ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "User info",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)
                    )),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @GetMapping(ApiPath.USER)
    ResponseEntity<UserResponse> getCurrentUser(CustomUserDetails userDetails);
}
