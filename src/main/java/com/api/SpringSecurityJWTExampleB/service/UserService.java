package com.api.SpringSecurityJWTExampleB.service;

import com.api.SpringSecurityJWTExampleB.entity.Role;
import com.api.SpringSecurityJWTExampleB.entity.User;

import java.util.List;

/*
- Source time code: 18:00
 */

public interface UserService {
    User saveUser(User user); // when this method is called user gets saved
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName); // when this method is called add role to user
    User getUser(String username);
    List<User> getUsers(); //
}
