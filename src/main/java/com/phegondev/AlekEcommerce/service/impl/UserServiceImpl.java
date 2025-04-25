package com.phegondev.AlekEcommerce.service.impl;

import com.phegondev.AlekEcommerce.dto.Response;
import com.phegondev.AlekEcommerce.dto.UserDto;
import com.phegondev.AlekEcommerce.entity.User;
import com.phegondev.AlekEcommerce.enums.UserRole;
import com.phegondev.AlekEcommerce.exception.InvalidCredentialException;
import com.phegondev.AlekEcommerce.exception.NotFoundException;
import com.phegondev.AlekEcommerce.mapper.EntityDtoMapper;
import com.phegondev.AlekEcommerce.repository.UserRepo;
import com.phegondev.AlekEcommerce.security.JwtUtils;
import com.phegondev.AlekEcommerce.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new NotFoundException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Password does not match");
        }
        String token = jwtUtils.generateToken(user);
        return Response.builder()
                .status(200)
                .message("User logged in successfully")
                .token(token)
                .expirationTime("6 months")
                .role(user.getRole().name())
                .build();

    }

    @Override
    public Response getAllUsers() {

        List<User> user = userRepo.findAll();
        List<UserDto> userDtos = user.stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Users retrieved successfully")
                .userList(userDtos)
                .build();
    }

    @Override
    public User getLoginUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        log.info("User email is: " + email);
        return userRepo.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User not found"));
    }

    @Override
    public Response getUserInfoAndOrderHistory() {
        User user = getLoginUser();
        UserDto userDto = entityDtoMapper.mapUserToDtoPlusAddressAndOrderItemHistory(user);
        return Response.builder()
                .status(200)
                .message("User info and order history retrieved successfully")
                .user(userDto)
                .build();
    }
}
