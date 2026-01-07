package com.example.jupiterPg.service;

import com.example.jupiterPg.model.Admin;
import com.example.jupiterPg.repository.AdminRepository;
import com.example.jupiterPg.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_success() {
        Admin admin = new Admin();
        admin.setEmail("admin@test.com");
        admin.setPassword("1234");

        when(adminRepository.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString()))
                .thenReturn("encoded");

        authService.register(admin);

        verify(adminRepository).save(any(Admin.class));
    }

    @Test
    void login_success() {
        Admin admin = new Admin();
        admin.setEmail("admin@test.com");
        admin.setPassword("encoded");

        when(adminRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(anyString(), anyString()))
                .thenReturn(true);
        when(jwtUtil.generateToken(anyString()))
                .thenReturn("jwt-token");

        String token = authService.login("admin@test.com", "1234");

        assertEquals("jwt-token", token);
    }
}
