package com.login.login_system.services;

import com.login.login_system.dto.RegisterRequest;
import com.login.login_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private void registerUser(RegisterRequest request){

    }
}
