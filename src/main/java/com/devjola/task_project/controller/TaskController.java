package com.devjola.task_project.controller;

import com.devjola.task_project.dto.CreateTaskDto;
import com.devjola.task_project.dto.UpdateTaskDto;
import com.devjola.task_project.models.Status;
import com.devjola.task_project.models.Task;
import com.devjola.task_project.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create-task")
    public ResponseEntity<Task> createTask(CreateTaskDto createTaskDto) {
        return ResponseEntity.ok().body(taskService.createTask(createTaskDto));
    }

    @GetMapping("/fetch-all-tasks")
    public ResponseEntity<List<Task>> getAllTasks () {
        return ResponseEntity.ok().body(taskService.fetchAllTasks());
    }

    @GetMapping("/fetch-by-id/{id}")
    public ResponseEntity<Task> fetchById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(taskService.fetchById(id));
    }

    @GetMapping("/fetch-by-title/{title}")
    public ResponseEntity<List<Task>> fetchByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok().body(taskService.fetchByName(title));
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody UpdateTaskDto updateTaskDto) {
        return ResponseEntity.ok().body(taskService.updateTask(id, updateTaskDto));
    }

    @PutMapping("/update-task-status/{id}/{status}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @PathVariable Status status) {
        return ResponseEntity.ok().body(taskService.updateTaskStatus(id, status));
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(taskService.deleteTask(id));
    }

}
