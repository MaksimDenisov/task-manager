package ru.denisovmaksim.taskmanager.backend.dto.api;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User's info")
public record UserResponse(
        @Schema(description = "User's ID", example = "1")
        long id,
        @Schema(description = "User's email", example = "john@mail.com")
        String email
) {
}
