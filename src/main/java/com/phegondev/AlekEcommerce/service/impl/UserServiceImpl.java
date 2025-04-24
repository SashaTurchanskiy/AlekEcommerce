package com.phegondev.AlekEcommerce.service.impl;

import com.phegondev.AlekEcommerce.dto.Response;
import com.phegondev.AlekEcommerce.dto.UserDto;
import com.phegondev.AlekEcommerce.entity.User;
import com.phegondev.AlekEcommerce.enums.UserRole;
import com.phegondev.AlekEcommerce.mapper.EntityDtoMapper;
import com.phegondev.AlekEcommerce.repository.UserRepo;
import com.phegondev.AlekEcommerce.security.JwtUtils;
import com.phegondev.AlekEcommerce.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response registerUser(UserDto registrationRequest) {
        UserRole role = UserRole.USER;

        if (registrationRequest.getRole() != null && registrationRequest.getRole().equalsIgnoreCase("admin")) {
            role = UserRole.ADMIN;
        }

        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .password(registrationRequest.getPassword())
                .role(role)
                .build();
       User savedUser = userRepo.save(user);

       UserDto userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);
       return Response.builder()
               .status(201)
               .message("User registered successfully")
               .user(userDto)
               .build();

    }

    @Override
    public Response loginUser(UserDto loginRequest) {
        return null;
    }

    @Override
    public Response getAllUsers() {
        return null;
    }

    @Override
    public User getLoginUser() {
        return null;
    }

    @Override
    public Response getUserInfoAndOrderHistory() {
        return null;
    }
}
