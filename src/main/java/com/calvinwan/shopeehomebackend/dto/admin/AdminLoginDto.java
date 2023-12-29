package com.calvinwan.shopeehomebackend.dto.admin;

import jakarta.validation.constraints.NotNull;

public class AdminLoginDto {

    @NotNull
    String name;

    @NotNull
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
