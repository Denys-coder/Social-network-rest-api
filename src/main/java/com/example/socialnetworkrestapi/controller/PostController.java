package com.example.socialnetworkrestapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController
{
    @PostMapping("")
    public String newPost()
    {
        return "new post was created";
    }
    
    @DeleteMapping("/{postId}")
    public String deletePost()
    {
        return "post was deleted";
    }
    
    @PatchMapping ("/{postId}")
    public String updatePost()
    {
        return "post was updated";
    }
}
