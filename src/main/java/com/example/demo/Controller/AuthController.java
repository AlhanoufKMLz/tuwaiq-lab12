package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.DTO.IN.UserDTOIN;
import com.example.demo.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    public final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTOIN user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("Welcome!"));
    }
}
