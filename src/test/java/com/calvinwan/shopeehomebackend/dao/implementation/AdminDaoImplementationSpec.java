package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.AdminDao;
import com.calvinwan.shopeehomebackend.model.Admin;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.DigestUtils;
import tw.teddysoft.ezspec.EzFeature;
import tw.teddysoft.ezspec.extension.junit5.EzScenario;
import tw.teddysoft.ezspec.keyword.Feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EzFeature
@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class AdminDaoImplementationSpec {
    @Autowired
    private AdminDao adminDao;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("Admin Retrieval",
                "As an admin user\n" +
                        "  I want to retrieve admin details by ID or name\n" +
                        "  So that I can manage admin accounts efficiently");
        feature.NewRule("Get admin");
    }

    @EzScenario
    public void get_by_id() {
        feature.newScenario("Get admin by ID")
                .Given("an admin ID", env -> {
                    env.put("id", "17335ce6-af7c-4c21-af55-9eca9dc5dfb7");
                })
                .When("I retrieve the admin by ID", env -> {
                    env.put("admin", adminDao.getById((env.gets("id"))));
                })
                .ThenSuccess(env -> {
                    Admin admin = env.get("admin", Admin.class);
                    assertNotNull(admin);
                    assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", admin.getId());
                    assertEquals("admin", admin.getName());
                    assertEquals(DigestUtils.md5DigestAsHex("admin".getBytes()), admin.getPassword());
                })
                .Execute();
    }

    @EzScenario
    public void get_by_name() {
        feature.newScenario("Get admin by name")
                .Given("an admin name", env -> {
                    env.put("name", "admin");
                })
                .When("I retrieve the admin by name", env -> {
                    env.put("admin", adminDao.getByName((env.gets("name"))));
                })
                .ThenSuccess(env -> {
                    Admin admin = env.get("admin", Admin.class);
                    assertNotNull(admin);
                    assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", admin.getId());
                    assertEquals("admin", admin.getName());
                    assertEquals(DigestUtils.md5DigestAsHex("admin".getBytes()), admin.getPassword());
                })
                .Execute();
    }
}
