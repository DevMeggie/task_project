package com.devjola.task_project.repository;

import com.devjola.task_project.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByTitle(String title);

    List<Task> findByTitleContaining(String title);


}
