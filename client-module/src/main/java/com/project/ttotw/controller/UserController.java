package com.project.ttotw.controller;

import com.project.ttotw.dto.ApiResponse;
import com.project.ttotw.dto.UserRequestDto;
import com.project.ttotw.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserRequestDto.SignUp signUp, Errors errors) {
        if (errors.hasErrors()) {
            return response.fail(errors);
        }

        return userService.signup(signUp);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid UserRequestDto.SignIn signIn, Errors errors) {
        if (errors.hasErrors()) {
            return response.fail(errors);
        }

        return userService.signin(signIn);
    }

    @PostMapping(value = "/reissue")
    public ResponseEntity<?> reissue() {
        return null;
    }
}
