package com.example.socialnetworkrestapi.security;

import com.auth0.jwt.JWT;
import com.example.socialnetworkrestapi.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private final AuthenticationManager authenticationManager;
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }
    
    // Trigger when we issue POST request to /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException
    {
        
        // Grab credentials and map them to login viewmodel
        LoginDto credentials = null;
        try
        {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                new ArrayList<>());
        
        // Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        
        return auth;
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException
    {
        // Grab principal
        UserDetailsImpl principal = (UserDetailsImpl) authResult.getPrincipal();
        
        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
        
        // Add token in response
        response.addHeader(JwtProperties.HEADER, JwtProperties.TOKEN_PREFIX + token);
    }
}