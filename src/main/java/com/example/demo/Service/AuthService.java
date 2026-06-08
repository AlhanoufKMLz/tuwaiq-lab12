package com.example.demo.Service;

import com.example.demo.DTO.IN.UserDTOIN;
import com.example.demo.Model.User;
import com.example.demo.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void register(UserDTOIN user){
        User newUser = new User();
        newUser.setRole("USER");
        newUser.setUsername(user.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        newUser.setPassword(hashPassword);

        authRepository.save(newUser);
    }
}

