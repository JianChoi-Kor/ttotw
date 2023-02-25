package com.project.ttotw.controller;

import com.project.ttotw.dto.ApiResponse;
import com.project.ttotw.dto.UserRequestDto;
import com.project.ttotw.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final ApiResponse response;

    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid UserRequestDto.Login login, Errors errors) {
        if (errors.hasErrors()) {
            return response.fail(errors);
        }

        return userService.login(login);
    }
}
