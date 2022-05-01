package com.api.SpringSecurityJWTExampleB.service;

import com.api.SpringSecurityJWTExampleB.entity.Role;
import com.api.SpringSecurityJWTExampleB.entity.User;
import com.api.SpringSecurityJWTExampleB.repo.RoleRepo;
import com.api.SpringSecurityJWTExampleB.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
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
public class UserServiceImpl implements UserService {
    private  final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
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
