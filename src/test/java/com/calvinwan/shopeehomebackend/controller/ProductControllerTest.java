package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.ImageBase64Converter;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/database/data.sql")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void insert() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new String(Files.readAllBytes(Paths.get("src/test/resources/img/test/samsung.json"))));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andReturn();
    }

//    @Test
//    @Transactional
//    public void get_by_id() throws Exception {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/product/6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
//
//        List<String> images = List.of(
//                ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_1.jpg"),
//                ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_2.jpg")
//        );
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().is(200))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("iphone"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(90))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.sales").value(27))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(36900))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is iphone"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.discountRate").value(0.87))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.discountDate").value("2024-07-31"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.shopId").value("1013f7a0-0017-4c21-872f-c014914e6834"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.images").value(images))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
//                .andReturn();
//    }


}