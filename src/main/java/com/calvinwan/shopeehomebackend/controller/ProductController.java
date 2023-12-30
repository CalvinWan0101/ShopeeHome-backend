package com.calvinwan.shopeehomebackend.controller;

import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductNameDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductPreviewDto;
import com.calvinwan.shopeehomebackend.model.Product;
import com.calvinwan.shopeehomebackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) {
        Product product = productService.getById(id);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/product/name/{name}") // TODO: change to give the simple information
    public ResponseEntity<List<Product>> getByName(@PathVariable String name) {
        List<Product> products = productService.getByName(name);

        if (products != null) {
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/product/name/all")
    public ResponseEntity<List<ProductNameDto>> getAllName() {
        List<ProductNameDto> products = productService.getAllName();

        if (products != null) {
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/product/name/shop/{shopId}")
    public ResponseEntity<List<ProductNameDto>> getAllNameByShopId(@PathVariable String shopId) {
        List<ProductNameDto> products = productService.getAllNameByShopId(shopId);

        if (!products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/product/preview/{id}")
    public ResponseEntity<ProductPreviewDto> getPreviewById(@PathVariable String id) {
        ProductPreviewDto product = productService.getPreviewById(id);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateById(@PathVariable String id,
                                              @RequestBody @Valid ProductDto productDto) {
        Product testProduct = productService.getById(id);
        if (testProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateById(id, productDto);
        Product product = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/product/sales/{id}")
    public ResponseEntity<Product> updateSalesById(@PathVariable String id,
                                                   @RequestBody @Valid int productSales) {
        Product testProduct = productService.getById(id);
        if (testProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateSalesById(id, productSales);
        Product product = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
