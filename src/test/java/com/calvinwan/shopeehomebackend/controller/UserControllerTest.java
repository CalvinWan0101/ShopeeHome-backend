package com.calvinwan.shopeehomebackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    void register() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "John",
                          "email": "john@gmail.com",
                          "phoneNumber": "0909000123",
                          "password": "john"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andReturn();
    }

    @Test
    void get_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/30e7e267-c791-424a-a94b-fa5e695d27e7");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value("30e7e267-c791-424a-a94b-fa5e695d27e7"))
                .andExpect(jsonPath("$.name").value("test1"))
                .andExpect(jsonPath("$.email").value("test1@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0909001001"))
                .andReturn();
    }

    @Test
    @Transactional
    void updateById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/user/30e7e267-c791-424a-a94b-fa5e695d27e7")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Calvin",
                          "email": "calvin@gmail.com",
                          "phoneNumber": "0909000111",
                          "password": "calvin"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value("30e7e267-c791-424a-a94b-fa5e695d27e7"))
                .andExpect(jsonPath("$.name").value("Calvin"))
                .andExpect(jsonPath("$.email").value("calvin@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0909000111"))
                .andReturn();
    }

    @Test
    @Transactional
    void delete_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/user/8b3d52ee-578a-4635-a877-1aefdcfc4fae");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204))
                .andReturn();
    }

    @Test
    void login() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "email": "calvin@gmail.com",
                        "password": "calvin"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();
    }
}