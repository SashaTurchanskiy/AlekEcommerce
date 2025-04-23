package com.phegondev.AlekEcommerce.security;

import com.phegondev.AlekEcommerce.entity.User;
import com.phegondev.AlekEcommerce.exception.NotFoundException;
import com.phegondev.AlekEcommerce.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepo.findByEmail(username)
               .orElseThrow(()-> new NotFoundException("Email not found"));
       return AuthUser.builder()
               .user(user)
               .build();
    }
}
