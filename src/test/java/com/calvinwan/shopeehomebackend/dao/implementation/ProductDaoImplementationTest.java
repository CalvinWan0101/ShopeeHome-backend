package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.ImageBase64Converter;
import com.calvinwan.shopeehomebackend.dao.ProductDao;
import com.calvinwan.shopeehomebackend.dto.product.ProductNameDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.model.Product;
import org.junit.jupiter.api.BeforeEach;
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
public class ProductDaoImplementationTest {

    @BeforeEach
    public void beforeEach() throws IOException, ParseException {
        List<String> images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_1.jpg"),
                ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_2.jpg")
        );
        ProductDto productDto = new ProductDto(
                "iphone",
                90,
                36900,
                "This is iphone",
                0.87,
                new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-31").getTime()),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                images,
                false
        );
        productDao.updateById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productDto);

        images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/2_xiaomi/xiaomi_1.png"),
                ImageBase64Converter.imageToBase64("src/test/resources/img/2_xiaomi/xiaomi_2.png")
        );
        productDto = new ProductDto(
                "xiaomi",
                140,
                19999,
                "This is xiaomi",
                0.9,
                new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-30").getTime()),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                images,
                false
        );
        productDao.updateById("8c883a21-fad1-43af-8b15-54b2c1c7a70e", productDto);

        images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/3_tissue/tissue_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/img/3_tissue/tissue_2.webp")
        );
        productDto = new ProductDto(
                "tissue",
                52123,
                100,
                "This is tissue",
                null,
                null,
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                images,
                false
        );
        productDao.updateById("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", productDto);

        images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/4_toothbrush/toothbrush_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/img/4_toothbrush/toothbrush_2.webp")
        );
        productDto = new ProductDto(
                "toothbrush",
                279123,
                50,
                "This is toothbrush",
                null,
                null,
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                images,
                false
        );
        productDao.updateById("9595f97a-bf11-488a-8c15-9edf4db1c450", productDto);

        images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/5_backpack/backpack_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/img/5_backpack/backpack_2.webp")
        );
        productDto = new ProductDto(
                "backpack",
                297,
                2100,
                "This is backpack",
                null,
                null,
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                images,
                true
        );
        productDao.updateById("4f366b46-50ea-42d9-8216-e677f43b1819", productDto);
    }

    @Autowired
    private ProductDao productDao;

    @Test
    public void get_by_id_iphone() throws IOException {
        Product product = productDao.getById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

        List<String> images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_1.jpg").toString(),
                ImageBase64Converter.imageToBase64("src/test/resources/img/1_iphone/iphone_2.jpg").toString()
        );
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
        assertEquals(images, product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    public void get_by_id_xiaomi() throws IOException {
        Product product = productDao.getById("8c883a21-fad1-43af-8b15-54b2c1c7a70e");

        List<String> images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/2_xiaomi/xiaomi_1.png").toString(),
                ImageBase64Converter.imageToBase64("src/test/resources/img/2_xiaomi/xiaomi_2.png").toString()
        );
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
        assertEquals(images, product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    public void get_by_id_tissue() throws IOException {
        Product product = productDao.getById("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3");

        List<String> images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/3_tissue/tissue_1.webp").toString(),
                ImageBase64Converter.imageToBase64("src/test/resources/img/3_tissue/tissue_2.webp").toString()
        );
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
        assertEquals(images, product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    public void get_by_id_toothbrush() throws IOException {
        Product product = productDao.getById("9595f97a-bf11-488a-8c15-9edf4db1c450");

        List<String> images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/4_toothbrush/toothbrush_1.webp").toString(),
                ImageBase64Converter.imageToBase64("src/test/resources/img/4_toothbrush/toothbrush_2.webp").toString()
        );
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
        assertEquals(images, product.getImages());
        assertFalse(product.isDeleted());
    }

    @Test
    public void get_by_name() {
        List<Product> products = productDao.getByName("i");

        assertEquals(3, products.size());
        assertTrue(products.stream().anyMatch(product -> "iphone".equals(product.getName())));
        assertTrue(products.stream().anyMatch(product -> "xiaomi".equals(product.getName())));
        assertTrue(products.stream().anyMatch(product -> "tissue".equals(product.getName())));
    }

    @Test
    public void get_by_name_deleted() {
        List<Product> products = productDao.getByName("backpack");

        assertNull(products);
    }

    @Test
    public void get_by_name_not_found() {
        List<Product> products = productDao.getByName("not found");

        assertNull(products);
    }

    @Test
    public void get_all_name_by_id() {
        List<ProductNameDto> productNameDtos = productDao.getAllName();

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
        List<ProductNameDto> productNameDtos = productDao.getAllNameByShopId("1013f7a0-0017-4c21-872f-c014914e6834");

        assertEquals(2, productNameDtos.size());
        assertTrue(productNameDtos.stream().anyMatch(product -> "6874ada1-747f-41a7-bb9a-613d2ec0ce1d".equals(product.getId())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "iphone".equals(product.getName())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "8c883a21-fad1-43af-8b15-54b2c1c7a70e".equals(product.getId())));
        assertTrue(productNameDtos.stream().anyMatch(product -> "xiaomi".equals(product.getName())));
    }

    @Test
    @Transactional
    public void insert() throws IOException, ParseException {
        List<String> images = new ArrayList<>();
        String iphone1 = ImageBase64Converter.imageToBase64("src/test/resources/img/6_samsung/samsung_1.webp");
        String iphone2 = ImageBase64Converter.imageToBase64("src/test/resources/img/6_samsung/samsung_2.webp");
        images.add(iphone1);
        images.add(iphone2);

        ProductDto productDto = new ProductDto(
                "samsung",
                81,
                31200,
                "This is samsung",
                0.92,
                new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2024-08-15").getTime()),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                images,
                false
        );

        String id = productDao.insert(productDto);

        Product product = productDao.getById(id);

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
        assertEquals(images.get(0), product.getImages().get(0));
        assertEquals(images.get(1), product.getImages().get(1));
        assertFalse(product.isDeleted());
    }

    @Test
    @Transactional
    public void update_by_id() throws IOException, ParseException {
        List<String> images = List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/img/2_xiaomi/xiaomi_1.png"),
                ImageBase64Converter.imageToBase64("src/test/resources/img/2_xiaomi/xiaomi_2.png")
        );
        ProductDto productDto = new ProductDto(
                "iphone",
                90,
                36900,
                "This is iphone",
                0.87,
                new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-31").getTime()),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                images,
                false
        );
        productDao.updateById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productDto);

        Product product = productDao.getById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

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
        assertEquals(images.get(0), product.getImages().get(0));
        assertEquals(images.get(1), product.getImages().get(1));
        assertFalse(product.isDeleted());
    }

    @Test
    @Transactional
    public void update_sales_by_id() {
        productDao.updateSalesById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", 29);

        Product product = productDao.getById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
        assertEquals(90 - 29, product.getAmount());
        assertEquals(27 + 29, product.getSales());
    }

    @Test
    @Transactional
    public void delete_by_id() {
        productDao.deleteById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");

        Product product = productDao.getById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
        assertTrue(product.isDeleted());
    }
}