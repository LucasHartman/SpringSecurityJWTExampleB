package com.api.SpringSecurityJWTExampleB.control;

import com.api.SpringSecurityJWTExampleB.entity.Role;
import com.api.SpringSecurityJWTExampleB.entity.User;
import com.api.SpringSecurityJWTExampleB.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/*
- time code: 28:30
 */

@RestController
@RequestMapping("/api") // root url
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/users") // url that shows list of all users
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save") // url for saving user
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save") // url for saving user
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser") // url for saving role
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(),form.getRoleName()); // timecode: 42:28
        return ResponseEntity.ok().build(); // build response, ok() returns 200 status
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}