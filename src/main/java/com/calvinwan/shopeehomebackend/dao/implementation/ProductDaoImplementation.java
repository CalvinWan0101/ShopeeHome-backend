package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ProductDao;
import com.calvinwan.shopeehomebackend.dto.product.ProductNameDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.mapper.ProductNameRowMapper;
import com.calvinwan.shopeehomebackend.mapper.ProductRowMapper;
import com.calvinwan.shopeehomebackend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImplementation implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public String insert(ProductDto productDto) {
        String sql = "INSERT INTO product (id, name, amount, sales, price, description, discount_rate, discount_date, shop_id, is_deleted) VALUES (:id, :name, :amount, :sales, :price, :description, :discountRate, :discountDate, :shopId, :isDeleted)";
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString());
        map.put("name", productDto.getName());
        map.put("amount", productDto.getAmount());
        map.put("sales", 0);
        map.put("price", productDto.getPrice());
        map.put("description", productDto.getDescription());
        map.put("discountRate", productDto.getDiscountRate());
        map.put("discountDate", productDto.getDiscountDate());
        map.put("shopId", productDto.getShopId());
        map.put("isDeleted", productDto.isDeleted());
        jdbcTemplate.update(sql, map);

        List<String> images = productDto.getImages();
        String sqlImage = "INSERT INTO product_image (product_id, image_order, image) VALUES (:id, :imageOrder, :image)";
        for (int i = 0; i < images.size(); i++) {
            Map<String, Object> mapImage = new HashMap<>();
            mapImage.put("id", map.get("id"));
            mapImage.put("imageOrder", i + 1);
            mapImage.put("image", images.get(i));
            jdbcTemplate.update(sqlImage, mapImage);
        }

        return (String) map.get("id");
    }

    @Override
    public Product getById(String id) {
        String sql = "SELECT id, name, amount, sales, price, description, discount_rate, discount_date, shop_id, is_deleted FROM product WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<Product> products = jdbcTemplate.query(sql, map, new ProductRowMapper());
        if (products.isEmpty()) {
            return null;
        }
        Product product = products.get(0);

        if (product.getDiscountDate() != null && product.getDiscountDate().before(new Date(System.currentTimeMillis()))) {
            product.setDiscountRate(null);
            product.setDiscountDate(null);
        }

        String sqlImage = "SELECT image FROM product_image WHERE product_id = :id ORDER BY image_order";
        List<String> images = jdbcTemplate.queryForList(sqlImage, map, String.class);
        product.setImages(images);
        return product;
    }

    @Override
    public List<String> getIdByName(String name) {
        String sql = "SELECT id FROM product WHERE LOWER(name) LIKE LOWER(:name) AND is_deleted = FALSE";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "%" + name + "%");
        List<String> ids = jdbcTemplate.queryForList(sql, map, String.class);
        if (ids.isEmpty()) {
            return null;
        }
        return ids;
    }

    @Override
    public List<ProductNameDto> getAllName() {
        String sql = "SELECT id, name FROM product WHERE is_deleted = FALSE";
        List<ProductNameDto> products = jdbcTemplate.query(sql, new ProductNameRowMapper());
        return products;
    }

    @Override
    public List<ProductNameDto> getAllNameByShopId(String shopId) {
        String sql = "SELECT id, name FROM product WHERE shop_id = :shopId AND is_deleted = FALSE";
        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);
        List<ProductNameDto> products = jdbcTemplate.query(sql, map, new ProductNameRowMapper());
        return products;
    }

    @Override
    public void updateById(String id, ProductDto productDto) {
        String sql = "UPDATE product SET name = :name, amount = :amount, price = :price, description = :description, discount_rate = :discountRate, discount_date = :discountDate, shop_id = :shopId, is_deleted = :isDeleted WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", productDto.getName());
        map.put("amount", productDto.getAmount());
        map.put("price", productDto.getPrice());
        map.put("description", productDto.getDescription());
        map.put("discountRate", productDto.getDiscountRate());
        map.put("discountDate", productDto.getDiscountDate());
        map.put("shopId", productDto.getShopId());
        map.put("isDeleted", productDto.isDeleted());
        jdbcTemplate.update(sql, map);

        String sqlDeleteImage = "DELETE FROM product_image WHERE product_id = :id";
        jdbcTemplate.update(sqlDeleteImage, map);

        List<String> images = productDto.getImages();
        String sqlImage = "INSERT INTO product_image (product_id, image_order, image) VALUES (:id, :imageOrder, :image)";
        for (int i = 0; i < images.size(); i++) {
            Map<String, Object> mapImage = new HashMap<>();
            mapImage.put("id", map.get("id"));
            mapImage.put("imageOrder", i + 1);
            mapImage.put("image", images.get(i));
            jdbcTemplate.update(sqlImage, mapImage);
        }
    }

    @Override
    public void updateSalesById(String id, int productSales) {
        Product product = getById(id);
        String sql = "UPDATE product SET amount = :amount, sales = :sales WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("amount", product.getAmount() - productSales);
        map.put("sales", product.getSales() + productSales);
        jdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteById(String id) {
        String sql = "UPDATE product SET is_deleted = true WHERE id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        jdbcTemplate.update(sql, map);
    }

    @Override
    public void updateImagesById(String id, List<String> images) {
        String sql = "DELETE FROM product_image WHERE product_id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        jdbcTemplate.update(sql, map);

        String sqlImage = "INSERT INTO product_image (product_id, image_order, image) VALUES (:id, :imageOrder, :image)";
        for (int i = 0; i < images.size(); i++) {
            Map<String, Object> mapImage = new HashMap<>();
            mapImage.put("id", map.get("id"));
            mapImage.put("imageOrder", i + 1);
            mapImage.put("image", images.get(i));
            jdbcTemplate.update(sqlImage, mapImage);
        }
    }
}
