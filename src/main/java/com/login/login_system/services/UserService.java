package com.login.login_system.services;

import com.login.login_system.dto.RegisterRequest;
import com.login.login_system.entities.User;
import com.login.login_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(RegisterRequest request){
        User userToSave = new User(request);
        userRepository.save(userToSave);
    }

    public UserDetails findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
