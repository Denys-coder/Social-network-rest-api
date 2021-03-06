package com.example.socialnetworkrestapi.dto;

import com.example.socialnetworkrestapi.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UserDto
{
    @Getter
    @Setter
    private int id;
    
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
    private String email;
    
    public static UserDto fromUserEntity(UserEntity user)
    {
        return new UserDto(user.getId(), user.getName(), user.getSurname(), user.getAge(), user.getAboutMe(), user.getEmail());
    }
}
