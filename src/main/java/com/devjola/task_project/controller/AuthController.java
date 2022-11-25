package com.devjola.task_project.controller;

import com.devjola.task_project.dto.LoginDto;
import com.devjola.task_project.dto.SignUpDto;
import com.devjola.task_project.models.User;
import com.devjola.task_project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody SignUpDto signUpDto) {
        User newUser = userService.signUp(signUpDto);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().body( userService.login(loginDto));
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok().body(userService.logout());
    }
}
