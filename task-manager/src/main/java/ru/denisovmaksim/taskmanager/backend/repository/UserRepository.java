package ru.denisovmaksim.taskmanager.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.denisovmaksim.taskmanager.backend.model.User;

import java.time.Instant;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(
            """
                    SELECT DISTINCT u from Task t JOIN  t.user u
                                WHERE t.isDone = FALSE
                                OR (t.completedAt >= :start AND t.completedAt < :end)
                                ORDER BY u.id
                    """
    )
    List<User> findUsersWithIncompleteTasksOrCompletedBetween(@Param("start") Instant start,
                                                              @Param("end") Instant end);


    @Query(
            """
                    SELECT u FROM User u LEFT JOIN FETCH u.taskSet t
                    WHERE u.id = :userId
                    AND (t.isDone = FALSE
                    OR (t.completedAt >= :start AND t.completedAt < :end))
                    """
    )
    User getUserWithIncompleteTasksOrCompletedBetween(@Param("userId") Long id,
                                                      @Param("start") Instant start,
                                                      @Param("end") Instant end);
}
