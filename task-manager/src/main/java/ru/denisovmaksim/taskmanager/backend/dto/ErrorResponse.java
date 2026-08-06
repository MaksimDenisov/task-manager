package ru.denisovmaksim.taskmanager.backend.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error message")
public record ErrorResponse(
        @Schema(description = "Error message", example = "Not found")
        String message
) {
}
