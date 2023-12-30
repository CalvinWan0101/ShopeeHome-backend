package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.dto.user.UserLoginDto;
import com.calvinwan.shopeehomebackend.model.User;
import com.calvinwan.shopeehomebackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class UserServiceImplementationTest {

    @Autowired
    private UserService userService;

    @Test
    public void get_by_id() {
        User user = userService.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");
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
    }

    @Test
    @Transactional
    public void insert() {
        UserDto userDto = new UserDto(
                "user87@gmail.com",
                "user87",
                "user87",
                "0909877877",
                "user87_avatar",
                List.of("address-user87-A", "address-user87-B", "address-user87-C"),
                false
        );

        String hashedPassword = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        String id = userService.insert(userDto);

        User user = userService.getById(id);
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("user87@gmail.com", user.getEmail());
        assertEquals(hashedPassword, user.getPassword());
        assertEquals("user87", user.getName());
        assertEquals("0909877877", user.getPhoneNumber());
        assertEquals(List.of("address-user87-A", "address-user87-B", "address-user87-C"), user.getAddresses());
        assertFalse(user.isDeleted());
    }

    @Test
    @Transactional
    public void insert_with_exist_email() {
        UserDto userDto = new UserDto(
                "user1@gmail.com",
                "user87",
                "user87",
                "0909877877",
                "user87_avatar",
                List.of("address-user87-A", "address-user87-B", "address-user87-C"),
                false
        );

        assertThrows(ResponseStatusException.class, () -> {
            userService.insert(userDto);
        });
    }

    @Test
    @Transactional
    public void update_by_id() {
        UserDto userDto = new UserDto(
                "userNew@gmail.com",
                "userNew",
                "userNew",
                "0909001001",
                "userNew_avatar",
                List.of("address-userNew-A", "address-userNew-B", "address-userNew-C"),
                false
        );

        userService.updateById("30e7e267-c791-424a-a94b-fa5e695d27e7", userDto);

        User user = userService.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");
        String hashedPassword = DigestUtils.md5DigestAsHex("userNew".getBytes());
        assertNotNull(user);
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", user.getId());
        assertEquals("userNew@gmail.com", user.getEmail());
        assertEquals(hashedPassword, user.getPassword());
        assertEquals("userNew", user.getName());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals(List.of("address-userNew-A", "address-userNew-B", "address-userNew-C"), user.getAddresses());
        assertFalse(user.isDeleted());
    }

    @Test
    @Transactional
    public void delete_by_id() {
        userService.deleteById("30e7e267-c791-424a-a94b-fa5e695d27e7");
        User user = userService.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");

        assertTrue(user.isDeleted());
    }

    @Test
    public void login_success() {
        UserLoginDto userLoginDto = new UserLoginDto("user1@gmail.com", "user1");

        User user = userService.login(userLoginDto);

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
    }

    @Test
    public void login_with_not_exist_email() {
        UserLoginDto userLoginDto = new UserLoginDto("wrong@gmail.com", "user1");

        assertThrows(ResponseStatusException.class, () -> {
            userService.login(userLoginDto);
        });
    }

    @Test
    public void login_with_wrong_password() {
        UserLoginDto userLoginDto = new UserLoginDto("user1@gmail.com", "wrong");

        assertThrows(ResponseStatusException.class, () -> {
            userService.login(userLoginDto);
        });
    }
}