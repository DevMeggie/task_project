package com.devjola.task_project.service;

import com.devjola.task_project.dto.LoginDto;
import com.devjola.task_project.dto.SignUpDto;
import com.devjola.task_project.dto.UpdateUserDetailsDto;
import com.devjola.task_project.models.User;

public interface UserService {

    User signUp(SignUpDto signUpDto);

    String login(LoginDto loginDto);

    User updateUserDetails(UpdateUserDetailsDto updateUserDto);

    String logout();
}
