package com.zeta.paymentManagementSystem.service;


import com.zeta.paymentManagementSystem.constants.Role;
import com.zeta.paymentManagementSystem.model.User;
import com.zeta.paymentManagementSystem.repository.UserRepository;
import com.zeta.paymentManagementSystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private User createSampleUser(int id) {
        return new User(id, "John Doe", "john.doe@example.com", Role.VIEWER);
    }

    @Test
    void testAddUser() {
        User user = createSampleUser(1);
        when(userRepository.save(user)).thenReturn(user);

        userService.addUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(createSampleUser(1), createSampleUser(2));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }
}
