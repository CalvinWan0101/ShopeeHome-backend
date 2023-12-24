package com.calvinwan.shopeehomebackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/database/data.sql")
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void login_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "admin",
                        "password": "admin"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    public void login_with_not_exist_name() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "wrong",
                        "password": "admin"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    public void login_with_wrong_password() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "admin",
                        "password": "wrong"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();
    }
}