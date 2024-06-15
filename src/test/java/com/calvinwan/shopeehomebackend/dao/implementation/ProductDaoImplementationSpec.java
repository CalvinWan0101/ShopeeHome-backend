package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ProductDao;
import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.dto.product.ProductNameDto;
import com.calvinwan.shopeehomebackend.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import tw.teddysoft.ezspec.keyword.Feature;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class ProductDaoImplementationSpec {

    @Autowired
    private ProductDao productDao;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("Product Management", "As a user\n" +
                "I want to manage products\n" +
                "So that I can maintain product information");
        feature.NewRule("Get Product by ID");
        feature.NewRule("Get Product by Name");
        feature.NewRule("Get All Product Names");
        feature.NewRule("Insert Product");
        feature.NewRule("Update Product by ID");
        feature.NewRule("Update Product Sales by ID");
        feature.NewRule("Delete Product by ID");
    }

    @Test
    public void get_by_id_iphone() {
        feature.newScenario("Get iPhone by ID").withRule("Get Product by ID")
                .Given("an iPhone product ID", env -> {
                    env.put("id", "6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
                })
                .When("I retrieve the product by ID", env -> {
                    env.put("product", productDao.getById(env.gets("id")));
                })
                .ThenSuccess(env -> {
                    Product product = env.get("product", Product.class);
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
                })
                .Execute();
    }

    @Test
    public void get_by_id_xiaomi() {
        feature.newScenario("Get Xiaomi by ID").withRule("Get Product by ID")
                .Given("a Xiaomi product ID", env -> {
                    env.put("id", "8c883a21-fad1-43af-8b15-54b2c1c7a70e");
                })
                .When("I retrieve the product by ID", env -> {
                    env.put("product", productDao.getById(env.gets("id")));
                })
                .ThenSuccess(env -> {
                    Product product = env.get("product", Product.class);
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
                })
                .Execute();
    }

    @Test
    public void get_by_id_tissue() {
        feature.newScenario("Get Tissue by ID").withRule("Get Product by ID")
                .Given("a Tissue product ID", env -> {
                    env.put("id", "acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3");
                })
                .When("I retrieve the product by ID", env -> {
                    env.put("product", productDao.getById(env.gets("id")));
                })
                .ThenSuccess(env -> {
                    Product product = env.get("product", Product.class);
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
                })
                .Execute();
    }

    @Test
    public void get_by_id_toothbrush() {
        feature.newScenario("Get Toothbrush by ID").withRule("Get Product by ID")
                .Given("a Toothbrush product ID", env -> {
                    env.put("id", "9595f97a-bf11-488a-8c15-9edf4db1c450");
                })
                .When("I retrieve the product by ID", env -> {
                    env.put("product", productDao.getById(env.gets("id")));
                })
                .ThenSuccess(env -> {
                    Product product = env.get("product", Product.class);
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
                })
                .Execute();
    }

    @Test
    public void get_by_name() {
        feature.newScenario("Get Products by Name").withRule("Get Product by Name")
                .Given("a partial product name", env -> {
                    env.put("name", "i");
                })
                .When("I retrieve the product IDs by name", env -> {
                    env.put("ids", productDao.getIdByName(env.gets("name")));
                })
                .ThenSuccess(env -> {
                    List<String> ids = env.get("ids", List.class);
                    assertEquals(3, ids.size());
                    assertTrue(ids.stream().anyMatch(id -> "6874ada1-747f-41a7-bb9a-613d2ec0ce1d".equals(id)));
                    assertTrue(ids.stream().anyMatch(id -> "8c883a21-fad1-43af-8b15-54b2c1c7a70e".equals(id)));
                    assertTrue(ids.stream().anyMatch(id -> "acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3".equals(id)));
                })
                .Execute();
    }

    @Test
    public void get_id_by_name_deleted() {
        feature.newScenario("Get Deleted Product by Name").withRule("Get Product by Name")
                .Given("a deleted product name", env -> {
                    env.put("name", "backpack");
                })
                .When("I retrieve the product IDs by name", env -> {
                    env.put("ids", productDao.getIdByName(env.gets("name")));
                })
                .ThenSuccess(env -> {
                    List<String> ids = env.get("ids", List.class);
                    assertNull(ids);
                })
                .Execute();
    }

    @Test
    public void get_id_by_name_not_found() {
        feature.newScenario("Get Non-existent Product by Name").withRule("Get Product by Name")
                .Given("a non-existent product name", env -> {
                    env.put("name", "not found");
                })
                .When("I retrieve the product IDs by name", env -> {
                    env.put("ids", productDao.getIdByName(env.gets("name")));
                })
                .ThenSuccess(env -> {
                    List<String> ids = env.get("ids", List.class);
                    assertNull(ids);
                })
                .Execute();
    }

    @Test
    public void get_all_name_by_id() {
        feature.newScenario("Get All Product Names by ID").withRule("Get All Product Names")
                .When("I retrieve all product names", env -> {
                    env.put("productNameDtos", productDao.getAllName());
                })
                .ThenSuccess(env -> {
                    List<ProductNameDto> productNameDtos = env.get("productNameDtos", List.class);
                    assertEquals(4, productNameDtos.size());
                    assertTrue(productNameDtos.stream().anyMatch(product -> "6874ada1-747f-41a7-bb9a-613d2ec0ce1d".equals(product.getId())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "iphone".equals(product.getName())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "8c883a21-fad1-43af-8b15-54b2c1c7a70e".equals(product.getId())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "xiaomi".equals(product.getName())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3".equals(product.getId())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "tissue".equals(product.getName())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "9595f97a-bf11-488a-8c15-9edf4db1c450".equals(product.getId())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "toothbrush".equals(product.getName())));
                })
                .Execute();
    }

    @Test
    public void get_all_name_by_shop_id() {
        feature.newScenario("Get All Product Names by Shop ID").withRule("Get All Product Names")
                .Given("a shop ID", env -> {
                    env.put("shopId", "1013f7a0-0017-4c21-872f-c014914e6834");
                })
                .When("I retrieve all product names by shop ID", env -> {
                    env.put("productNameDtos", productDao.getAllNameByShopId(env.gets("shopId")));
                })
                .ThenSuccess(env -> {
                    List<ProductNameDto> productNameDtos = env.get("productNameDtos", List.class);
                    assertEquals(2, productNameDtos.size());
                    assertTrue(productNameDtos.stream().anyMatch(product -> "6874ada1-747f-41a7-bb9a-613d2ec0ce1d".equals(product.getId())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "iphone".equals(product.getName())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "8c883a21-fad1-43af-8b15-54b2c1c7a70e".equals(product.getId())));
                    assertTrue(productNameDtos.stream().anyMatch(product -> "xiaomi".equals(product.getName())));
                })
                .Execute();
    }

    @Test
    @Transactional
    public void insert() {
        feature.newScenario("Insert Product").withRule("Insert Product")
                .Given("a new product", env -> {
                    ProductDto productDto = null;
                    try {
                        productDto = new ProductDto(
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
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    env.put("productDto", productDto);
                })
                .When("I insert the product", env -> {
                    String id = productDao.insert(env.get("productDto", ProductDto.class));
                    env.put("id", id);
                })
                .ThenSuccess(env -> {
                    Product product = productDao.getById(env.gets("id"));
                    assertNotNull(product);
                    assertEquals(env.gets("id"), product.getId());
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
                })
                .Execute();
    }

    @Test
    @Transactional
    public void update_by_id() {
        feature.newScenario("Update Product by ID").withRule("Update Product by ID")
                .Given("an existing product ID and new product details", env -> {
                    ProductDto productDto = null;
                    try {
                        productDto = new ProductDto(
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
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    env.put("productDto", productDto);
                    env.put("id", "6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
                })
                .When("I update the product by ID", env -> {
                    productDao.updateById(env.gets("id"), env.get("productDto", ProductDto.class));
                })
                .ThenSuccess(env -> {
                    Product product = productDao.getById(env.gets("id"));
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
                })
                .Execute();
    }

    @Test
    @Transactional
    public void update_sales_by_id() {
        feature.newScenario("Update Product Sales by ID").withRule("Update Product Sales by ID")
                .Given("an existing product ID and new sales amount", env -> {
                    env.put("id", "6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
                    env.put("sales", 29);
                })
                .When("I update the product sales by ID", env -> {
                    productDao.updateSalesById(env.gets("id"), env.get("sales", Integer.class));
                })
                .ThenSuccess(env -> {
                    Product product = productDao.getById(env.gets("id"));
                    assertNotNull(product);
                    assertEquals(90 - 29, product.getAmount());
                    assertEquals(27 + 29, product.getSales());
                })
                .Execute();
    }

    @Test
    @Transactional
    public void delete_by_id() {
        feature.newScenario("Delete Product by ID").withRule("Delete Product by ID")
                .Given("an existing product ID", env -> {
                    env.put("id", "6874ada1-747f-41a7-bb9a-613d2ec0ce1d");
                })
                .When("I delete the product by ID", env -> {
                    productDao.deleteById(env.gets("id"));
                })
                .ThenSuccess(env -> {
                    Product product = productDao.getById(env.gets("id"));
                    assertNotNull(product);
                    assertTrue(product.isDeleted());
                })
                .Execute();
    }
}
