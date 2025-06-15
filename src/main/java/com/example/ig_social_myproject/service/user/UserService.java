package com.example.ig_social_myproject.service.user;

import com.example.ig_social_myproject.model.dto.UserDTO;
import com.example.ig_social_myproject.model.request.UserRequest;
import com.example.ig_social_myproject.entity.User;
import com.example.ig_social_myproject.exception.DuplicateResourceException;
import com.example.ig_social_myproject.exception.ResourceNotFoundException;
import com.example.ig_social_myproject.repository.UserRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // @Transactional(noRollbackFor = DuplicateResourceException.class)
    public UserDTO createUser(UserRequest userRequest) {
        // Kiểm tra trùng username/email
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new DuplicateResourceException("Tên đăng nhập đã tồn tại");
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateResourceException("Email đã tồn tại");
        }

        // Tạo user mới
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        user.setFullName(userRequest.getFullName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setProfilePicture(userRequest.getProfilePicture());
        user.setBio(userRequest.getBio());
        user.setCreatedAt(LocalDateTime.now());

        user = userRepository.save(user);
        return mapToUserDTO(user);
    }

    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));
        return mapToUserDTO(user);
    }

    public UserDTO updateUser(Integer userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));

        if (userDTO.getUsername() != null && !userDTO.getUsername().equals(user.getUsername())) {
            if (userRepository.existsByUsername(userDTO.getUsername())) {
                throw new DuplicateResourceException("Tên đăng nhập đã tồn tại");
            }
            user.setUsername(userDTO.getUsername());
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                throw new DuplicateResourceException("Email đã tồn tại");
            }
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getFullName() != null)
            user.setFullName(userDTO.getFullName());
        if (userDTO.getPhoneNumber() != null)
            user.setPhoneNumber(userDTO.getPhoneNumber());
        if (userDTO.getProfilePicture() != null)
            user.setProfilePicture(userDTO.getProfilePicture());
        if (userDTO.getBio() != null)
            user.setBio(userDTO.getBio());
        if (userDTO.getIsPrivate() != null)
            user.setIsPrivate(userDTO.getIsPrivate());
        if (userDTO.getIsVerified() != null)
            user.setIsVerified(userDTO.getIsVerified());

        user = userRepository.save(user);
        return mapToUserDTO(user);
    }

    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .userID(user.getUserID())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .profilePicture(user.getProfilePicture())
                .bio(user.getBio())
                .isPrivate(user.getIsPrivate())
                .isVerified(user.getIsVerified())
                .createdAt(user.getCreatedAt())
                .build();
    }
}