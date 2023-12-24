package com.calvinwan.shopeehomebackend.dao;

import com.calvinwan.shopeehomebackend.model.Admin;
import com.calvinwan.shopeehomebackend.model.User;

public interface AdminDao {
    Admin getById(String id);

    Admin getByName(String name);
}
