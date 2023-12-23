package com.calvinwan.shopeehomebackend.dao;

import com.calvinwan.shopeehomebackend.dto.UserDto;
import com.calvinwan.shopeehomebackend.model.User;

public interface UserDao {
    public String insert(UserDto userDto);

    User getById(String userId);

    User getByEmail(String email);

    void updateById(String userId, UserDto userDto);

    void deleteById(String userId);
}
