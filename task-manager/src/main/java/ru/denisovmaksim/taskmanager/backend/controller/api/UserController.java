package ru.denisovmaksim.taskmanager.backend.controller.api;

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
import ru.denisovmaksim.taskmanager.backend.dto.api.UserRequest;
import ru.denisovmaksim.taskmanager.backend.dto.api.UserResponse;
import ru.denisovmaksim.taskmanager.backend.model.CustomUserDetails;
import ru.denisovmaksim.taskmanager.backend.service.UserService;


@Slf4j
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @PostMapping(ApiPath.AUTH)
    @Override
    public ResponseEntity<Void> loginUser(@RequestBody UserRequest request) {
        log.info("Login User {}", request);
        String token = "Bearer " + userService.login(request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }

    @PostMapping(ApiPath.USER)
    @Override
    public ResponseEntity<Void> registerUser(@RequestBody UserRequest request) {
        log.info("Register user {}", request);
        String token = "Bearer " + userService.register(request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .build();
    }

    @GetMapping(ApiPath.USER)
    @Override
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("getCurrentUser");
        UserResponse userResponse = new UserResponse(userDetails.getUser().getId(), userDetails.getUsername());
        return ResponseEntity.ok(userResponse);
    }
}
