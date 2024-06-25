package com.springboot.blog.controller;

import com.springboot.blog.payload.JWTAuthResponse;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.SignupDto;
import com.springboot.blog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/auth")
@Tag(
        name = "CRUD REST APIs for User Resource"
)
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Login REST API
    @Operation(
            summary = "Authenticate a User REST API",
            description = "Authenticate a User with email or username, and password"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PostMapping(value = {"/api/v1/auth/login", "/api/v1/auth/signin"})
    public ResponseEntity<JWTAuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        // return JWTAuthResponse DTO
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }


    // Signup REST API
    @Operation(
            summary = "Create a User REST API",
            description = "Create a new User and save to database"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PostMapping(value = {"/api/v1/auth/signup", "/api/v1/auth/register"})
    public ResponseEntity<String> signup(@Valid @RequestBody SignupDto signupDto) {
        String response = authService.signup(signupDto);
        return ResponseEntity.ok(response);
    }
}
