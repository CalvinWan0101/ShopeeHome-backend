package com.calvinwan.shopeehomebackend.controller;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import tw.teddysoft.ezspec.extension.junit5.EzScenario;
import tw.teddysoft.ezspec.keyword.Feature;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/database/data.sql")
public class ProductControllerSpec {

    @Autowired
    private MockMvc mockMvc;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("Product Management", "As a user\n" +
                "I want to manage products\n" +
                "So that I can maintain the product information");
        feature.NewRule("Insert Product");
        feature.NewRule("Get Product by ID");
        feature.NewRule("Get Product by Name");
        feature.NewRule("Update Product by ID");
        feature.NewRule("Update Product Sales by ID");
        feature.NewRule("Delete Product by ID");
    }

    @EzScenario
    @Transactional
    public void insert() {
        feature.newScenario("Insert product").withRule("Insert Product")
                .Given("a new product", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                        "name": "samsung",
                                        "amount": 81,
                                        "price": 31200,
                                        "description": "This is samsung",
                                        "discountRate": 0.92,
                                        "discountDate": "2024-08-15",
                                        "shopId": "1013f7a0-0017-4c21-872f-c014914e6834",
                                        "images": [
                                            "samsung_image_1",
                                            "samsung_image_2"
                                        ],
                                        "isDeleted": false
                                    }"""));
                })
                .When("I send an insert request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
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
                                .andExpect(MockMvcResultMatchers.jsonPath("$.images[0]").value("samsung_image_1"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.images[1]").value("samsung_image_2"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_by_id() {
        feature.newScenario("Get product by ID").withRule("Get Product by ID")
                .Given("an existing product ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/6874ada1-747f-41a7-bb9a-613d2ec0ce1d"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
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
                                .andExpect(MockMvcResultMatchers.jsonPath("$.images[0]").value("iphone_image_1"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.images[1]").value("iphone_image_2"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_by_id_not_found() {
        feature.newScenario("Get product by non-existent ID").withRule("Get Product by ID")
                .Given("a non-existent product ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/wrong123"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(404))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_id_by_name() {
        feature.newScenario("Get product ID by name").withRule("Get Product by Name")
                .Given("an existing product name", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/id/iphone"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(200))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_id_by_name_with_uncompleted_name() {
        feature.newScenario("Get product ID by incomplete name").withRule("Get Product by Name")
                .Given("an incomplete product name", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/id/i"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(200))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("8c883a21-fad1-43af-8b15-54b2c1c7a70e"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3"))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_id_by_name_not_found() {
        feature.newScenario("Get product ID by non-existent name").withRule("Get Product by Name")
                .Given("a non-existent product name", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/name/wrong123"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(404))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_all_name() {
        feature.newScenario("Get all product names").withRule("Get Product by Name")
                .Given("a request to get all product names", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/name/all"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
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
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_all_name_by_shop_id() {
        feature.newScenario("Get all product names by shop ID").withRule("Get Product by Name")
                .Given("a shop ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/name/shop/1013f7a0-0017-4c21-872f-c014914e6834"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(200))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("6874ada1-747f-41a7-bb9a-613d2ec0ce1d"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("iphone"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("8c883a21-fad1-43af-8b15-54b2c1c7a70e"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("xiaomi"))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_product_preview_by_id() {
        feature.newScenario("Get product preview by ID").withRule("Get Product by ID")
                .Given("a product ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/preview/6874ada1-747f-41a7-bb9a-613d2ec0ce1d"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(200))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("iphone"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value(32103))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.sales").value(27))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value("iphone_image_1"))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void get_product_preview_by_id_not_found() {
        feature.newScenario("Get product preview by non-existent ID").withRule("Get Product by ID")
                .Given("a non-existent product ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/product/preview/wrong123"));
                })
                .When("I send a get request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(404))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void update_product_by_id() {
        feature.newScenario("Update product by ID").withRule("Update Product by ID")
                .Given("an existing product ID and new product details", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .put("/product/6874ada1-747f-41a7-bb9a-613d2ec0ce1d")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                        "name": "samsung",
                                        "amount": 81,
                                        "price": 31200,
                                        "description": "This is samsung",
                                        "discountRate": 0.92,
                                        "discountDate": "2024-08-15",
                                        "shopId": "1013f7a0-0017-4c21-872f-c014914e6834",
                                        "images": [
                                            "samsung_image_1",
                                            "samsung_image_2"
                                        ],
                                        "isDeleted": false
                                    }"""));
                })
                .When("I send an update request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
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
                                .andExpect(MockMvcResultMatchers.jsonPath("$.images[0]").value("samsung_image_1"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.deleted").value(false))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void update_product_by_id_not_found() {
        feature.newScenario("Update product by non-existent ID").withRule("Update Product by ID")
                .Given("a non-existent product ID and new product details", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .put("/product/wrong123")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                        "name": "samsung",
                                        "amount": 81,
                                        "price": 31200,
                                        "description": "This is samsung",
                                        "discountRate": 0.92,
                                        "discountDate": "2024-08-15",
                                        "shopId": "1013f7a0-0017-4c21-872f-c014914e6834",
                                        "images": [
                                            "samsung_image_1",
                                            "samsung_image_2"
                                        ],
                                        "isDeleted": false
                                    }"""));
                })
                .When("I send an update request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(404))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void update_product_sales_by_id() {
        feature.newScenario("Update product sales by ID").withRule("Update Product Sales by ID")
                .Given("an existing product ID and sales update", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .put("/product/sales/6874ada1-747f-41a7-bb9a-613d2ec0ce1d")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("10"));
                })
                .When("I send an update request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(200))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void update_product_sales_by_id_not_found() {
        feature.newScenario("Update product sales by non-existent ID").withRule("Update Product Sales by ID")
                .Given("a non-existent product ID and sales update", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .put("/product/sales/wrong123")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("10"));
                })
                .When("I send an update request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(404))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void delete_product_by_id() {
        feature.newScenario("Delete product by ID").withRule("Delete Product by ID")
                .Given("an existing product ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .delete("/product/6874ada1-747f-41a7-bb9a-613d2ec0ce1d"));
                })
                .When("I send a delete request", env -> {
                    RequestBuilder requestBuilder = env.get("requestBuilder", RequestBuilder.class);
                    try {
                        env.put("response", mockMvc.perform(requestBuilder));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .ThenSuccess(env -> {
                    try {
                        env.get("response", ResultActions.class)
                                .andExpect(status().is(204))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }
}
