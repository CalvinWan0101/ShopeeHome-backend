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
public class UserControllerSpec {

    @Autowired
    private MockMvc mockMvc;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("User Management", "As a user\n" +
                "I want to manage my account\n" +
                "So that I can maintain my personal information");
        feature.NewRule("Register User");
        feature.NewRule("Get User by ID");
        feature.NewRule("Update User by ID");
        feature.NewRule("Delete User by ID");
        feature.NewRule("User Login");
    }

    @Test
    @Transactional
    public void register() {
        feature.newScenario("Register user").withRule("Register User")
                .Given("a new user", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
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
                                .andExpect(jsonPath("$.id").isNotEmpty())
                                .andExpect(jsonPath("$.name").value("John"))
                                .andExpect(jsonPath("$.email").value("john@gmail.com"))
                                .andExpect(jsonPath("$.phoneNumber").value("0909000123"))
                                .andExpect(jsonPath("$.avatar").value("john_avatar"))
                                .andExpect(jsonPath("$.addresses[0]").value("address-test1-A"))
                                .andExpect(jsonPath("$.addresses[1]").value("address-test1-B"))
                                .andExpect(jsonPath("$.addresses[2]").value("address-test1-C"))
                                .andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @Test
    public void get_by_id() {
        feature.newScenario("Get user by ID").withRule("Get User by ID")
                .Given("an existing user ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .get("/user/30e7e267-c791-424a-a94b-fa5e695d27e7"));
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
                                .andExpect(jsonPath("$.id").value("30e7e267-c791-424a-a94b-fa5e695d27e7"))
                                .andExpect(jsonPath("$.name").value("user1"))
                                .andExpect(jsonPath("$.email").value("user1@gmail.com"))
                                .andExpect(jsonPath("$.phoneNumber").value("0909001001"))
                                .andExpect(jsonPath("$.avatar").value("user1_avatar"))
                                .andExpect(jsonPath("$.addresses[0]").value("address-user1-A"))
                                .andExpect(jsonPath("$.addresses[1]").value("address-user1-B"))
                                .andExpect(jsonPath("$.addresses[2]").value("address-user1-C"))
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
        feature.newScenario("Update user by ID").withRule("Update User by ID")
                .Given("an existing user ID and new user details", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
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
                                .andExpect(jsonPath("$.id").value("30e7e267-c791-424a-a94b-fa5e695d27e7"))
                                .andExpect(jsonPath("$.name").value("Calvin"))
                                .andExpect(jsonPath("$.email").value("calvin@gmail.com"))
                                .andExpect(jsonPath("$.phoneNumber").value("0909000111"))
                                .andExpect(jsonPath("$.avatar").value("calvin_avatar"))
                                .andExpect(jsonPath("$.addresses[0]").value("address-calvin-A"))
                                .andExpect(jsonPath("$.addresses[1]").value("address-calvin-B"))
                                .andExpect(jsonPath("$.addresses[2]").value("address-calvin-C"))
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
        feature.newScenario("Delete user by ID").withRule("Delete User by ID")
                .Given("an existing user ID", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .delete("/user/8b3d52ee-578a-4635-a877-1aefdcfc4fae"));
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
    public void login_success() {
        feature.newScenario("Login success").withRule("User Login")
                .Given("a user's correct email and password", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .post("/user/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "email": "user1@gmail.com",
                                    "password": "user1"
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
    public void login_with_not_exist_email() {
        feature.newScenario("Login with non-existent email").withRule("User Login")
                .Given("a user's non-existent email", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .post("/user/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "email": "wrong@gmail.com",
                                    "password": "user1"
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
    public void login_with_wrong_password() {
        feature.newScenario("Login with wrong password").withRule("User Login")
                .Given("a user's correct email and wrong password", env -> {
                    env.put("requestBuilder", MockMvcRequestBuilders
                            .post("/user/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "email": "user1@gmail.com",
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
