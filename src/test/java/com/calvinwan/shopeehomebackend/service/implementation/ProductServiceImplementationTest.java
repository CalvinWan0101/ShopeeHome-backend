package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dto.product.ProductNameDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductPreviewDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.model.Product;
import com.calvinwan.shopeehomebackend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class ProductServiceImplementationTest {

    @Autowired
    private ProductService productService;

    @Test
    public void get_by_id_iphone() throws IOException {
        Product product = productService.getById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

        assertNotNull(product);
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", product.getId());
        assertEquals("iphone", product.getName());
        assertEquals(90, product.getAmount());
        assertEquals(27, product.getSales());
        assertEquals(36900, product.getPrice());
        assertEquals("This is iphone", product.getDescription());
        assertEquals(0.87, product.getDiscountRate());
        assertEquals("2024-07-31", product.getDiscountDate().toString());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", product.getShopId());
        assertEquals(List.of("iphone_image_1", "iphone_image_2"), product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    public void get_by_id_xiaomi() throws IOException {
        Product product = productService.getById("8c883a21-fad1-43af-8b15-54b2c1c7a70e");

        assertNotNull(product);
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", product.getId());
        assertEquals("xiaomi", product.getName());
        assertEquals(140, product.getAmount());
        assertEquals(30, product.getSales());
        assertEquals(19999, product.getPrice());
        assertEquals("This is xiaomi", product.getDescription());
        assertEquals(0.9, product.getDiscountRate());
        assertEquals("2024-06-30", product.getDiscountDate().toString());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", product.getShopId());
        assertEquals(List.of("xiaomi_image_1", "xiaomi_image_2"), product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    public void get_by_id_tissue() throws IOException {
        Product product = productService.getById("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3");

        assertNotNull(product);
        assertEquals("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", product.getId());
        assertEquals("tissue", product.getName());
        assertEquals(52123, product.getAmount());
        assertEquals(29134, product.getSales());
        assertEquals(100, product.getPrice());
        assertEquals("This is tissue", product.getDescription());
        assertNull(product.getDiscountRate());
        assertNull(product.getDiscountDate());
        assertEquals("f0694ecf-6282-48f9-a401-49eb08067ce0", product.getShopId());
        assertEquals(List.of("tissue_image_1", "tissue_image_2"), product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    public void get_by_id_toothbrush() throws IOException {
        Product product = productService.getById("9595f97a-bf11-488a-8c15-9edf4db1c450");

        assertNotNull(product);
        assertEquals("9595f97a-bf11-488a-8c15-9edf4db1c450", product.getId());
        assertEquals("toothbrush", product.getName());
        assertEquals(279123, product.getAmount());
        assertEquals(34126, product.getSales());
        assertEquals(50, product.getPrice());
        assertEquals("This is toothbrush", product.getDescription());
        assertNull(product.getDiscountRate());
        assertNull(product.getDiscountDate());
        assertEquals("f0694ecf-6282-48f9-a401-49eb08067ce0", product.getShopId());
        assertEquals(List.of("toothbrush_image_1", "toothbrush_image_2"), product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    public void get_id_by_name() {
        List<String> ids = productService.getIdByName("i");

        assertEquals(3, ids.size());
        assertTrue(ids.stream().anyMatch(id -> "6874ada1-747f-41a7-bb9a-613d2ec0ce1d".equals(id)));
        assertTrue(ids.stream().anyMatch(id -> "8c883a21-fad1-43af-8b15-54b2c1c7a70e".equals(id)));
        assertTrue(ids.stream().anyMatch(id -> "acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3".equals(id)));
    }

    @Test
    public void get_by_name_deleted() {
        List<String> ids = productService.getIdByName("backpack");

        assertNull(ids);
    }

    @Test
    public void get_by_name_not_found() {
        List<String> ids = productService.getIdByName("not found");

        assertNull(ids);
    }

    @Test
    public void get_all_name_by_id() {
        List<ProductNameDto> productNameDtos = productService.getAllName();

        assertEquals(4, productNameDtos.size());
        assertTrue(productNameDtos.stream().anyMatch(product -> "6874ada1-747f-41a7-bb9a-613d2ec0ce1d".equals(product.getId())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "iphone".equals(product.getName())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "8c883a21-fad1-43af-8b15-54b2c1c7a70e".equals(product.getId())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "xiaomi".equals(product.getName())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3".equals(product.getId())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "tissue".equals(product.getName())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "9595f97a-bf11-488a-8c15-9edf4db1c450".equals(product.getId())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "toothbrush".equals(product.getName())));
    }

    @Test
    public void get_all_name_by_shop_id() {
        List<ProductNameDto> productNameDtos = productService.getAllNameByShopId("1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(2, productNameDtos.size());
        assertTrue(productNameDtos.stream().anyMatch(product -> "6874ada1-747f-41a7-bb9a-613d2ec0ce1d".equals(product.getId())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "iphone".equals(product.getName())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "8c883a21-fad1-43af-8b15-54b2c1c7a70e".equals(product.getId())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "xiaomi".equals(product.getName())));
    }

    @Test
    public void get_preview_by_id_with_discount() throws IOException {
        ProductPreviewDto productPreviewDto = productService.getPreviewById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

        assertNotNull(productPreviewDto);
        assertEquals("iphone", productPreviewDto.getName());
        assertEquals(32103, productPreviewDto.getFinalPrice());
        assertEquals(27, productPreviewDto.getSales());
        assertEquals("iphone_image_1", productPreviewDto.getImage());
    }

    @Test
    public void get_preview_by_id_without_discount() throws IOException {
        ProductPreviewDto productPreviewDto = productService.getPreviewById("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3");

        assertNotNull(productPreviewDto);
        assertEquals("tissue", productPreviewDto.getName());
        assertEquals(100, productPreviewDto.getFinalPrice());
        assertEquals(29134, productPreviewDto.getSales());
        assertEquals("tissue_image_1", productPreviewDto.getImage());
    }

    @Test
    public void get_preview_by_id_with_expired_discount() throws IOException {
        ProductPreviewDto productPreviewDto = productService.getPreviewById("9595f97a-bf11-488a-8c15-9edf4db1c450");

        assertNotNull(productPreviewDto);
        assertEquals("toothbrush", productPreviewDto.getName());
        assertEquals(50, productPreviewDto.getFinalPrice());
        assertEquals(34126, productPreviewDto.getSales());
        assertEquals("toothbrush_image_1", productPreviewDto.getImage());
    }

    @Test
    @Transactional
    public void insert() throws IOException, ParseException {
        ProductDto productDto = new ProductDto(
                "samsung",
                81,
                31200,
                "This is samsung",
                0.92,
                new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2024-08-15").getTime()),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of("samsung_image_1", "samsung_image_2"),
                false
        );

        String id = productService.insert(productDto);

        Product product = productService.getById(id);

        assertNotNull(product);
        assertEquals(id, product.getId());
        assertEquals("samsung", product.getName());
        assertEquals(81, product.getAmount());
        assertEquals(0, product.getSales());
        assertEquals(31200, product.getPrice());
        assertEquals("This is samsung", product.getDescription());
        assertEquals(0.92, product.getDiscountRate());
        assertEquals("2024-08-15", product.getDiscountDate().toString());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", product.getShopId());
        assertEquals(List.of("samsung_image_1", "samsung_image_2"), product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    @Transactional
    public void update_by_id() throws IOException, ParseException {
        ProductDto productDto = new ProductDto(
                "iphone",
                90,
                36900,
                "This is iphone",
                0.87,
                new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-31").getTime()),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of("xiaomi_image_1", "xiaomi_image_2"),
                false
        );
        productService.updateById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productDto);

        Product product = productService.getById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

        assertNotNull(product);
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", product.getId());
        assertEquals("iphone", product.getName());
        assertEquals(90, product.getAmount());
        assertEquals(27, product.getSales());
        assertEquals(36900, product.getPrice());
        assertEquals("This is iphone", product.getDescription());
        assertEquals(0.87, product.getDiscountRate());
        assertEquals("2024-07-31", product.getDiscountDate().toString());
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", product.getShopId());
        assertEquals(List.of("xiaomi_image_1", "xiaomi_image_2"), product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    @Transactional
    public void update_sales_by_id() {
        productService.updateSalesById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", 29);

        Product product = productService.getById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
        assertEquals(90 - 29, product.getAmount());
        assertEquals(27 + 29, product.getSales());
    }

    @Test
    @Transactional
    public void delete_by_id() {
        productService.deleteById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

        Product product = productService.getById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
        assertTrue(product.isDeleted());
    }

}