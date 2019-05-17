package com.polytech.ekwalsharezapi;

import com.polytech.ekwalsharezapi.controller.UserController;
import com.polytech.ekwalsharezapi.model.User;
import com.polytech.ekwalsharezapi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yml")
public class AuthentificationITest {

    User martin;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserController service;
    @MockBean
    private UserRepository repository;

    @Before
    public void setUp() {

        martin = new User();
        martin.setPassword("myPassword");
        martin.setEmail("martin");

        Mockito.when(repository.findByEmail("martin")).thenReturn(martin);
    }

    @Test
    public void try_connect_get_token()
            throws Exception {

        mvc.perform(post("/users/signin").param("username", "martin")
                .param("password", "myPassword")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void wrong_password()
            throws Exception {

        mvc.perform(post("/users/signin").param("username", "martin")
                .param("password", "feojgigj")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
