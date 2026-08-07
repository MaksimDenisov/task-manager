package ru.denisovmaksim.taskmanager.backend.dto.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Task response data")
public record TaskResponse(
        @Schema(
                description = "Task ID",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Task name",
                example = "Buy groceries"
        )
        String name,

        @Schema(
                description = "Task description",
                example = "Cucumber, lettuce, corn"
        )
        String description,

        @Schema(
                description = "Status of task",
                example = "true"
        )
        boolean isDone
) { }
