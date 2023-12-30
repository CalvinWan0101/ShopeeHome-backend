package com.calvinwan.shopeehomebackend.service;

import com.calvinwan.shopeehomebackend.dto.product.ProductNameDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductPreviewDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.model.Product;

import java.util.List;

public interface ProductService {
    String insert(ProductDto productDto);

    Product getById(String id);

    List<String> getIdByName(String name);

    List<ProductNameDto> getAllName();

    List<ProductNameDto> getAllNameByShopId(String shopId);

    ProductPreviewDto getPreviewById(String id);

    void updateById(String id, ProductDto productDto);

    void updateSalesById(String id, int productSales);

    void deleteById(String id);
}
