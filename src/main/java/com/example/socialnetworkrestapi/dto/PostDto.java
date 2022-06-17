package com.example.socialnetworkrestapi.dto;

import com.example.socialnetworkrestapi.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PostDto
{
    @Getter
    @Setter
    private long id;
    
    @Getter
    @Setter
    private String header;
    
    @Getter
    @Setter
    private String text;
    
    public static PostDto fromPostEntity(PostEntity post)
    {
        return new PostDto(post.getId(), post.getHeader(), post.getText());
    }
}
