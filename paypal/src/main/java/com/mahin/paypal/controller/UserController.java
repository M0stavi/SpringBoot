package com.mahin.paypal.controller;

import com.mahin.paypal.entity.Users;
import com.mahin.paypal.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping
    public Users createUser(@RequestBody Users user){
        return userServiceImpl.createUser(user);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user){
        return userServiceImpl.updateUser(id, user);
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id){
        return userServiceImpl.getUserById(id);
    }

    @GetMapping
    public List<Users> getAllUser(){
        return userServiceImpl.getAllUser();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userServiceImpl.deleteUSer(id);
    }


}
