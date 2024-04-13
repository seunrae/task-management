package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.dto.JwtResponse;
import com.example.TaskManagement.dto.LogInRequest;
import com.example.TaskManagement.dto.SignInRequest;
import com.example.TaskManagement.models.ROLE;
import com.example.TaskManagement.models.User;
import com.example.TaskManagement.repository.UserRepository;
import com.example.TaskManagement.service.JwtService;
import com.example.TaskManagement.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public User createUser(SignInRequest signInRequest) {
        User user = User.builder()
                .firstname(signInRequest.getFirstname())
                .lastname(signInRequest.getLastname())
                .email(signInRequest.getEmail())
                .password(passwordEncoder.encode(signInRequest.getPassword()))
                .role(ROLE.USER)
                .tasks(new ArrayList<>())
                .projects(new ArrayList<>())
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public JwtResponse logIn(LogInRequest logInRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getEmail(), logInRequest.getPassword()));

            User user = userRepository.findByEmail(logInRequest.getEmail()).orElseThrow(() -> new IllegalStateException("email or password not correct"));
            var jwt = jwtService.generateToken(user);
            JwtResponse jwtResponse = new JwtResponse();

            jwtResponse.setId(user.getId());
            jwtResponse.setFirstname(user.getFirstname());
            jwtResponse.setLastname(user.getLastname());
            jwtResponse.setEmail(user.getEmail());
            jwtResponse.setToken(jwt);
            jwtResponse.setCreatedAt(user.getCreatedAt());
            jwtResponse.setTasks(new ArrayList<>());
            jwtResponse.setProjects(new ArrayList<>());

            return jwtResponse;
        }
        catch (Exception e) {
            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setToken(e.getMessage());
            return jwtResponse;
        }
    }
}
