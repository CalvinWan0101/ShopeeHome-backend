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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/database/data.sql")
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void register() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/shop/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "calvinshop@gmail.com",
                          "password": "calvinshop",
                          "name": "CalvinShop",
                           "phoneNumber": "0909000123",
                            "address": "calvin's shop address",
                            "description": "calvin's shop description",
                            "avatar": "calvinshop_avatar",
                            "createrId": "17335ce6-af7c-4c21-af55-9eca9dc5dfb7",
                            "deleterId": null,
                            "deleted": false
                          }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("calvinshop@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("CalvinShop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("0909000123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("calvin's shop address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("calvin's shop description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value("calvinshop_avatar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createrId").value("17335ce6-af7c-4c21-af55-9eca9dc5dfb7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                .andReturn();
    }

    @Test
    @Transactional
    public void get_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/shop/1013f7a0-0017-4c21-872f-c014914e6834");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1013f7a0-0017-4c21-872f-c014914e6834"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("shop1@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("shop1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("0909001001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("address1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is shop 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value("shop1_avatar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.background").value("shop1_background"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createrId").value("17335ce6-af7c-4c21-af55-9eca9dc5dfb7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                .andReturn();
    }

    @Test
    @Transactional
    public void update_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/shop/1013f7a0-0017-4c21-872f-c014914e6834")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "calvinshop@gmail.com",
                          "password": "calvinshop",
                          "name": "CalvinShop",
                           "phoneNumber": "0909000123",
                            "address": "calvin's shop address",
                            "description": "calvin's shop description",
                            "avatar": "calvinshop_avatar",
                            "background": "calvinshop_background",
                            "createrId": "17335ce6-af7c-4c21-af55-9eca9dc5dfb7",
                            "deleterId": null,
                            "deleted": false
                          }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1013f7a0-0017-4c21-872f-c014914e6834"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("calvinshop@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("CalvinShop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("0909000123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("calvin's shop address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("calvin's shop description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value("calvinshop_avatar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.background").value("calvinshop_background"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createrId").value("17335ce6-af7c-4c21-af55-9eca9dc5dfb7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                .andReturn();
    }

    @Test
    @Transactional
    public void delete_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/shop/1013f7a0-0017-4c21-872f-c014914e6834");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204))
                .andReturn();
    }

    @Test
    @Transactional
    public void login_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/shop/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "shop1@gmail.com",
                            "password": "shop1"
                            }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    @Transactional
    public void login_with_not_exist_email() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/shop/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "wrong@gmail.com",
                            "password": "shop1"
                            }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    @Transactional
    public void login_with_wrong_password() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/shop/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "shop1@gmail.com",
                            "password": "wrong"
                            }""");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();
    }
}