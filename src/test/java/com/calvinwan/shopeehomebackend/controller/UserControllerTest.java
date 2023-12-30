package com.calvinwan.shopeehomebackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/database/data.sql")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void register() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "John",
                          "email": "john@gmail.com",
                          "phoneNumber": "0909000123",
                          "password": "john",
                          "avatar": "john_avatar",
                          "addresses":[
                            "address-test1-A",
                            "address-test1-B",
                            "address-test1-C"
                            ]
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0909000123"))
                .andExpect(jsonPath("$.avatar").value("john_avatar"))
                .andExpect(jsonPath("$.addresses[0]").value("address-test1-A"))
                .andExpect(jsonPath("$.addresses[1]").value("address-test1-B"))
                .andExpect(jsonPath("$.addresses[2]").value("address-test1-C"))
                .andReturn();
    }

    @Test
    public void get_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/30e7e267-c791-424a-a94b-fa5e695d27e7");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value("30e7e267-c791-424a-a94b-fa5e695d27e7"))
                .andExpect(jsonPath("$.name").value("user1"))
                .andExpect(jsonPath("$.email").value("user1@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0909001001"))
                .andExpect(jsonPath("$.avatar").value("user1_avatar"))
                .andExpect(jsonPath("$.addresses[0]").value("address-user1-A"))
                .andExpect(jsonPath("$.addresses[1]").value("address-user1-B"))
                .andExpect(jsonPath("$.addresses[2]").value("address-user1-C"))
                .andReturn();
    }

    @Test
    @Transactional
    public void update_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/user/30e7e267-c791-424a-a94b-fa5e695d27e7")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "name": "Calvin",
                          "email": "calvin@gmail.com",
                          "phoneNumber": "0909000111",
                          "password": "calvin",
                            "avatar": "calvin_avatar",
                          "addresses":[
                            "address-calvin-A",
                            "address-calvin-B",
                            "address-calvin-C"
                            ]
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value("30e7e267-c791-424a-a94b-fa5e695d27e7"))
                .andExpect(jsonPath("$.name").value("Calvin"))
                .andExpect(jsonPath("$.email").value("calvin@gmail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("0909000111"))
                .andExpect(jsonPath("$.avatar").value("calvin_avatar"))
                .andExpect(jsonPath("$.addresses[0]").value("address-calvin-A"))
                .andExpect(jsonPath("$.addresses[1]").value("address-calvin-B"))
                .andExpect(jsonPath("$.addresses[2]").value("address-calvin-C"))
                .andReturn();
    }

    @Test
    @Transactional
    public void delete_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/user/8b3d52ee-578a-4635-a877-1aefdcfc4fae");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204))
                .andReturn();
    }

    @Test
    public void login_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "email": "user1@gmail.com",
                        "password": "user1"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    public void login_with_not_exist_email() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "email": "wrong@gmail.com",
                        "password": "user1"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    public void login_with_wrong_password() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "email": "user1@gmail.com",
                        "password": "wrong"
                        }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();
    }

}