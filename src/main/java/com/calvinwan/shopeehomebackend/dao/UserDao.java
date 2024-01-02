package com.calvinwan.shopeehomebackend.dao;

import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.model.User;

public interface UserDao {
    public String insert(UserDto userDto);

    User getById(String id);

    User getByEmail(String email);

    void updateById(String id, UserDto userDto);

    void updateAvatarById(String id, String avatar);
}
