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

import java.io.IOException;
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
                .content(new String(Files.readAllBytes(Paths.get("src/test/resources/img/6_samsung/samsung.json"))));

        List<String> images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/6_samsung/samsung_1.webp")
        );

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("samsung"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(81))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sales").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(31200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is samsung"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountRate").value(0.92))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountDate").value("2024-08-15"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shopId").value("1013f7a0-0017-4c21-872f-c014914e6834"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.images[0]").value(ImageBase64Converter.imageToBase64("src/test/resources/img/6_samsung/samsung_1.webp")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                .andReturn();
    }

    @Test
    public void get_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("iphone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(90))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sales").value(27))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(36900))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is iphone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountRate").value(0.87))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountDate").value("2024-07-31"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shopId").value("1013f7a0-0017-4c21-872f-c014914e6834"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.images[0]").value(ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_1.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.images[1]").value(ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_2.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                .andReturn();
    }

    @Test
    public void get_by_id_not_found() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/wrong123");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    public void get_by_name() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/name/iphone");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("iphone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(90))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sales").value(27))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(36900))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("This is iphone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].discountRate").value(0.87))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].discountDate").value("2024-07-31"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].shopId").value("1013f7a0-0017-4c21-872f-c014914e6834"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].images[0]").value(ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_1.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].images[1]").value(ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_2.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].deleted").value(false))
                .andReturn();
    }

    @Test
    public void get_by_name_with_uncompleted_name() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/name/i");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("iphone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("xiaomi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("tissue"))
                .andReturn();
    }

    @Test
    public void get_by_name_not_found() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/name/wrong123");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    public void get_all_name() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/name/all");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("iphone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("8c883a21-fad1-43af-8b15-54b2c1c7a70e"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("xiaomi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("tissue"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].id").value("9595f97a-bf11-488a-8c15-9edf4db1c450"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value("toothbrush"))
                .andReturn();
    }

    @Test
    public void get_all_name_by_shop_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/name/shop/1013f7a0-0017-4c21-872f-c014914e6834");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("iphone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("8c883a21-fad1-43af-8b15-54b2c1c7a70e"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("xiaomi"))
                .andReturn();
    }

    @Test
    public void get_product_preview_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/preview/6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("iphone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(32103))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sales").value(27))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value(ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_1.jpg")))
                .andReturn();
    }

    @Test
    public void get_product_preview_by_id_not_found() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/preview/wrong123");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    @Transactional
    public void update_product_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/product/6874ada1-747f-41a7-bb9a-613d2ec0ce1d")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new String(Files.readAllBytes(Paths.get("src/test/resources/img/6_samsung/samsung.json"))));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("samsung"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(81))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sales").value(27))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(31200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is samsung"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountRate").value(0.92))
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountDate").value("2024-08-15"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shopId").value("1013f7a0-0017-4c21-872f-c014914e6834"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.images[0]").value(ImageBase64Converter.imageToBase64("src/test/resources/img/6_samsung/samsung_1.webp")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                .andReturn();
    }

    @Test
    @Transactional
    public void update_product_by_id_not_found() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/product/wrong123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new String(Files.readAllBytes(Paths.get("src/test/resources/img/6_samsung/samsung.json"))));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    @Transactional
    public void update_product_sales_by_id() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/product/sales/6874ada1-747f-41a7-bb9a-613d2ec0ce1d")
                .contentType(MediaType.APPLICATION_JSON)
                .content("10");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    @Transactional
    public void update_product_sales_by_id_not_found() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/product/sales/wrong123")
                .contentType(MediaType.APPLICATION_JSON)
                .content("10");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404))
                .andReturn();
    }
}