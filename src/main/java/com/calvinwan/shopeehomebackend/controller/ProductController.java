package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.model.Product;
import com.calvinwan.shopeehomebackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> insert(@RequestBody @Valid ProductDto productDto) {
        String id = productService.insert(productDto);
        Product product = productService.getById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}
