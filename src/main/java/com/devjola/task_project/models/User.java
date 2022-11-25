package com.devjola.task_project.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_tbl")
public class User extends BaseClass {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dob;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 8, max = 15, message = "Password must be greater or equal to 8 and can " +
            "either be numbers or characters")
    private String password;

    @NotNull
    private String address;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Task> taskList;
}
