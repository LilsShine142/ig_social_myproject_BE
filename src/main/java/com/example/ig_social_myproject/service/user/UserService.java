// package com.example.ig_social_myproject.service.user;

// import com.example.ig_social_myproject.model.dto.UserDTO;
// import com.example.ig_social_myproject.model.request.user.UserRequest;
// import com.example.ig_social_myproject.entity.User;
// import com.example.ig_social_myproject.exception.DuplicateResourceException;
// import com.example.ig_social_myproject.exception.ResourceNotFoundException;
// import com.example.ig_social_myproject.repository.UserRepository;
// import com.example.ig_social_myproject.service.role.RoleService;

// import java.time.LocalDateTime;
// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.LockedException;
// import org.springframework.security.authentication.DisabledException;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserService implements UserDetailsService {
//     private final UserRepository userRepository;
//     private final BCryptPasswordEncoder passwordEncoder;
//     private final RoleService roleService;

//     @Autowired
//     public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.roleService = roleService;
//     }

//     public UserDTO createUser(UserRequest userRequest) {
//         // Kiểm tra trùng username/email
//         if (userRepository.existsByUsername(userRequest.getUsername())) {
//             throw new DuplicateResourceException("Tên đăng nhập đã tồn tại");
//         }
//         if (userRepository.existsByEmail(userRequest.getEmail())) {
//             throw new DuplicateResourceException("Email đã tồn tại");
//         }

//         // Tạo user mới
//         User user = new User();
//         user.setUsername(userRequest.getUsername());
//         user.setEmail(userRequest.getEmail());
//         user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
//         user.setFullName(userRequest.getFullName());
//         user.setPhoneNumber(userRequest.getPhoneNumber());
//         user.setProfilePicture(userRequest.getProfilePicture());
//         user.setBio(userRequest.getBio());
//         user.setCreatedAt(LocalDateTime.now());

//         // Set các trường mới
//         user.setRoleId(userRequest.getRoleId() != null ? userRequest.getRoleId() : 1); // Default role
//         user.setIsActive(userRequest.getIsActive() != null ? userRequest.getIsActive() : true);
//         user.setIsLocked(userRequest.getIsLocked() != null ? userRequest.getIsLocked() : false);
//         user.setIsPrivate(userRequest.getIsPrivate() != null ? userRequest.getIsPrivate() : false);
//         user.setIsVerified(userRequest.getIsVerified() != null ? userRequest.getIsVerified() : false);

//         user = userRepository.save(user);
//         return mapToUserDTO(user);
//     }

//     public UserDTO getUserById(Integer userId) {
//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));
//         return mapToUserDTO(user);
//     }

//     public UserDTO getUserByUsername(String username) {
//         User user = userRepository.findByUsername(username)
//                 .orElseThrow(() -> new ResourceNotFoundException(
//                         "Không tìm thấy người dùng với tên đăng nhập: " + username));
//         return mapToUserDTO(user);
//     }

//     public UserDTO updateUser(Integer userId, UserDTO userDTO) {
//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));

//         if (userDTO.getUsername() != null && !userDTO.getUsername().equals(user.getUsername())) {
//             if (userRepository.existsByUsername(userDTO.getUsername())) {
//                 throw new DuplicateResourceException("Tên đăng nhập đã tồn tại");
//             }
//             user.setUsername(userDTO.getUsername());
//         }

//         if (userDTO.getEmail() != null && !userDTO.getEmail().equals(user.getEmail())) {
//             if (userRepository.existsByEmail(userDTO.getEmail())) {
//                 throw new DuplicateResourceException("Email đã tồn tại");
//             }
//             user.setEmail(userDTO.getEmail());
//         }

//         // Cập nhật các trường khác
//         if (userDTO.getFullName() != null)
//             user.setFullName(userDTO.getFullName());
//         if (userDTO.getPhoneNumber() != null)
//             user.setPhoneNumber(userDTO.getPhoneNumber());
//         if (userDTO.getProfilePicture() != null)
//             user.setProfilePicture(userDTO.getProfilePicture());
//         if (userDTO.getBio() != null)
//             user.setBio(userDTO.getBio());
//         if (userDTO.getIsPrivate() != null)
//             user.setIsPrivate(userDTO.getIsPrivate());
//         if (userDTO.getIsVerified() != null)
//             user.setIsVerified(userDTO.getIsVerified());
//         if (userDTO.getRoleId() != null)
//             user.setRoleId(userDTO.getRoleId());
//         if (userDTO.getIsActive() != null)
//             user.setIsActive(userDTO.getIsActive());
//         if (userDTO.getIsLocked() != null)
//             user.setIsLocked(userDTO.getIsLocked());

//         user = userRepository.save(user);
//         return mapToUserDTO(user);
//     }

//     public void deleteUser(Integer userId) {
//         if (!userRepository.existsById(userId)) {
//             throw new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId);
//         }
//         userRepository.deleteById(userId);
//     }

//     private UserDTO mapToUserDTO(User user) {
//         UserDTO.UserDTOBuilder builder = UserDTO.builder()
//                 .userID(user.getUserID())
//                 .username(user.getUsername())
//                 .email(user.getEmail())
//                 .fullName(user.getFullName())
//                 .phoneNumber(user.getPhoneNumber())
//                 .profilePicture(user.getProfilePicture())
//                 .bio(user.getBio())
//                 .isPrivate(user.getIsPrivate())
//                 .isVerified(user.getIsVerified())
//                 .createdAt(user.getCreatedAt())
//                 .roleId(user.getRoleId())
//                 .isActive(user.getIsActive())
//                 .isLocked(user.getIsLocked())
//                 .lastLogin(user.getLastLogin());

//         // // Lấy role name nếu có relationship với Role entity
//         // try {
//         // if (user.getRole() != null) {
//         // String roleName = roleService.getRoleNameByRoleId(user.getRoleId());
//         // builder.roleName(roleName);
//         // }
//         // } catch (Exception e) {
//         // // Nếu chưa có relationship hoặc lazy loading failed, bỏ qua
//         // }

//         return builder.build();
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByUsername(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));

//         // Kiểm tra trạng thái tài khoản
//         if (Boolean.TRUE.equals(user.getIsLocked())) {
//             throw new LockedException("Tài khoản đã bị khóa");
//         }

//         if (Boolean.FALSE.equals(user.getIsActive())) {
//             throw new DisabledException("Tài khoản đã bị vô hiệu hóa");
//         }

//         // Tạo authorities
//         Set<GrantedAuthority> authorities = new HashSet<>();

//         // Thêm default role
//         authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

//         // Nếu có roleId, thêm role từ database thông qua RoleService
//         try {
//             if (user.getRoleId() != null) {
//                 String roleName = roleService.getRoleNameByRoleId(user.getRoleId());
//                 if (roleName != null && !roleName.equals("UNKNOWN") && !roleName.equals("ERROR")) {
//                     authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
//                 }
//             }
//         } catch (Exception e) {
//             System.out.println("Warning: Could not load role for user " + username + ". Using default ROLE_USER");
//         }

//         return org.springframework.security.core.userdetails.User.builder()
//                 .username(user.getUsername())
//                 .password(user.getPasswordHash())
//                 .authorities(authorities)
//                 .accountExpired(false)
//                 .accountLocked(Boolean.TRUE.equals(user.getIsLocked()))
//                 .credentialsExpired(false)
//                 .disabled(Boolean.FALSE.equals(user.getIsActive()))
//                 .build();
//     }

//     public void updateLastLogin(String username) {
//         User user = userRepository.findByUsername(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));

//         user.setLastLogin(LocalDateTime.now());
//         userRepository.save(user);
//     }

//     // Helper method để kiểm tra user có active không
//     public boolean isUserActive(String username) {
//         User user = userRepository.findByUsername(username).orElse(null);
//         return user != null && Boolean.TRUE.equals(user.getIsActive()) && !Boolean.TRUE.equals(user.getIsLocked());
//     }

//     // Helper method để lock/unlock user
//     public void lockUser(Integer userId, boolean locked) {
//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));
//         user.setIsLocked(locked);
//         userRepository.save(user);
//     }

//     // Helper method để activate/deactivate user
//     public void activateUser(Integer userId, boolean active) {
//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));
//         user.setIsActive(active);
//         userRepository.save(user);
//     }
// }

package com.example.ig_social_myproject.service.user;

import com.example.ig_social_myproject.model.dto.UserDTO;
import com.example.ig_social_myproject.model.request.user.UserRequest;
import com.example.ig_social_myproject.entity.User;
import com.example.ig_social_myproject.exception.DuplicateResourceException;
import com.example.ig_social_myproject.exception.ResourceNotFoundException;
import com.example.ig_social_myproject.repository.UserRepository;
import com.example.ig_social_myproject.service.role.RoleService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

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

        // Set default role nếu không có roleId
        Integer roleId = userRequest.getRoleId();
        if (roleId == null) {
            // Lấy default role từ RoleService
            try {
                roleId = roleService.getDefaultRole().getRoleId();
            } catch (Exception e) {
                // Fallback: set roleId = 1 (USER)
                roleId = 1;
                System.out.println("Warning: Could not get default role, using roleId = 1");
            }
        }

        user.setRoleId(roleId);
        user.setIsActive(userRequest.getIsActive() != null ? userRequest.getIsActive() : true);
        user.setIsLocked(userRequest.getIsLocked() != null ? userRequest.getIsLocked() : false);
        user.setIsPrivate(userRequest.getIsPrivate() != null ? userRequest.getIsPrivate() : false);
        user.setIsVerified(userRequest.getIsVerified() != null ? userRequest.getIsVerified() : false);

        user = userRepository.save(user);
        return mapToUserDTO(user);
    }

    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));
        return mapToUserDTO(user);
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người dùng với tên đăng nhập: " + username));
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

        // Cập nhật các trường khác
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
        if (userDTO.getRoleId() != null)
            user.setRoleId(userDTO.getRoleId());
        if (userDTO.getIsActive() != null)
            user.setIsActive(userDTO.getIsActive());
        if (userDTO.getIsLocked() != null)
            user.setIsLocked(userDTO.getIsLocked());

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
        UserDTO.UserDTOBuilder builder = UserDTO.builder()
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
                .roleId(user.getRoleId())
                .isActive(user.getIsActive())
                .isLocked(user.getIsLocked())
                .lastLogin(user.getLastLogin());

        return builder.build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));

        // Kiểm tra trạng thái tài khoản
        if (Boolean.TRUE.equals(user.getIsLocked())) {
            throw new LockedException("Tài khoản đã bị khóa");
        }

        if (Boolean.FALSE.equals(user.getIsActive())) {
            throw new DisabledException("Tài khoản đã bị vô hiệu hóa");
        }

        // Tạo authorities
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Thêm default role
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Nếu có roleId, thêm role từ database thông qua RoleService
        try {
            if (user.getRoleId() != null) {
                String roleName = roleService.getRoleNameByRoleId(user.getRoleId());
                if (roleName != null && !roleName.equals("UNKNOWN") && !roleName.equals("ERROR")) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
                }
            }
        } catch (Exception e) {
            System.out.println("Warning: Could not load role for user " + username + ". Using default ROLE_USER");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(Boolean.TRUE.equals(user.getIsLocked()))
                .credentialsExpired(false)
                .disabled(Boolean.FALSE.equals(user.getIsActive()))
                .build();
    }

    public void updateLastLogin(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    // Helper method để kiểm tra user có active không
    public boolean isUserActive(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return user != null && Boolean.TRUE.equals(user.getIsActive()) && !Boolean.TRUE.equals(user.getIsLocked());
    }

    // Helper method để lock/unlock user
    public void lockUser(Integer userId, boolean locked) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));
        user.setIsLocked(locked);
        userRepository.save(user);
    }

    // Helper method để activate/deactivate user
    public void activateUser(Integer userId, boolean active) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + userId));
        user.setIsActive(active);
        userRepository.save(user);
    }
}