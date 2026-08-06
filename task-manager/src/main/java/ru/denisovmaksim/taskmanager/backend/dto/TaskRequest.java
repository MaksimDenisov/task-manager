package ru.denisovmaksim.taskmanager.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Task request data")
public record TaskRequest(
        @Schema(
                description = "Task name",
                example = "Buy groceries"
        )
        @NotBlank
        @Size(max = 100, message = "Length of name must be max 100 character")
        String name,

        @Schema(
                description = "Task description",
                example = "Cucumber, lettuce, corn"
        )
        @Size(max = 2000, message = "Length of description must be max 2000 character")
        String description
) { }
