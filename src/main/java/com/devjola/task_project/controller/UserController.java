package com.devjola.task_project.controller;

import com.devjola.task_project.dto.UpdateUserDetailsDto;
import com.devjola.task_project.models.User;
import com.devjola.task_project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<User> updateUserDetails(@RequestBody UpdateUserDetailsDto updateUserDetailsDto) {
        return ResponseEntity.ok().body(userService.updateUserDetails(updateUserDetailsDto));
    }

}
