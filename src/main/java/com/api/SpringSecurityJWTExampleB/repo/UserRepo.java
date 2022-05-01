package com.api.SpringSecurityJWTExampleB.repo;

import com.api.SpringSecurityJWTExampleB.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
-- This Class
- Repository, can perform various operations on the object.
- extends JpaRepository:    specific extension of Repository
- @Repository:              mechanism for storage, retrieval, update, delete and search operation on objects.
                            Providing CRUD operations on database tables
--  time code: 15:30
 */

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username); // finds user in table by username
}
