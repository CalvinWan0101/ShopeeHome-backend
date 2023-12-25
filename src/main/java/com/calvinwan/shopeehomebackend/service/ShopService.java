package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.ShopDto;
import com.calvinwan.shopeehomebackend.dto.ShopLoginDto;
import com.calvinwan.shopeehomebackend.model.Shop;

public interface ShopService {
    String insert(ShopDto shopDto);

    Shop getById(String id);

    void updateById(String id, ShopDto shopDto);

    void deleteById(String id);

    Shop login(ShopLoginDto shopLoginDto);
}
