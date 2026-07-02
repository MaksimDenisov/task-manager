package ru.denisovmaksim.taskmanager.backend.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "User registration data")
public record UserRequest(
    @Schema(description = "User's email", example = "john@mail.com")
    String email,

    @Schema(description = "Password", example = "MyStrongPassword123")
    @Size(min = 8, max = 64)
    String password
) { }
