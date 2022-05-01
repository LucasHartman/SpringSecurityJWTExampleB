package com.api.SpringSecurityJWTExampleB.repo;

import com.api.SpringSecurityJWTExampleB.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/*
- time code: 16:50
 */

public interface RoleRepo extends JpaRepository<Role, Long> {
        Role findByName(String name); // finds role in table by name
}
