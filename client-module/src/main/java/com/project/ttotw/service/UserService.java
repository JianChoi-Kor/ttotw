package com.project.ttotw.service;

import com.project.ttotw.dto.UserRequestDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ResponseEntity<?> signin(HttpServletRequest request, UserRequestDto.SignIn signIn);
    ResponseEntity<?> reissue(HttpServletRequest request);
    ResponseEntity<?> signup(UserRequestDto.SignUp signUp);
}
