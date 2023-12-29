package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.dto.shop.ShopDto;
import com.calvinwan.shopeehomebackend.dto.shop.ShopLoginDto;
import com.calvinwan.shopeehomebackend.model.Shop;
import com.calvinwan.shopeehomebackend.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {

    @Autowired
    ShopService shopService;

    @PostMapping("/shop/register")
    public ResponseEntity<Shop> register(@RequestBody @Valid ShopDto shopDto) {
        String id = shopService.insert(shopDto);
        Shop shop = shopService.getById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(shop);
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<Shop> getById(@PathVariable String id) {
        Shop shop = shopService.getById(id);

        if (shop != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shop);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/shop/{id}")
    public ResponseEntity<Shop> updateById(@PathVariable String id,
                                           @RequestBody @Valid ShopDto shopDto) {
        Shop testShop = shopService.getById(id);
        if (testShop == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        shopService.updateById(id, shopDto);
        Shop shop = shopService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(shop);
    }

    @DeleteMapping("/shop/{id}")
    public ResponseEntity<Shop> deleteById(@PathVariable String id) {
        shopService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/shop/login")
    public ResponseEntity<Shop> login(@RequestBody @Valid ShopLoginDto shopLoginDto) {
        Shop shop = shopService.login(shopLoginDto);
        if (shop != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shop);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
