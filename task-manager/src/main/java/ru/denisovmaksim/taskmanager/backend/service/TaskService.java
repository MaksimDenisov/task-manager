package ru.denisovmaksim.taskmanager.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denisovmaksim.taskmanager.backend.dto.api.TaskRequest;
import ru.denisovmaksim.taskmanager.backend.dto.api.TaskResponse;
import ru.denisovmaksim.taskmanager.backend.exception.TaskNotFoundException;
import ru.denisovmaksim.taskmanager.backend.model.Task;
import ru.denisovmaksim.taskmanager.backend.repository.TaskRepository;
import ru.denisovmaksim.taskmanager.backend.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public TaskResponse create(Long userId, TaskRequest request) {
        Task task = new Task(request.name(), request.description(), userRepository.getReferenceById(userId));
        return toResponse(taskRepository.save(task));
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAll(long userId) {
        List<Task> tasks = taskRepository.getAllByUserIdOrderByName(userId);
        return tasks.stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public TaskResponse update(Long userId, Long taskId, TaskRequest request) {
        Task task = getTaskByIdAndThrowIfNotExist(userId, taskId);
        return toResponse(task.update(request.name(), request.description()));
    }

    @Transactional
    public TaskResponse complete(Long userId, Long taskId) {
        Task task = getTaskByIdAndThrowIfNotExist(userId, taskId);
        return toResponse(task.complete());
    }

    @Transactional
    public TaskResponse reopen(Long userId, Long taskId) {
        Task task = getTaskByIdAndThrowIfNotExist(userId, taskId);
        return toResponse(task.reopen());
    }

    @Transactional
    public void delete(Long userId, Long taskId) {
        Task task = getTaskByIdAndThrowIfNotExist(userId, taskId);
        taskRepository.delete(task);
    }

    private Task getTaskByIdAndThrowIfNotExist(Long userId, Long taskId) {
        return taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() ->
                        new TaskNotFoundException(String.format("Task with id = %d not found", taskId)));
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId(), task.getName(), task.getDescription(), task.isDone());
    }
}
