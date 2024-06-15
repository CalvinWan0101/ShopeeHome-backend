package com.calvinwan.shopeehomebackend.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import tw.teddysoft.ezspec.keyword.Feature;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/database/data.sql")
public class ShopControllerSpec {

    @Autowired
    private MockMvc mockMvc;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("Shop Management", "As a shop owner\n" +
                "I want to manage my shop\n" +
                "So that I can maintain shop information");
        feature.NewRule("Register Shop");
        feature.NewRule("Get Shop by ID");
        feature.NewRule("Update Shop by ID");
        feature.NewRule("Delete Shop by ID");
        feature.NewRule("Shop Login");
    }

    @Test
    @Transactional
    public void register() {
        feature.newScenario("Register shop").withRule("Register Shop")
                .Given("a new shop", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
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
                                    }"""));
                })
                .When("I send a register request", env -> {
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
                                .andExpect(jsonPath("$.id").exists())
                                .andExpect(jsonPath("$.email").value("calvinshop@gmail.com"))
                                .andExpect(jsonPath("$.name").value("CalvinShop"))
                                .andExpect(jsonPath("$.phoneNumber").value("0909000123"))
                                .andExpect(jsonPath("$.address").value("calvin's shop address"))
                                .andExpect(jsonPath("$.description").value("calvin's shop description"))
                                .andExpect(jsonPath("$.avatar").value("calvinshop_avatar"))
                                .andExpect(jsonPath("$.createrId").value("17335ce6-af7c-4c21-af55-9eca9dc5dfb7"))
                                .andExpect(jsonPath("$.deleted").value(false))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @Test
    @Transactional
    public void get_by_id() {
        feature.newScenario("Get shop by ID").withRule("Get Shop by ID")
                .Given("an existing shop ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/shop/1013f7a0-0017-4c21-872f-c014914e6834"));
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
                                .andExpect(jsonPath("$.id").value("1013f7a0-0017-4c21-872f-c014914e6834"))
                                .andExpect(jsonPath("$.email").value("shop1@gmail.com"))
                                .andExpect(jsonPath("$.name").value("shop1"))
                                .andExpect(jsonPath("$.phoneNumber").value("0909001001"))
                                .andExpect(jsonPath("$.address").value("address1"))
                                .andExpect(jsonPath("$.description").value("This is shop 1"))
                                .andExpect(jsonPath("$.avatar").value("shop1_avatar"))
                                .andExpect(jsonPath("$.background").value("shop1_background"))
                                .andExpect(jsonPath("$.createrId").value("17335ce6-af7c-4c21-af55-9eca9dc5dfb7"))
                                .andExpect(jsonPath("$.deleted").value(false))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @Test
    @Transactional
    public void update_by_id() {
        feature.newScenario("Update shop by ID").withRule("Update Shop by ID")
                .Given("an existing shop ID and new shop details", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
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
                                .andExpect(jsonPath("$.id").value("1013f7a0-0017-4c21-872f-c014914e6834"))
                                .andExpect(jsonPath("$.email").value("calvinshop@gmail.com"))
                                .andExpect(jsonPath("$.name").value("CalvinShop"))
                                .andExpect(jsonPath("$.phoneNumber").value("0909000123"))
                                .andExpect(jsonPath("$.address").value("calvin's shop address"))
                                .andExpect(jsonPath("$.description").value("calvin's shop description"))
                                .andExpect(jsonPath("$.avatar").value("calvinshop_avatar"))
                                .andExpect(jsonPath("$.background").value("calvinshop_background"))
                                .andExpect(jsonPath("$.createrId").value("17335ce6-af7c-4c21-af55-9eca9dc5dfb7"))
                                .andExpect(jsonPath("$.deleted").value(false))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @Test
    @Transactional
    public void delete_by_id() {
        feature.newScenario("Delete shop by ID").withRule("Delete Shop by ID")
                .Given("an existing shop ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .delete("/shop/1013f7a0-0017-4c21-872f-c014914e6834"));
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

    @Test
    @Transactional
    public void login_success() {
        feature.newScenario("Login success").withRule("Shop Login")
                .Given("a shop's correct email and password", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .post("/shop/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "email": "shop1@gmail.com",
                                      "password": "shop1"
                                    }"""));
                })
                .When("I send a login request", env -> {
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

    @Test
    @Transactional
    public void login_with_not_exist_email() {
        feature.newScenario("Login with non-existent email").withRule("Shop Login")
                .Given("a shop's non-existent email", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .post("/shop/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "email": "wrong@gmail.com",
                                      "password": "shop1"
                                    }"""));
                })
                .When("I send a login request", env -> {
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
                                .andExpect(status().is(400))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @Test
    @Transactional
    public void login_with_wrong_password() {
        feature.newScenario("Login with wrong password").withRule("Shop Login")
                .Given("a shop's correct email and wrong password", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .post("/shop/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "email": "shop1@gmail.com",
                                      "password": "wrong"
                                    }"""));
                })
                .When("I send a login request", env -> {
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
                                .andExpect(status().is(400))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }
}
