package com.example.socialnetworkrestapi.security;

import com.example.socialnetworkrestapi.entity.UserEntity;
import com.example.socialnetworkrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserRepository userRepository;
    
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        
        if (userEntity == null)
        {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        
        return new UserDetailsImpl(userEntity);
    }
}
