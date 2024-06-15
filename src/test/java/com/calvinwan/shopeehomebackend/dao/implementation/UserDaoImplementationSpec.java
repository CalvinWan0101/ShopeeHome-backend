package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tw.teddysoft.ezspec.keyword.Feature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class UserDaoImplementationSpec {

    @Autowired
    private UserDao userDao;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("User Management", "As an admin\nI want to manage user information\nSo that I can keep track of user data");
        feature.NewRule("Retrieve User");
        feature.NewRule("Create User");
        feature.NewRule("Update User");
    }

    @Test
    public void get_by_id() {
        feature.newScenario("Get User by ID").withRule("Retrieve User")
                .Given("a user ID", env -> {
                    env.put("userId", "30e7e267-c791-424a-a94b-fa5e695d27e7");
                })
                .When("I retrieve the user by ID", env -> {
                    env.put("user", userDao.getById(env.gets("userId")));
                })
                .ThenSuccess(env -> {
                    User user = env.get("user", User.class);
                    String hashedPassword = DigestUtils.md5DigestAsHex("user1".getBytes());
                    List<String> addresses = List.of("address-user1-A", "address-user1-B", "address-user1-C");

                    assertNotNull(user);
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", user.getId());
                    assertEquals("user1", user.getName());
                    assertEquals("user1@gmail.com", user.getEmail());
                    assertEquals("0909001001", user.getPhoneNumber());
                    assertEquals("user1_avatar", user.getAvatar());
                    assertEquals(hashedPassword, user.getPassword());
                    assertEquals(addresses, user.getAddresses());
                })
                .Execute();
    }

    @Test
    public void get_by_email() {
        feature.newScenario("Get User by Email").withRule("Retrieve User")
                .Given("a user email", env -> {
                    env.put("email", "user1@gmail.com");
                })
                .When("I retrieve the user by email", env -> {
                    env.put("user", userDao.getByEmail(env.gets("email")));
                })
                .ThenSuccess(env -> {
                    User user = env.get("user", User.class);
                    String hashedPassword = DigestUtils.md5DigestAsHex("user1".getBytes());
                    List<String> addresses = List.of("address-user1-A", "address-user1-B", "address-user1-C");

                    assertNotNull(user);
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", user.getId());
                    assertEquals("user1", user.getName());
                    assertEquals("user1@gmail.com", user.getEmail());
                    assertEquals("0909001001", user.getPhoneNumber());
                    assertEquals("user1_avatar", user.getAvatar());
                    assertEquals(hashedPassword, user.getPassword());
                    assertEquals(addresses, user.getAddresses());
                })
                .Execute();
    }

    @Test
    @Transactional
    public void insert() {
        feature.newScenario("Create New User").withRule("Create User")
                .Given("a new user DTO", env -> {
                    UserDto userDto = new UserDto(
                            "user87@gmail.com",
                            "user87",
                            "user87",
                            "0909877877",
                            "user87_avatar",
                            List.of("address-user87-A", "address-user87-B", "address-user87-C"),
                            false
                    );
                    env.put("userDto", userDto);
                })
                .When("I insert the new user", env -> {
                    env.put("userId", userDao.insert(env.get("userDto", UserDto.class)));
                })
                .ThenSuccess(env -> {
                    User user = userDao.getById(env.gets("userId"));

                    assertNotNull(user);
                    assertEquals(env.gets("userId"), user.getId());
                    assertEquals("user87@gmail.com", user.getEmail());
                    assertEquals("user87", user.getPassword());
                    assertEquals("user87", user.getName());
                    assertEquals("0909877877", user.getPhoneNumber());
                    assertEquals("user87_avatar", user.getAvatar());
                    assertEquals(List.of("address-user87-A", "address-user87-B", "address-user87-C"), user.getAddresses());
                    assertFalse(user.isDeleted());
                })
                .Execute();
    }

    @Test
    @Transactional
    public void update_by_id() {
        feature.newScenario("Update User by ID").withRule("Update User")
                .Given("an existing user ID and new user DTO", env -> {
                    UserDto userDto = new UserDto(
                            "userNew@gmail.com",
                            "userNew",
                            "userNew",
                            "0909001001",
                            "userNew_avatar",
                            List.of("address-userNew-A", "address-userNew-B", "address-userNew-C"),
                            false
                    );
                    env.put("userId", "30e7e267-c791-424a-a94b-fa5e695d27e7");
                    env.put("userDto", userDto);
                })
                .When("I update the user by ID", env -> {
                    userDao.updateById(env.gets("userId"), env.get("userDto", UserDto.class));
                })
                .ThenSuccess(env -> {
                    User user = userDao.getById(env.gets("userId"));

                    assertNotNull(user);
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", user.getId());
                    assertEquals("userNew@gmail.com", user.getEmail());
                    assertEquals("userNew", user.getPassword());
                    assertEquals("userNew", user.getName());
                    assertEquals("0909001001", user.getPhoneNumber());
                    assertEquals("userNew_avatar", user.getAvatar());
                    assertEquals(List.of("address-userNew-A", "address-userNew-B", "address-userNew-C"), user.getAddresses());
                    assertFalse(user.isDeleted());
                })
                .Execute();
    }
}
