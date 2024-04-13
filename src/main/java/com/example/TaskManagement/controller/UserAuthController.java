package com.example.TaskManagement.controller;

import com.example.TaskManagement.dto.JwtResponse;
import com.example.TaskManagement.dto.LogInRequest;
import com.example.TaskManagement.dto.SignInRequest;
import com.example.TaskManagement.models.User;
import com.example.TaskManagement.service.UserAuthService;
import com.example.TaskManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserAuthController {
    private final UserAuthService userAuthService;
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(userAuthService.createUser(signInRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> logIn(@RequestBody LogInRequest logInRequest) {
        return ResponseEntity.ok(userAuthService.logIn(logInRequest));
    }
}
