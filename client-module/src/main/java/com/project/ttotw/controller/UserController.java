package com.project.ttotw.controller;

import com.project.ttotw.dto.ApiResponse;
import com.project.ttotw.dto.UserRequestDto;
import com.project.ttotw.entity.User;
import com.project.ttotw.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    private final ApiResponse response;

//    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequestDto.Login login, Errors errors) {
        if (errors.hasErrors()) {
            return response.fail(errors);
        }

        return userService.login(login);
    }
}
