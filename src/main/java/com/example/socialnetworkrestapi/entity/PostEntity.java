package com.example.socialnetworkrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "posts")
@NoArgsConstructor
public class PostEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;
    
    @Getter
    @Setter
    private String header;
    
    @Getter
    @Setter
    private String text;
    
    @Getter
    @Setter
    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    
    public PostEntity(String header, String text, UserEntity user)
    {
        this.header = header;
        this.text = text;
        this.user = user;
    }
    
    @PreRemove
    private void removePostFromUser()
    {
        user.getPosts().remove(this);
    }
}
