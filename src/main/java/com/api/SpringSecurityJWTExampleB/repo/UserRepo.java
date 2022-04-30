package com.api.SpringSecurityJWTExampleB.repo;

import com.api.SpringSecurityJWTExampleB.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
- Source time code: 15:30
 */

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username); // finds user in table by username
}
