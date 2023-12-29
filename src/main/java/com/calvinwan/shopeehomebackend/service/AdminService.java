package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.admin.AdminLoginDto;
import com.calvinwan.shopeehomebackend.model.Admin;

public interface AdminService {
    Admin getById(String id);
    Admin login(AdminLoginDto adminLoginDto);
}
