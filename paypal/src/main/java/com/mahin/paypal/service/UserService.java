package com.mahin.paypal.service;

import com.mahin.paypal.entity.Users;

import java.util.List;

public interface UserService {
    public Users createUser(Users user);
    public Users updateUser(Long id, Users user);
    public Users getUserById(Long id);
    public List<Users> getAllUser();
    public void deleteUSer(Long id);
}
