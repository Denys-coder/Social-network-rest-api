package com.example.socialnetworkrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class RegisterDto
{
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String surname;
    
    @Getter
    @Setter
    private int age;
    
    @Getter
    @Setter
    private String aboutMe;
    
    @Getter
    @Setter
    private String password;
    
    @Getter
    @Setter
    private String email;
}
