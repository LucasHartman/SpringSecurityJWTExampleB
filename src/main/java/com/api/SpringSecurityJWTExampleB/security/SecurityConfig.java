package com.api.SpringSecurityJWTExampleB.security;

/*
--  This Class
-
-   extends WebSecurityConfigurerAdapter, to override certain methods
-

--  time code: 49:30


 */

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.api.SpringSecurityJWTExampleB.filter.CustomAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // Dependency Injects Bean, for getting user details
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // DI, encode password


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        - This method Manages Authentication
        - Authentication verifies who you are who you say you are (can you access the building)
        1. userDetailsService():  gets user details (see UserServiceImpl.java)
        2. passwordEncoder():     encodes password  (see Execution file)
         */
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        1. disable csrf, because we are going to use a JWT
        2. ?
        3. authorizeRequest,  permit everybody to  access this application
        4. add filter, check user when they log in

        -- time code 1:02:20 & 1:27:30
         */
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean()); // initialize constructor
        customAuthenticationFilter.setFilterProcessesUrl("/api/login/**"); // set login-page to /api/login url
        http.csrf().disable(); // disable csrf
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login").permitAll(); // permit this page to all users (order matters)
        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER"); // give authorization to user accessing /api/user/..)
        http.authorizeRequests().antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter); // Authentication Filter (check CustomAuthenticationFilter.java)
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
