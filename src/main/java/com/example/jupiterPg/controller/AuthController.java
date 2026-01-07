package com.example.jupiterPg.controller;

import com.example.jupiterPg.dto.LoginRequest;
import com.example.jupiterPg.model.Admin;
import com.example.jupiterPg.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ✅ ADMIN SIGN UP
    @PostMapping("/register")
    public String register(@RequestBody Admin admin) {
        authService.register(admin);
        return "Admin registered successfully";
    }

    // ✅ ADMIN LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
    }
}
