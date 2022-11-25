package com.devjola.task_project.service.serviceImpl;

import com.devjola.task_project.dto.CreateTaskDto;
import com.devjola.task_project.dto.UpdateTaskDto;
import com.devjola.task_project.exception.AlreadyExistsException;
import com.devjola.task_project.exception.EmptyListException;
import com.devjola.task_project.exception.NotFoundException;
import com.devjola.task_project.models.Status;
import com.devjola.task_project.models.Task;
import com.devjola.task_project.models.User;
import com.devjola.task_project.repository.TaskRepository;
import com.devjola.task_project.service.TaskService;
import com.devjola.task_project.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final Utils utils;

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(CreateTaskDto createTaskDto) {
        Long userId = utils.getLoggedUserById();
        User user = utils.findUserById(userId);

        Task task = taskRepository.findByTitle(createTaskDto.getTitle());

        if(user.getTaskList().contains(task)) {
            throw new AlreadyExistsException("Task Already Exists");
        }
        Task newTask = Task.builder()
                .title(createTaskDto.getTitle())
                .description(createTaskDto.getDescription())
                .build();
        taskRepository.save(newTask);
        return newTask;
    }

    @Override
    public List<Task> fetchAllTasks() {
        List<Task> taskList = taskRepository.findAll();

        if (taskList.isEmpty()) {
            throw new EmptyListException("You do not have any tasks");
        }
        return taskList;
    }

    @Override
    public Task fetchById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    @Override
    public List<Task> fetchByName(String title) {
        List<Task> taskList = taskRepository.findByTitleContaining(title);
        if(taskList.isEmpty()) {
            throw new EmptyListException("No task with found");
        }
        return taskList;
    }

    @Override
    public Task updateTask(Long id, UpdateTaskDto updateTaskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task does exist"));

        if (!task.getTitle().equals(updateTaskDto.getTitle()) && updateTaskDto.getTitle() != null) {
            task.setTitle(updateTaskDto.getTitle());
        }
        if (!task.getDescription().equals(updateTaskDto.getDescription()) && updateTaskDto.getDescription() != null) {
            task.setDescription(updateTaskDto.getDescription());
        }
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task updateTaskStatus(Long id, Status status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task does exist"));

        if(!task.getStatus().equals(status) && status != null) {
            task.setStatus(status);
            if(status == Status.DONE) {
                task.setCompletedAt(LocalDateTime.now());
            }
        }
        return task;
    }

    @Override
    public String deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task does exist"));

        taskRepository.delete(task);
      return "task deleted";
    }
}












