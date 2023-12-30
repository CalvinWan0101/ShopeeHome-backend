package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class UserDaoImplementationTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void get_by_id() {
        User user = userDao.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");
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
    public void get_by_email() {
        User user = userDao.getByEmail("user1@gmail.com");
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

        String id = userDao.insert(userDto);

        User user = userDao.getById(id);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("user87@gmail.com", user.getEmail());
        assertEquals("user87", user.getPassword());
        assertEquals("user87", user.getName());
        assertEquals("0909877877", user.getPhoneNumber());
        assertEquals("user87_avatar", user.getAvatar());
        assertEquals(List.of("address-user87-A", "address-user87-B", "address-user87-C"), user.getAddresses());
        assertFalse(user.isDeleted());
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

        userDao.updateById("30e7e267-c791-424a-a94b-fa5e695d27e7", userDto);


        User user = userDao.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");
        assertNotNull(user);
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", user.getId());
        assertEquals("userNew@gmail.com", user.getEmail());
        assertEquals("userNew", user.getPassword());
        assertEquals("userNew", user.getName());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals("userNew_avatar", user.getAvatar());
        assertEquals(List.of("address-userNew-A", "address-userNew-B", "address-userNew-C"), user.getAddresses());
        assertFalse(user.isDeleted());
    }
}