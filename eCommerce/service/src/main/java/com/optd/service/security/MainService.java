package com.optd.service.security;

import com.optd.entity.User;
import com.optd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class MainService {

    @Autowired
    UserRepository userRepository;

    protected Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUserId();
    }
}
