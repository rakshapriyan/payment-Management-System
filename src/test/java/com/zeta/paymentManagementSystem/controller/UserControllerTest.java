package com.zeta.paymentManagementSystem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeta.paymentManagementSystem.constants.Role;
import com.zeta.paymentManagementSystem.model.User;
import com.zeta.paymentManagementSystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {
        UserController.class,
        UserControllerTest.TestConfig.class
})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    static class TestConfig {
        @Bean
        public UserService userService() {
            return mock(UserService.class);
        }
    }

    @BeforeEach
    void setup() {
        reset(userService);
    }

    private User createSampleUser(int id) {
        return new User(id, "John Doe", "john.doe@example.com", Role.VIEWER);
    }

    @Test
    void testCreateUser() throws Exception {
        User user = createSampleUser(1);
        doNothing().when(userService).addUser(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User created successfully."));
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(createSampleUser(1), createSampleUser(2));
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("John Doe")));
    }
}
