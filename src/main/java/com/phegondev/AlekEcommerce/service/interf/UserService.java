package com.phegondev.AlekEcommerce.service.interf;

import com.phegondev.AlekEcommerce.dto.Response;
import com.phegondev.AlekEcommerce.dto.UserDto;
import com.phegondev.AlekEcommerce.entity.User;

public interface UserService {
    Response registerUser(UserDto registrationRequest);
    Response loginUser(UserDto loginRequest);
    Response getAllUsers();
    User getLoginUser();
    Response getUserInfoAndOrderHistory();
}
