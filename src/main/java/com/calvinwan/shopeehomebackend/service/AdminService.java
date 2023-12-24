package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.AdminLoginDto;
import com.calvinwan.shopeehomebackend.model.Admin;

public interface AdminService {
    Admin getById(String id);
    Admin login(AdminLoginDto adminLoginDto);
}
