package ru.denisovmaksim.taskmanager.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.denisovmaksim.taskmanager.backend.config.ApiPath;
import ru.denisovmaksim.taskmanager.backend.dto.UserRequest;
import ru.denisovmaksim.taskmanager.backend.dto.UserResponse;
import ru.denisovmaksim.taskmanager.backend.model.CustomUserDetails;
import ru.denisovmaksim.taskmanager.backend.service.JwtService;
import ru.denisovmaksim.taskmanager.backend.service.UserDetailsServiceImpl;
import ru.denisovmaksim.taskmanager.backend.service.UserService;

@Tag(name = "Users", description = "Operation with user (sign up, sign in, get user info)")
@Slf4j
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final JwtService jwtService;

    @Operation(summary = "Login user", description = "Return JWT if authenticated")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Incorrect email or password")
    })
    @PostMapping(ApiPath.AUTH)
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request) {
        log.info("Login User {}", request);
        String token = "Bearer " + userService.login(request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }

    @Operation(summary = "Register user", description = "Return JWT if registration successful")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Register  successful"),
            @ApiResponse(responseCode = "409", description = "This email is already taken"),
            @ApiResponse(responseCode = "400", description = "Incorrect data")
    })
    @PostMapping(ApiPath.USER)
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request) {
        log.info("Register user {}", request);
        String token = "Bearer " + userService.register(request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }

    @Operation(summary = "Get user info", description = "Return user's email and ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User info"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(ApiPath.USER)
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("getCurrentUser");
        UserResponse userResponse = new UserResponse(userDetails.getUser().getId(), userDetails.getUsername());
        return ResponseEntity.ok(userResponse);
    }
}
