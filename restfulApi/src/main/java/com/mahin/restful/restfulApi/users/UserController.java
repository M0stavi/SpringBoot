package com.mahin.restful.restfulApi.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService){
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUser(){
        return userDaoService.getAllUser();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable Integer id){
        User retrievedUser = userDaoService.retrieveUserById(id);
        if (retrievedUser == null)
            throw new UserNotFoundException("id: "+id);
        else
            return retrievedUser;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
