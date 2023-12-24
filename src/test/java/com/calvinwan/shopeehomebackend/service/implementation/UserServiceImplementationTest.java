package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dto.UserDto;
import com.calvinwan.shopeehomebackend.dto.UserLoginDto;
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

        String hashedPassword = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        String id = userService.insert(userDto);

        User user = userService.getById(id);
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("test100", user.getName());
        assertEquals("test100@gmail.com", user.getEmail());
        assertEquals("0909100100", user.getPhoneNumber());
        assertEquals(hashedPassword, user.getPassword());
        assertEquals(userDto.getAddresses(), user.getAddresses());
    }

    @Test
    @Transactional
    public void insert_with_exist_email() {
        UserDto userDto = new UserDto(
                "test100",
                "test1@gmail.com",
                "0909100100",
                "test100",
                List.of("address-test100-A", "address-test100-B", "address-test100-C")
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
                "newpassword",
                List.of("address-calvin-A", "address-calvin-B", "address-calvin-C")
        );

        userService.updateById("30e7e267-c791-424a-a94b-fa5e695d27e7", userDto);

        User user = userService.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");
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
        userService.deleteById("30e7e267-c791-424a-a94b-fa5e695d27e7");
        User user = userService.getById("30e7e267-c791-424a-a94b-fa5e695d27e7");

        assertNull(user);
    }

    @Test
    public void login_success() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("calvin@gmail.com");
        userLoginDto.setPassword("calvin");

        User user = userService.login(userLoginDto);
        String hashedPassword = DigestUtils.md5DigestAsHex("calvin".getBytes());

        assertNotNull(user);
        assertEquals("74244e64-73a8-46a7-ac69-4b20efab0d82", user.getId());
        assertEquals("Calvin", user.getName());
        assertEquals("calvin@gmail.com", user.getEmail());
        assertEquals("0909000111", user.getPhoneNumber());
        assertEquals(hashedPassword, user.getPassword());
        assertEquals(List.of("address-calvin-A"), user.getAddresses());
    }

    @Test
    public void login_with_not_exist_email() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("wrong@gmail.com");
        userLoginDto.setPassword("wrong");

        assertThrows(ResponseStatusException.class, () -> {
            userService.login(userLoginDto);
        });
    }

    @Test
    public void login_with_wrong_password() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("calvin@gmail.com");
        userLoginDto.setPassword("wrong");

        assertThrows(ResponseStatusException.class, () -> {
            userService.login(userLoginDto);
        });
    }
}