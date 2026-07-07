package ru.denisovmaksim.taskmanager.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank
    @Column(name = "email", length = 254)
    private String email;

    @NotBlank
    @Column(name = "password", length = 255)
    private String password;

    @CreationTimestamp
    @Temporal(TIMESTAMP)
    @Column(name = "created")
    private Instant createdAt;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
