package com.optd.service.security;

import com.optd.common.dto.security.AuthResponseDto;
import com.optd.common.dto.security.LoginDto;
import com.optd.entity.User;
import com.optd.repository.UserRepository;
import com.optd.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserValidator userValidator;


    public AuthResponseDto login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername().trim().toLowerCase(), loginDto.getPassword()));
        User user = userRepository.findByUsername(loginDto.getUsername().trim().toLowerCase()).orElse(null);
        String jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .token(jwtToken)
                .build();
    }

    public void register(LoginDto loginDto) {
        userValidator.checkUserName(loginDto);
        User user = User.builder()
                .username(loginDto.getUsername())
                .password(passwordEncoder.encode(loginDto.getPassword()))
                .build();
        userRepository.save(user);
    }

}
