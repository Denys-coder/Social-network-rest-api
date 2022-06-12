package com.example.socialnetworkrestapi.entity;

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
    private int id;
    
    @Getter
    @Setter
    private String header;
    
    @Getter
    @Setter
    private String text;
    
    @Getter
    @Setter
    @JoinColumn
    private int userId;
}
