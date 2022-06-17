package com.example.socialnetworkrestapi.service;

import com.example.socialnetworkrestapi.entity.PostEntity;
import com.example.socialnetworkrestapi.entity.UserEntity;
import com.example.socialnetworkrestapi.repository.PostRepository;
import com.example.socialnetworkrestapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    
    public DBInit(UserRepository userRepository, PostRepository postRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) {
        
        // crete users
        UserEntity user1 = new UserEntity("name1", "surname1",10,
                "about1",passwordEncoder.encode("pass1"), "email1");
        UserEntity user2 = new UserEntity("name2", "surname2",20,
                "about2",passwordEncoder.encode("pass2"), "email2");
        UserEntity user3 = new UserEntity("name3", "surname3",30,
                "about3",passwordEncoder.encode("pass3"), "email3");
        
        // create posts
        PostEntity post1 = new PostEntity("header1", "text1", user1);
        PostEntity post2 = new PostEntity("header2", "text2", user2);
        
        // link users and posts
        user1.getPosts().add(post1);
        user2.getPosts().add(post2);
    
        // save in database
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        postRepository.save(post1);
        postRepository.save(post2);
    }
}