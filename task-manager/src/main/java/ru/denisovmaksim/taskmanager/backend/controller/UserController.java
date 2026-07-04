package ru.denisovmaksim.taskmanager.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.denisovmaksim.taskmanager.backend.dto.ApiPath;
import ru.denisovmaksim.taskmanager.backend.dto.UserRequest;
import ru.denisovmaksim.taskmanager.backend.dto.UserResponse;

@Tag(name = "Users", description = "Operation with user (sign up, sign in, get user info)")
@Slf4j
@RestController
@RequestMapping()
public class UserController {

    @Operation(summary = "Register user", description = "Return JWT if registration successful")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Register  successful"),
            @ApiResponse(responseCode = "409", description = "This email is already taken"),
            @ApiResponse(responseCode = "400", description = "Incorrect data")
    })
    @PostMapping(ApiPath.USERS)
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest user) {
        log.info("Register user {}", user);
        return ResponseEntity.ok(new UserResponse(1, "jonh@mail.com"));
    }

    @Operation(summary = "Login user", description = "Return JWT if authenticated")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Incorrect email or password")
    })
    @PostMapping(ApiPath.AUTH)
    public ResponseEntity<UserResponse> loginUser(UserRequest user) {
        log.info("Login User {}", user);
        return ResponseEntity.ok(new UserResponse(1, "jonh@mail.com"));
    }

    @Operation(summary = "Get user info", description = "Return user's email and ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User info"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(ApiPath.USERS)
    public ResponseEntity<UserResponse> getCurrentUser() {
        log.info("getCurrentUser");
        return ResponseEntity.ok(new UserResponse(1, "jonh@mail.com"));
    }
}
