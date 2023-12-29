package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.dto.admin.AdminLoginDto;
import com.calvinwan.shopeehomebackend.model.Admin;
import com.calvinwan.shopeehomebackend.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login")
    public ResponseEntity<Admin> login(@RequestBody @Valid AdminLoginDto adminLoginDto) {
        Admin admin = adminService.login(adminLoginDto);
        if (admin != null) {
            return ResponseEntity.status(HttpStatus.OK).body(admin);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
