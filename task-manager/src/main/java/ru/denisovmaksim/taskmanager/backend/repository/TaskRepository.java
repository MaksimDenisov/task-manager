package ru.denisovmaksim.taskmanager.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denisovmaksim.taskmanager.backend.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndUserId(Long taskId, Long userId);

    List<Task> getAllByUserIdOrderByName(long userId);
}
