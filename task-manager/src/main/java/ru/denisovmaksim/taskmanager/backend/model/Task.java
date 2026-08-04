package ru.denisovmaksim.taskmanager.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "is_done")
    private boolean isDone;

    @Column(name = "completed_at")
    private Instant completedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public void update(String name, String description, boolean isDone) {
        this.name = name;
        this.description = description;
        this.isDone = isDone;
        if (isDone) {
            completedAt = Instant.now();
        }
    }
}
