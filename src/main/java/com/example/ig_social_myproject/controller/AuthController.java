package com.example.ig_social_myproject.controller;

import com.example.ig_social_myproject.model.dto.UserDTO;
import com.example.ig_social_myproject.model.request.auth.LoginRequest;
import com.example.ig_social_myproject.model.response.ResponseHandler;
import com.example.ig_social_myproject.service.user.UserService;
import com.example.ig_social_myproject.utils.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountLockedException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final ResponseHandler responseHandler;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, ResponseHandler responseHandler,
            JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.responseHandler = responseHandler;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        // Kiểm tra validation errors
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return responseHandler.responseError(errorMessage, org.springframework.http.HttpStatus.BAD_REQUEST);
        }

        try {
            System.out.println("Login attempt for username: " + loginRequest.getUsername());

            // Xác thực user credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // Cập nhật last_login
            userService.updateLastLogin(loginRequest.getUsername());

            // Tạo JWT token
            String token = jwtTokenProvider.generateToken(loginRequest.getUsername());

            // Lấy thông tin user
            UserDTO user = userService.getUserByUsername(loginRequest.getUsername());

            return responseHandler.responseSuccess("Đăng nhập thành công", Map.of("user", user, "token", token));

        } catch (DisabledException e) {
            return responseHandler.responseError("Tài khoản đã bị vô hiệu hóa",
                    org.springframework.http.HttpStatus.FORBIDDEN);

        } catch (BadCredentialsException e) {
            System.err.println("Bad credentials for user: " + loginRequest.getUsername());
            return responseHandler.responseError("Tên đăng nhập hoặc mật khẩu không đúng",
                    org.springframework.http.HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            e.printStackTrace();
            return responseHandler.responseError("Lỗi hệ thống: " + e.getMessage(),
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Test endpoint để debug
    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody Map<String, String> request) {
        return responseHandler.responseSuccess("Test thành công", request);
    }
}