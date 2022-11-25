package com.devjola.task_project.dto;

import com.devjola.task_project.models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskDto {
    @NotNull
    private String title;

    @NotNull
    private String description;

}
