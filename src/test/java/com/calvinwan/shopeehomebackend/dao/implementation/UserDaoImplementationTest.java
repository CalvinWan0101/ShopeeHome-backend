package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.UserDto;
import com.calvinwan.shopeehomebackend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoImplementationTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void get_by_id() {
        User user = userDao.getById("UUID-1");

        assertNotNull(user);
        assertEquals("UUID-1", user.getId());
        assertEquals("test1", user.getName());
        assertEquals("test1@gmail.com", user.getEmail());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals("test1", user.getPassword());
    }

    @Test
    public void get_by_email() {
        User user = userDao.getByEmail("test1@gmail.com");

        assertNotNull(user);
        assertEquals("UUID-1", user.getId());
        assertEquals("test1", user.getName());
        assertEquals("test1@gmail.com", user.getEmail());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals("test1", user.getPassword());
    }

    @Test
    @Transactional
    public void insert() {
        UserDto userDto = new UserDto(
                "test100",
                "test100@gmail.com",
                "0909100100",
                "test100"
        );

        String id = userDao.insert(userDto);

        User user2 = userDao.getById(id);
        assertNotNull(user2);
        assertEquals(id, user2.getId());
        assertEquals("test100", user2.getName());
        assertEquals("test100@gmail.com", user2.getEmail());
        assertEquals("0909100100", user2.getPhoneNumber());
        assertEquals("test100", user2.getPassword());
    }

    @Test
    @Transactional
    public void update_by_id() {
        UserDto userDto = new UserDto(
                "Calvin",
                "calvin@gmail.com",
                "0909001001",
                "newpassword"
        );

        userDao.updateById("UUID-1", userDto);

        User user = userDao.getById("UUID-1");
        assertNotNull(user);
        assertEquals("UUID-1", user.getId());
        assertEquals("Calvin", user.getName());
        assertEquals("calvin@gmail.com", user.getEmail());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    @Transactional
    public void delete_by_id() {
        userDao.deleteById("UUID-1");
        User user = userDao.getById("UUID-1");

        assertNull(user);
    }
}