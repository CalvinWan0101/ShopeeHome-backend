package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.dto.user.UserLoginDto;
import com.calvinwan.shopeehomebackend.model.User;
import com.calvinwan.shopeehomebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserDto userDto) {
        String id = userService.insert(userDto);
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        User user = userService.getById(id);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateById(@PathVariable String id,
                                           @RequestBody @Valid UserDto userDto) {
        User testUser = userService.getById(id);
        if (testUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userService.updateById(id, userDto);
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        User user = userService.login(userLoginDto);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
