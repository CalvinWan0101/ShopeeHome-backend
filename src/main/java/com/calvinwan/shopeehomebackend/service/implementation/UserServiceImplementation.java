package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.dto.user.UserLoginDto;
import com.calvinwan.shopeehomebackend.model.User;
import com.calvinwan.shopeehomebackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImplementation implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    private UserDao userDao;

    @Override
    public String insert(UserDto userDto) {
        userDto.setDeleted(false);
        User user = userDao.getByEmail(userDto.getEmail());
        if (user != null) {
            log.warn("Email {} already exists", userDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        userDto.setPassword(hashedPassword);

        return userDao.insert(userDto);
    }

    @Override
    public User getById(String id) {
        return userDao.getById(id);
    }

    @Override
    public void updateById(String id, UserDto userDto) {
        String hashedPassword = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes());
        userDto.setPassword(hashedPassword);
        userDao.updateById(id, userDto);
    }

    @Override
    public void deleteById(String id) {
        User user = userDao.getById(id);
        if (user != null) {
            UserDto userDto = new UserDto(user.getEmail(), user.getPassword(), user.getName(), user.getPhoneNumber(), user.getAvatar(), user.getAddresses(), true);
            userDao.updateById(id, userDto);
        }
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        User user = userDao.getByEmail(userLoginDto.getEmail());
        if (user == null) {
            log.warn("Email {} not registed", userLoginDto.getEmail());
            String email = userLoginDto.getEmail();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email " + email + " not registered");
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginDto.getPassword().getBytes());

        if (!user.getPassword().equals(hashedPassword)) {
            log.warn("Password is incorrect");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
        }
        return user;
    }
}
