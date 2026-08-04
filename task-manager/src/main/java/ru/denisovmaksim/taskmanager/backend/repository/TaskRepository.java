package ru.denisovmaksim.taskmanager.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.denisovmaksim.taskmanager.backend.model.Task;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndUserId(Long taskId, Long userId);

    Set<Task> getAllByUserId(long userId);

    @Query("""
            delete from Task t
            where t.id = :taskId
            and t.user.id = :userId
            """)
    @Modifying
    void delete(Long userId, long taskId);
}
