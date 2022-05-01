package com.api.SpringSecurityJWTExampleB.service;

import com.api.SpringSecurityJWTExampleB.entity.Role;
import com.api.SpringSecurityJWTExampleB.entity.User;
import com.api.SpringSecurityJWTExampleB.repo.RoleRepo;
import com.api.SpringSecurityJWTExampleB.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
-- This Class
-   GET and CREATE user Methods
-   @Service:           business logic resides within the service layer
-   Business logic:     determines how data is transformed, and how it is routed to users

--  time code: 21:50
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j // logging
public class UserServiceImpl implements UserService, UserDetailsService {
    private  final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
        - This Method: Override a method from the implemented UserDetailsService interface
        1. Find user by Username
        2. Check if it is not null object
        3. Create a empty ArrayList
        4. Loop through all the role of the user, and adds roles to ArrayList
        5. Returns user: username, password, and ArrayList of user roles
         */
        User user = userRepo.findByUsername(username); // get user by username
        if( user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User {} found in the database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(); // empty list
        user.getRoles().forEach(role -> {
                authorities.add( new SimpleGrantedAuthority( role.getName() ));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword())); // encode password and set it to the user
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Saving new role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username); // find user
        Role role = roleRepo.findByName(roleName); // find role
        user.getRoles().add(role);  // get & set role to user
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username); // find & return user
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

}
