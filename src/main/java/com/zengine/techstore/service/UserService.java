package com.zengine.techstore.service;

import com.zengine.techstore.dto.ChangeUserDto;
import com.zengine.techstore.dto.UserRegistrationDto;
import com.zengine.techstore.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDto registration);
    User saveEdit(ChangeUserDto userDto);
}
