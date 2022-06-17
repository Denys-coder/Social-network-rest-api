package com.example.socialnetworkrestapi.dto;

import com.example.socialnetworkrestapi.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class NewPostDto
{
    @Getter
    @Setter
    private String header;
    
    @Getter
    @Setter
    private String text;
    
    public static NewPostDto fromPostEntity(PostEntity post)
    {
        return new NewPostDto(post.getHeader(), post.getText());
    }
}