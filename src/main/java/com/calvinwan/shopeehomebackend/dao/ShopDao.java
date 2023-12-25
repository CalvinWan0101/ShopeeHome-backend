package com.calvinwan.shopeehomebackend.dao;

import com.calvinwan.shopeehomebackend.dto.ShopDto;
import com.calvinwan.shopeehomebackend.model.Shop;

public interface ShopDao {
    public String insert(ShopDto shopDto);

    Shop getById(String id);

    Shop getByEmail(String email);

    void updateById(String id, ShopDto shopDto);
}
