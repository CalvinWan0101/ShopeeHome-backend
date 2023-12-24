package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.UserDto;
import com.calvinwan.shopeehomebackend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class UserDaoImplementationTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void get_by_id() {
        User user = userDao.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");
        String hashedPassword = DigestUtils.md5DigestAsHex("test1".getBytes());
        List<String> addresses = List.of("address-test1-A", "address-test1-B", "address-test1-C");

        assertNotNull(user);
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", user.getId());
        assertEquals("test1", user.getName());
        assertEquals("test1@gmail.com", user.getEmail());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals(hashedPassword, user.getPassword());
        assertEquals(addresses, user.getAddresses());
    }

    @Test
    public void get_by_email() {
        User user = userDao.getByEmail("test1@gmail.com");
        String hashedPassword = DigestUtils.md5DigestAsHex("test1".getBytes());
        List<String> addresses = List.of("address-test1-A", "address-test1-B", "address-test1-C");

        assertNotNull(user);
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", user.getId());
        assertEquals("test1", user.getName());
        assertEquals("test1@gmail.com", user.getEmail());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals(hashedPassword, user.getPassword());
        assertEquals(addresses, user.getAddresses());
    }

    @Test
    @Transactional
    public void insert() {
        UserDto userDto = new UserDto(
                "test100",
                "test100@gmail.com",
                "0909100100",
                "test100",
                List.of("address-test100-A", "address-test100-B", "address-test100-C")
        );

        String id = userDao.insert(userDto);

        User user2 = userDao.getById(id);

        assertNotNull(user2);
        assertEquals(id, user2.getId());
        assertEquals("test100", user2.getName());
        assertEquals("test100@gmail.com", user2.getEmail());
        assertEquals("0909100100", user2.getPhoneNumber());
        assertEquals("test100", user2.getPassword());
        assertEquals(List.of("address-test100-A", "address-test100-B", "address-test100-C"), user2.getAddresses());
    }

    @Test
    @Transactional
    public void update_by_id() {
        UserDto userDto = new UserDto(
                "Calvin",
                "calvin@gmail.com",
                "0909001001",
                "newpassword",
                List.of("address-calvin-A", "address-calvin-B", "address-calvin-C")
        );

        userDao.updateById("30e7e267-c791-424a-a94b-fa5e695d27e7", userDto);

        User user = userDao.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");
        assertNotNull(user);
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", user.getId());
        assertEquals("Calvin", user.getName());
        assertEquals("calvin@gmail.com", user.getEmail());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals("newpassword", user.getPassword());
        assertEquals(List.of("address-calvin-A", "address-calvin-B", "address-calvin-C"), user.getAddresses());

    }

    @Test
    @Transactional
    public void delete_by_id() {
        userDao.deleteById("30e7e267-c791-424a-a94b-fa5e695d27e7");
        User user = userDao.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");

        assertNull(user);
    }
}