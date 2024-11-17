package com.audora.weatherapp.controller;

import com.audora.weatherapp.entity.User;
import com.audora.weatherapp.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(user);
    }
}

@Getter
@Setter
class LoginRequest {
    private String email;
    private String password;
}
