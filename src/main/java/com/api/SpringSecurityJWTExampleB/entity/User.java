package com.api.SpringSecurityJWTExampleB.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import  com.api.SpringSecurityJWTExampleB.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
- Source time code: 8:50
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String name;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) // load all Roles, when load all Users
    private Collection<Role> roles = new ArrayList<>(); // list of roles

    public Collection<Role> getRoles() {
        return this.roles;
    }

}
