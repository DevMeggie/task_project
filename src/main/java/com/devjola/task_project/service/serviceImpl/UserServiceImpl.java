package com.devjola.task_project.service.serviceImpl;

import com.devjola.task_project.dto.LoginDto;
import com.devjola.task_project.dto.SignUpDto;
import com.devjola.task_project.dto.UpdateUserDetailsDto;
import com.devjola.task_project.exception.AlreadyExistsException;
import com.devjola.task_project.exception.IncorrectValueEntryException;
import com.devjola.task_project.exception.NotFoundException;
import com.devjola.task_project.exception.PasswordsDoNotMatchException;
import com.devjola.task_project.models.User;
import com.devjola.task_project.repository.UserRepository;
import com.devjola.task_project.service.UserService;
import com.devjola.task_project.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private HttpSession httpSession;

    private final Utils utils;

    @Override
    public User signUp(SignUpDto signUpDto) {
        boolean dbUser = userRepository.existsByEmail(signUpDto.getEmail());

        if(dbUser) {
            throw new AlreadyExistsException("User with " + signUpDto.getEmail() + " already exists");
        }
        if(!Objects.equals(signUpDto.getPassword(), signUpDto.getVerifyPassword())) {
            throw new PasswordsDoNotMatchException("Passwords do not match");
        }

        User newUser = User.builder()
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .dob(signUpDto.getDob())
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .address(signUpDto.getAddress())
                .taskList(new ArrayList<>())
                .build();
        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public String login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        boolean dbUser = userRepository.existsByEmail(email);
        User existingDbUser = userRepository.findByEmail(email);

        if(email.isEmpty() || password.isEmpty()) {
            throw new IncorrectValueEntryException("You have incorrectly entered values");
        }
        if(!dbUser) {
           throw new NotFoundException("User with " + email + " does not exist");
        }

        if(!existingDbUser.getPassword().equals(password)) {
            throw new IncorrectValueEntryException("Email or Password Incorrect");
        }

        httpSession.setAttribute("User_id", existingDbUser.getId());
        httpSession.setAttribute("Permission", "User");

        return "Login in Successful";
    }

    @Override
    public User updateUserDetails(UpdateUserDetailsDto updateUserDto) {
        Long userId = utils.getLoggedUserById();
        User user = utils.findUserById(userId);

        if(!user.getFirstName().equals(updateUserDto.getFirstName()) && Objects.nonNull(updateUserDto.getFirstName())) {
            user.setFirstName(updateUserDto.getFirstName());
        }
        if(!user.getLastName().equals(updateUserDto.getLastName()) && Objects.nonNull(updateUserDto.getLastName())) {
            user.setLastName(updateUserDto.getLastName());
        }
        if(!user.getAddress().equals(updateUserDto.getAddress()) && Objects.nonNull(updateUserDto.getAddress())) {
            user.setAddress(updateUserDto.getAddress());
        }

        userRepository.save(user);
        return user;
    }

    @Override
    public String logout() {
        httpSession.invalidate();
        return "User Logged Out" ;
    }


}
