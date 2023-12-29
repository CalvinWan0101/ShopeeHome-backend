package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dto.admin.AdminLoginDto;
import com.calvinwan.shopeehomebackend.model.Admin;
import com.calvinwan.shopeehomebackend.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class AdminServiceImplementationTest {

    @Autowired
    AdminService adminService;

    @Test
    public void get_by_id() {
        Admin admin = adminService.getById("17335ce6-af7c-4c21-af55-9eca9dc5dfb7");
        String hashedPassword = DigestUtils.md5DigestAsHex("admin".getBytes());

        assertNotNull(admin);
        assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", admin.getId());
        assertEquals("admin", admin.getName());
        assertEquals(hashedPassword, admin.getPassword());
    }

    @Test
    public void login_success() {
        AdminLoginDto adminLoginDto = new AdminLoginDto();
        adminLoginDto.setName("admin");
        adminLoginDto.setPassword("admin");

        Admin admin = adminService.login(adminLoginDto);
        String hashedPassword = DigestUtils.md5DigestAsHex("admin".getBytes());

        assertNotNull(admin);
        assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", admin.getId());
        assertEquals("admin", admin.getName());
        assertEquals(hashedPassword, admin.getPassword());
    }

    @Test
    public void login_with_not_exist_name() {
        AdminLoginDto adminLoginDto = new AdminLoginDto();
        adminLoginDto.setName("wrong");
        adminLoginDto.setPassword("admin");

        assertThrows(ResponseStatusException.class, () -> {
            adminService.login(adminLoginDto);
        });
    }

    @Test
    public void login_with_wrong_password() {
        AdminLoginDto adminLoginDto = new AdminLoginDto();
        adminLoginDto.setName("admin");
        adminLoginDto.setPassword("wrong");

        assertThrows(ResponseStatusException.class, () -> {
            adminService.login(adminLoginDto);
        });
    }

}