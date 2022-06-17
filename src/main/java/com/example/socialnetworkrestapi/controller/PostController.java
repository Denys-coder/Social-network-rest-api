package com.example.socialnetworkrestapi.controller;

import com.example.socialnetworkrestapi.dto.NewPostDto;
import com.example.socialnetworkrestapi.dto.PostDto;
import com.example.socialnetworkrestapi.entity.PostEntity;
import com.example.socialnetworkrestapi.entity.UserEntity;
import com.example.socialnetworkrestapi.repository.PostRepository;
import com.example.socialnetworkrestapi.repository.UserRepository;
import com.example.socialnetworkrestapi.security.JwtAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController
{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") long postId)
    {
        PostEntity postEntity = postRepository.getById(postId).orElse(null);
        
        if (postEntity == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        PostDto postDto = PostDto.fromPostEntity(postEntity);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<?> newPost(@RequestBody NewPostDto post, HttpServletRequest request)
    {
        String email = jwtAuthorizationFilter.getUsernamePasswordAuthentication(request).getName();
        UserEntity userEntity = userRepository.findByEmail(email).get();
        PostEntity postEntity = new PostEntity(post.getHeader(), post.getText(), userEntity);
        
        postRepository.save(postEntity);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") long postId, HttpServletRequest request)
    {
        // check is user trying to delete his own post
        String email = jwtAuthorizationFilter.getUsernamePasswordAuthentication(request).getName();
        UserEntity userEntity = userRepository.findByEmail(email).get();
        if (postRepository.getById(postId).get().getUser().getId() != userEntity.getId())
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        postRepository.deleteById(postId);
        
        return new ResponseEntity<PostDto>(HttpStatus.OK);
    }
    
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable("postId") long postId, @RequestBody PostDto postDto, HttpServletRequest request)
    {
        // check is user trying to update his own post
        String email = jwtAuthorizationFilter.getUsernamePasswordAuthentication(request).getName();
        UserEntity userEntity = userRepository.findByEmail(email).get();
        if (postRepository.getById(postId).get().getUser().getId() != userEntity.getId())
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        PostEntity postEntity = postRepository.getById(postId).orElse(null);
        if (postEntity == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        postEntity.setHeader(postDto.getHeader());
        postEntity.setText(postDto.getText());
        
        postRepository.save(postEntity);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
