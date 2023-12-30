package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dao.ProductDao;
import com.calvinwan.shopeehomebackend.dto.product.ProductNameDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductPreviewDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.model.Product;
import com.calvinwan.shopeehomebackend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImplementation.class);

    @Autowired
    private ProductDao productDao;

    @Override
    public String insert(ProductDto productDto) {
        return productDao.insert(productDto);
    }

    @Override
    public Product getById(String id) {
        return productDao.getById(id);
    }

    @Override
    public List<String> getIdByName(String name) {
        return productDao.getIdByName(name);
    }

    @Override
    public List<ProductNameDto> getAllName() {
        return productDao.getAllName();
    }

    @Override
    public List<ProductNameDto> getAllNameByShopId(String shopId) {
        return productDao.getAllNameByShopId(shopId);
    }

    @Override
    public ProductPreviewDto getPreviewById(String id) {
        Product product = productDao.getById(id);
        if (product == null) {
            return null;
        }
        int finalPrice = product.getPrice();
        if (product.getDiscountRate() != null && product.getDiscountDate().after(new Date(System.currentTimeMillis()))) {
            finalPrice = (int) (product.getPrice() * product.getDiscountRate());
        }

        return new ProductPreviewDto(
                product.getName(),
                finalPrice,
                product.getSales(),
                product.getImages().get(0)
        );
    }

    @Override
    public void updateById(String id, ProductDto productDto) {
        productDao.updateById(id, productDto);
    }

    @Override
    public void updateSalesById(String id, int productSales) {
        productDao.updateSalesById(id, productSales);
    }

    @Override
    public void deleteById(String id) {
        productDao.deleteById(id);
    }
}
