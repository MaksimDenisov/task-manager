package ru.denisovmaksim.taskmanager.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.denisovmaksim.taskmanager.backend.dto.TaskRequest;
import ru.denisovmaksim.taskmanager.backend.dto.TaskResponse;
import ru.denisovmaksim.taskmanager.backend.exception.TaskNotFoundException;
import ru.denisovmaksim.taskmanager.backend.model.Task;
import ru.denisovmaksim.taskmanager.backend.repository.TaskRepository;
import ru.denisovmaksim.taskmanager.backend.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public TaskResponse create(Long userId, TaskRequest request) {
        Task task = new Task(request.name(), request.description(), userRepository.getReferenceById(userId));
        Task createdTask = taskRepository.save(task);
        return new TaskResponse(createdTask.getId(), createdTask.getName(),
                createdTask.getDescription(), createdTask.isDone());
    }

    public Set<TaskResponse> getAll(long userId) {
        Set<Task> tasks = taskRepository.getAllByUserId(userId);
        return tasks.stream()
                .map(t -> new TaskResponse(t.getId(), t.getName(), t.getDescription(), t.isDone()))
                .collect(Collectors.toSet());
    }

    @Transactional
    public TaskResponse update(Long userId, Long taskId, TaskRequest request) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task with id = %d not found", taskId)));
        task.update(request.name(), request.description(), request.isDone());
        return new TaskResponse(task.getId(), task.getName(), task.getDescription(), task.isDone());
    }

    @Transactional
    public void delete(Long userId, long id) {
        taskRepository.delete(userId, id);
    }
}
