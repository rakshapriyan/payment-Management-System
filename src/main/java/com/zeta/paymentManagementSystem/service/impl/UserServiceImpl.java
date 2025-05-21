package com.zeta.paymentManagementSystem.service.impl;

import com.zeta.paymentManagementSystem.model.User;
import com.zeta.paymentManagementSystem.repository.UserRepository;
import com.zeta.paymentManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
