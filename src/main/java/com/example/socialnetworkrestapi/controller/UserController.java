package com.example.socialnetworkrestapi.controller;

import com.example.socialnetworkrestapi.dto.PostDto;
import com.example.socialnetworkrestapi.dto.UserDto;
import com.example.socialnetworkrestapi.entity.PostEntity;
import com.example.socialnetworkrestapi.entity.UserEntity;
import com.example.socialnetworkrestapi.repository.PostRepository;
import com.example.socialnetworkrestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController
{
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") int id)
    {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        
        if (userEntity == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        UserDto result = UserDto.fromUserEntity(userEntity);
        return new ResponseEntity<UserDto>(result, HttpStatus.OK);
    }
    
    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(@PathVariable("userId") int userId)
    {
        List<PostEntity> postEntities = postRepository.getByUserId(userId);
        List<PostDto> postDtos = new ArrayList<>(postEntities.size());
        for (PostEntity postEntity : postEntities)
        {
            postDtos.add(PostDto.fromPostEntity(postEntity));
        }
        
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
}
