package com.project.ttotw.service;

import com.project.ttotw.dto.UserRequestDto;
import com.project.ttotw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> login(UserRequestDto.Login login) {
        return null;
    }
}
