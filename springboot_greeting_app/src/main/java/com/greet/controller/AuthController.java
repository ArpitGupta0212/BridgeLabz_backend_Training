package com.greet.controller;

import com.greet.dto.UserLoginDto;
import com.greet.dto.UserRegistrationDto;
import com.greet.dto.UserResponseDto;
import com.greet.model.Role;
import com.greet.model.User;
import com.greet.service.UserService;
import com.greet.util.JwtUtil;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());
        user.setRole(registrationDto.getRole() == null ? Role.USER : registrationDto.getRole());

        if (userService.register(user)) {
            UserResponseDto responseDto = new UserResponseDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole()
            );
            return ResponseEntity.ok(Map.of(
                    "message", "User registered successfully",
                    "user", responseDto
            ));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "Username is already taken"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto loginDto) {
        return userService.login(loginDto.getUsername(), loginDto.getPassword())
                .<ResponseEntity<?>>map(user -> {
                    String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
                    return ResponseEntity.ok(Map.of(
                            "message", "Login successful",
                            "token", "Bearer " + token,
                            "role", user.getRole()
                    ));
                })
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Invalid username or password")));
    }
}
