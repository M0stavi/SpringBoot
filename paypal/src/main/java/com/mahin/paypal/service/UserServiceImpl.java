package com.mahin.paypal.service;


import com.mahin.paypal.entity.Users;
import com.mahin.paypal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Users createUser(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Users updateUser(Long id, Users user) {
        Users existingUser = getUserById(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        return userRepository.save(existingUser);
    }

    @Override
    public Users getUserById(Long id) {
        Optional<Users> users = userRepository.findById(id);
        return users.orElseThrow(() -> new RuntimeException("User not found with id "+ id));
    }

    @Override
    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUSer(Long id) {
        Users existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}
