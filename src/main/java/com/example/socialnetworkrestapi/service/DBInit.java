package com.example.socialnetworkrestapi.service;

import com.example.socialnetworkrestapi.entity.UserEntity;
import com.example.socialnetworkrestapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public DBInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();
        
        // Crete users
        UserEntity user1 = new UserEntity("name1", "surname1",10,
                "about1",passwordEncoder.encode("pass1"), "email1");
        List<UserEntity> users = Arrays.asList(user1);
        
        // Save to db
        this.userRepository.saveAll(users);
    }
}