package com.example.socialnetworkrestapi.security;

import com.example.socialnetworkrestapi.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails
{
    @Getter
    @Setter
    private UserEntity userEntity;
    
    public UserDetailsImpl(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
    
    @Override
    public String getUsername()
    {
        return userEntity.getEmail();
    }
    
    @Override
    public String getPassword()
    {
        return userEntity.getPassword();
    }
    
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
