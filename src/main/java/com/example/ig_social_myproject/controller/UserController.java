package com.example.ig_social_myproject.controller;

import com.example.ig_social_myproject.exception.ResourceNotFoundException;
import com.example.ig_social_myproject.model.dto.UserDTO;
import com.example.ig_social_myproject.model.response.ResponseHandler;
import com.example.ig_social_myproject.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ResponseHandler responseHandler;

    @Autowired
    public UserController(UserService userService, ResponseHandler responseHandler) {
        this.userService = userService;
        this.responseHandler = responseHandler;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO response = userService.createUser(userDTO);
        return responseHandler.responseCreated("Tạo người dùng thành công", response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Integer userId) {
        try {
            UserDTO response = userService.getUserById(userId);
            return responseHandler.responseSuccess("Lấy thông tin người dùng thành công", response);
        } catch (ResourceNotFoundException ex) {
            // System.err.println("Lỗi: " + ex.getMessage());
            return responseHandler.handleNotFound(ex.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDTO userDTO) {
        UserDTO response = userService.updateUser(userId, userDTO);
        return responseHandler.responseSuccess("Cập nhật người dùng thành công", response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return responseHandler.responseSuccess("Xóa người dùng thành công", null);
    }
}