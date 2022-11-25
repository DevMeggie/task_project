package com.devjola.task_project.service;

import com.devjola.task_project.dto.CreateTaskDto;
import com.devjola.task_project.dto.UpdateTaskDto;
import com.devjola.task_project.models.Status;
import com.devjola.task_project.models.Task;

import java.util.List;

public interface TaskService {
    Task createTask(CreateTaskDto createTaskDto);

    List<Task> fetchAllTasks();

    Task fetchById(Long id);

    List<Task> fetchByName(String title);

    Task updateTask(Long id, UpdateTaskDto updateTaskDto);

    Task updateTaskStatus(Long id, Status status);

    String deleteTask(Long id);
}
