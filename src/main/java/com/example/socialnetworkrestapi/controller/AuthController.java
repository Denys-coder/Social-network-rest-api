package com.example.socialnetworkrestapi.controller;

import com.example.socialnetworkrestapi.dto.RegisterDto;
import com.example.socialnetworkrestapi.entity.UserEntity;
import com.example.socialnetworkrestapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto)
    {
        UserEntity userEntity = new UserEntity(registerDto.getName(), registerDto.getSurname(), registerDto.getAge(),
                registerDto.getAboutMe(), passwordEncoder.encode(registerDto.getPassword()), registerDto.getEmail());
        userRepository.save(userEntity);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
