package com.optd.api;

import com.optd.common.dto.ApiSuccess;
import com.optd.common.dto.security.LoginDto;
import com.optd.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/optd/auth")
public class AuthAPI {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(new ApiSuccess("Giriş Başarılı", authService.login(loginDto)), HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody LoginDto loginDto){
        authService.register(loginDto);
        return new ResponseEntity<>(new ApiSuccess("Kayıt Başarılı",null), HttpStatus.OK);
    }

}