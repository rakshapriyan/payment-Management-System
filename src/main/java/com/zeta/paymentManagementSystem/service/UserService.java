package com.zeta.paymentManagementSystem.service;

import com.zeta.paymentManagementSystem.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);
}
