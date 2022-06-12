package com.example.socialnetworkrestapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String password;
    
    @Getter
    @Setter
    private String email;
    
    public UserEntity(String name, String surname, int age, String aboutMe, String password, String email)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.aboutMe = aboutMe;
        this.password = password;
        this.email = email;
    }
}
