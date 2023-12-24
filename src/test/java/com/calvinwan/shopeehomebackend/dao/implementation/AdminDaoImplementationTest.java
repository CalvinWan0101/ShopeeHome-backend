package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.AdminDao;
import com.calvinwan.shopeehomebackend.model.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class AdminDaoImplementationTest {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void get_by_id() {
        Admin admin = adminDao.getById("17335ce6-af7c-4c21-af55-9eca9dc5dfb7");
        String hashedPassword = DigestUtils.md5DigestAsHex("admin".getBytes());

        assertNotNull(admin);
        assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", admin.getId());
        assertEquals("admin", admin.getName());
        assertEquals(hashedPassword, admin.getPassword());
    }

    @Test
    public void get_by_name(){
        Admin admin = adminDao.getByName("admin");
        String hashedPassword = DigestUtils.md5DigestAsHex("admin".getBytes());

        assertNotNull(admin);
        assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", admin.getId());
        assertEquals("admin", admin.getName());
        assertEquals(hashedPassword, admin.getPassword());
    }
}