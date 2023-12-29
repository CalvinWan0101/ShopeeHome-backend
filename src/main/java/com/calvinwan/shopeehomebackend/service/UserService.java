package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.dto.user.UserLoginDto;
import com.calvinwan.shopeehomebackend.model.User;

public interface UserService {
    String insert(UserDto userDto);

    User getById(String id);

    void updateById(String id, UserDto userDto);

    void deleteById(String id);

    User login(UserLoginDto userLoginDto);
}
