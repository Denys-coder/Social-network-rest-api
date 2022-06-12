package com.example.socialnetworkrestapi.security;

import com.example.socialnetworkrestapi.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private final UserDetailsServiceImpl userPrincipalDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public SecurityConfiguration(UserDetailsServiceImpl userPrincipalDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                .authorizeRequests()
                // configure access rules
                .antMatchers("/login").permitAll()
                .antMatchers( "/register").permitAll()
                .antMatchers("/user/*").permitAll()
                .antMatchers("/post/*").hasRole("USER")
                
                
                .anyRequest().authenticated();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        
        return daoAuthenticationProvider;
    }
}
