package com.devjola.task_project.util;

import com.devjola.task_project.exception.NotFoundException;
import com.devjola.task_project.models.User;
import com.devjola.task_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class Utils {
        private final HttpSession httpSession;

        private final UserRepository userRepository;


        public Long getLoggedUserById() {
            Long userId =  (Long) httpSession.getAttribute("User_id");
            if(userId == null) throw new NotFoundException("Please log in to perform this operation!");
            return userId;
        }


        public User findUserById(Long id) {
            return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
        }
}
