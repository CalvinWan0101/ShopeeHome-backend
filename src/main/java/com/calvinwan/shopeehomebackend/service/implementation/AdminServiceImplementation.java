package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dao.AdminDao;
import com.calvinwan.shopeehomebackend.dto.admin.AdminLoginDto;
import com.calvinwan.shopeehomebackend.model.Admin;
import com.calvinwan.shopeehomebackend.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminServiceImplementation implements AdminService {

    private final static Logger log = LoggerFactory.getLogger(AdminServiceImplementation.class);

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getById(String id) {
        return adminDao.getById(id);
    }

    @Override
    public Admin login(AdminLoginDto adminLoginDto) {
        Admin admin = adminDao.getByName(adminLoginDto.getName());
        if (admin == null) {
            log.warn("Admin {} not registed", adminLoginDto.getName());
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(adminLoginDto.getPassword().getBytes());

        if (!hashedPassword.equals(admin.getPassword())) {
            log.warn("Password not match");
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        return admin;
    }
}
