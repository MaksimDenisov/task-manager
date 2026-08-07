package ru.denisovmaksim.taskmanager.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.denisovmaksim.taskmanager.backend.dto.internal.UserInternalResp;
import ru.denisovmaksim.taskmanager.backend.model.User;
import ru.denisovmaksim.taskmanager.backend.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {
    private final UserRepository userRepository;

    public List<UserInternalResp> findUsersWithIncompleteTasksOrCompletedBetween(
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        startDateTime = ifNullGet(startDateTime, LocalDateTime.MIN);
        endDateTime = ifNullGet(endDateTime, LocalDateTime.MAX);
        ZoneId zone = ZoneId.systemDefault();
        Instant start = startDateTime.atZone(zone).toInstant();
        Instant end = endDateTime.atZone(zone).toInstant();
        return userRepository.findUsersWithIncompleteTasksOrCompletedBetween(start, end)
                .stream()
                .map(user -> new UserInternalResp(user.getId(), user.getEmail()))
                .toList();
    }

    public User getUserWithIncompleteTasksOrCompletedBetween(
            Long userId,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        startDateTime = ifNullGet(startDateTime, LocalDateTime.MIN);
        endDateTime = ifNullGet(endDateTime, LocalDateTime.MAX);
        ZoneId zone = ZoneId.systemDefault();
        Instant start = startDateTime.atZone(zone).toInstant();
        Instant end = endDateTime.atZone(zone).toInstant();
        return userRepository.getUserWithIncompleteTasksOrCompletedBetween(userId, start, end);
    }

    private LocalDateTime ifNullGet(LocalDateTime dateTime, LocalDateTime def) {
        return (dateTime == null) ? def : dateTime;
    }
}
