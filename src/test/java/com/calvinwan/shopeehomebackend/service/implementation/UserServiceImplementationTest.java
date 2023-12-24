package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dto.UserDto;
import com.calvinwan.shopeehomebackend.model.User;
import com.calvinwan.shopeehomebackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplementationTest {

    @Autowired
    private UserService userService;

    @Test
    public void get_by_id() {
        User user = userService.getById("UUID-1");
        String hashedPassword = DigestUtils.md5DigestAsHex("test1".getBytes());

        assertNotNull(user);
        assertEquals("UUID-1", user.getId());
        assertEquals("test1", user.getName());
        assertEquals("test1@gmail.com", user.getEmail());
        assertEquals("0909001001", user.getPhoneNumber());
        assertEquals(hashedPassword, user.getPassword());
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

        String hashedPassword = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        String id = userService.insert(userDto);

        User user = userService.getById(id);
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("test100", user.getName());
        assertEquals("test100@gmail.com", user.getEmail());
        assertEquals("0909100100", user.getPhoneNumber());
        assertEquals(hashedPassword, user.getPassword());
    }

    @Test
    @Transactional
    public void insert_with_exist_email() {
        UserDto userDto = new UserDto(
                "test100",
                "test1@gmail.com",
                "0909100100",
                "test100"
        );

        assertThrows(ResponseStatusException.class, () -> {
            userService.insert(userDto);
        });
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

        userService.updateById("UUID-1", userDto);

        User user = userService.getById("UUID-1");
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
        userService.deleteById("UUID-1");
        User user = userService.getById("UUID-1");

        assertNull(user);
    }
}