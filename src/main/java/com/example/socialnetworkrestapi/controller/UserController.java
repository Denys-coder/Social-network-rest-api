package com.example.socialnetworkrestapi.controller;

import com.example.socialnetworkrestapi.dto.UserDto;
import com.example.socialnetworkrestapi.entity.UserEntity;
import com.example.socialnetworkrestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController
{
    private final UserRepository userRepository;
    
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") int id)
    {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        
        if (userEntity == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        UserDto result = UserDto.fromUser(userEntity);
        return new ResponseEntity<UserDto>(result, HttpStatus.OK);
    }
    
    @GetMapping("{userId}/posts")
    public String getAllPosts(@PathVariable("userId") int userId)
    {
        return "all posts";
    }
    
    @GetMapping("{userId}/post/{postId}")
    public String getPostById(@PathVariable("userId") int userId, @PathVariable int postId)
    {
        return "post by id";
    }
}
