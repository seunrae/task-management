package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.repository.UserRepository;
import com.example.TaskManagement.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
