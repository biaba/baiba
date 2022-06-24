package com.skujevska.baiba.controller;

import com.skujevska.baiba.model.User;
import com.skujevska.baiba.service.CarService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    CarService carService;

    static User user = new User("otto" + new Random().nextInt(), "pssw");

    @Test
    @Order(1)
    void homeContainsUserForm() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("home"));
    }

    @Test
    @Order(2)
    void accessLoginToAll() throws Exception {
        mvc.perform(get("/signup"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void accessSignUpToAll() throws Exception {
        mvc.perform(get("/signin"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void registerSucceeds() throws Exception {
        mvc.perform(post("/signup")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                ).andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("addUserSuccess"));
    }

    @Test
    @Order(5)
    void registerNotSucceedsUserExists() throws Exception {
        mvc.perform(post("/signup")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                ).andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("userNameTaken"))
                .andExpect(redirectedUrl("/signup?userNameTaken=true"));
    }

    @Test
    @Order(6)
    void loginSucceeds() throws Exception {
        mvc.perform(post("/signin")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                ).andExpect(status().is3xxRedirection());
    }

    @Test
    @Order(7)
    void loginFailure() throws Exception {
        mvc.perform(post("/signin")
                        .param("username", user.getUsername())
                        .param("password", "wrong password")
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signin?error"));
    }
}
