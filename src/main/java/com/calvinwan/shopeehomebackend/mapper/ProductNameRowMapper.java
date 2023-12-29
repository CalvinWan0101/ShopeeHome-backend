package com.calvinwan.shopeehomebackend.mapper;

import com.calvinwan.shopeehomebackend.dto.product.ProductNameDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductNameRowMapper implements RowMapper<ProductNameDto> {
    @Override
    public ProductNameDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductNameDto productNameDto = new ProductNameDto(
                rs.getString("id"),
                rs.getString("name")
        );
        return productNameDto;
    }
}
