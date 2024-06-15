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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tw.teddysoft.ezspec.extension.junit5.EzScenario;
import tw.teddysoft.ezspec.keyword.Feature;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/database/data.sql")
public class AdminControllerSpec {
    @Autowired
    private MockMvc mockMvc;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("Admin Login", "As an admin user\n" + "  I want to login to the system");
        feature.NewRule("Login");
    }

    @EzScenario
    public void login_success() {
        feature.newScenario("Login success").withRule("Login")
                .Given("an admin's credentials", env -> {
                    env.put("name", "admin");
                    env.put("password", "admin");
                })
                .When("I send a login request", env -> {
                    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/login").contentType(MediaType.APPLICATION_JSON).content(String.format("""
                            {
                            "name": "%s",
                            "password": "%s"
                            }""", env.gets("name"), env.gets("password")));
                    env.put("requestBuilder", requestBuilder);
                })
                .ThenSuccess(env -> {
                    try {
                        mockMvc.perform(env.get("requestBuilder", RequestBuilder.class)).andExpect(status().is(200)).andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void login_with_not_exist_name() {
        feature.newScenario("Login with non-existent name").withRule("Login")
                .Given("non-existent admin's credentials", env -> {
                    env.put("name", "wrong");
                    env.put("password", "admin");
                })
                .When("I send a login request", env -> {
                    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/login").contentType(MediaType.APPLICATION_JSON).content(String.format("""
                            {
                            "name": "%s",
                            "password": "%s"
                            }""", env.gets("name"), env.gets("password")));
                    env.put("requestBuilder", requestBuilder);
                })
                .ThenSuccess(env -> {
                    try {
                        mockMvc.perform(env.get("requestBuilder", RequestBuilder.class)).andExpect(status().is(400)).andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }

    @EzScenario
    public void login_with_wrong_password() {
        feature.newScenario("Login with wrong password").withRule("Login")
                .Given("admin's credentials with wrong password", env -> {
                    env.put("name", "admin");
                    env.put("password", "wrong");
                })
                .When("I send a login request", env -> {
                    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/login").contentType(MediaType.APPLICATION_JSON).content(String.format("""
                            {
                            "name": "%s",
                            "password": "%s"
                            }""", env.gets("name"), env.gets("password")));
                    env.put("requestBuilder", requestBuilder);
                })
                .ThenSuccess(env -> {
                    try {
                        mockMvc.perform(env.get("requestBuilder", RequestBuilder.class)).andExpect(status().is(400)).andReturn();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .Execute();
    }
}