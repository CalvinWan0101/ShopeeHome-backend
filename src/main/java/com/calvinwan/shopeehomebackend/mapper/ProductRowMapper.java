package com.calvinwan.shopeehomebackend.mapper;

import com.calvinwan.shopeehomebackend.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Double discountRate = rs.getDouble("discount_rate");
        if (rs.wasNull()) {
            discountRate = null;
        }

        Product product = new Product(
                rs.getString("id"),
                rs.getString("name"),
                rs.getInt("amount"),
                rs.getInt("sales"),
                rs.getInt("price"),
                rs.getString("description"),
                discountRate,
                rs.getDate("discount_date"),
                rs.getString("shop_id"),
                rs.getBoolean("is_deleted")
        );
        return product;
    }

}
