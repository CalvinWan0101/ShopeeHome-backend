package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ShopDao;
import com.calvinwan.shopeehomebackend.dto.shop.ShopDto;
import com.calvinwan.shopeehomebackend.model.Shop;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tw.teddysoft.ezspec.extension.junit5.EzScenario;
import tw.teddysoft.ezspec.keyword.Feature;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class ShopDaoImplementationSpec {

    @Autowired
    private ShopDao shopDao;
    static Feature feature;

    @BeforeAll
    public static void beforeAll() {
        feature = Feature.New("Shop Management", "As a shop owner or user\n" +
                "I want to manage shop details\n" +
                "So that I can maintain accurate shop information");
        feature.NewRule("Get Shop by ID");
        feature.NewRule("Get Shop by Email");
        feature.NewRule("Insert Shop");
        feature.NewRule("Update Shop by ID");
    }

    @EzScenario
    public void get_by_id() {
        feature.newScenario("Get Shop by ID").withRule("Get Shop by ID")
                .Given("a shop ID", env -> {
                    env.put("id", "1013f7a0-0017-4c21-872f-c014914e6834");
                })
                .When("I retrieve the shop by ID", env -> {
                    env.put("shop", shopDao.getById(env.gets("id")));
                })
                .ThenSuccess(env -> {
                    Shop shop = env.get("shop", Shop.class);
                    String hashedPassword = DigestUtils.md5DigestAsHex("shop1".getBytes());
                    assertNotNull(shop);
                    assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shop.getId());
                    assertEquals("shop1@gmail.com", shop.getEmail());
                    assertEquals(hashedPassword, shop.getPassword());
                    assertEquals("shop1", shop.getName());
                    assertEquals("0909001001", shop.getPhoneNumber());
                    assertEquals("address1", shop.getAddress());
                    assertEquals("This is shop 1", shop.getDescription());
                    assertEquals("shop1_avatar", shop.getAvatar());
                    assertEquals("shop1_background", shop.getBackground());
                    assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", shop.getCreaterId());
                    assertNull(shop.getDeleterId());
                    assertFalse(shop.isDeleted());
                })
                .Execute();
    }

    @EzScenario
    public void get_by_email() {
        feature.newScenario("Get Shop by Email").withRule("Get Shop by Email")
                .Given("a shop email", env -> {
                    env.put("email", "shop1@gmail.com");
                })
                .When("I retrieve the shop by email", env -> {
                    env.put("shop", shopDao.getByEmail(env.gets("email")));
                })
                .ThenSuccess(env -> {
                    Shop shop = env.get("shop", Shop.class);
                    String hashedPassword = DigestUtils.md5DigestAsHex("shop1".getBytes());
                    assertNotNull(shop);
                    assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shop.getId());
                    assertEquals("shop1@gmail.com", shop.getEmail());
                    assertEquals(hashedPassword, shop.getPassword());
                    assertEquals("shop1", shop.getName());
                    assertEquals("0909001001", shop.getPhoneNumber());
                    assertEquals("address1", shop.getAddress());
                    assertEquals("This is shop 1", shop.getDescription());
                    assertEquals("shop1_avatar", shop.getAvatar());
                    assertEquals("shop1_background", shop.getBackground());
                    assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", shop.getCreaterId());
                    assertNull(shop.getDeleterId());
                    assertFalse(shop.isDeleted());
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void insert() {
        feature.newScenario("Insert Shop").withRule("Insert Shop")
                .Given("a new shop DTO", env -> {
                    ShopDto shopDto = new ShopDto(
                            "shop87@gmail.com",
                            "shop87",
                            "shop87",
                            "0909000087",
                            "87 street 87th",
                            "This is shop87.",
                            "shop87_avatar",
                            "shop87_background",
                            "17335ce6-af7c-4c21-af55-9eca9dc5dfb7",
                            null,
                            false
                    );
                    env.put("shopDto", shopDto);
                })
                .When("I insert the shop", env -> {
                    String id = shopDao.insert(env.get("shopDto", ShopDto.class));
                    env.put("id", id);
                })
                .ThenSuccess(env -> {
                    Shop shop = shopDao.getById(env.gets("id"));
                    assertNotNull(shop);
                    assertEquals(env.gets("id"), shop.getId());
                    assertEquals("shop87@gmail.com", shop.getEmail());
                    assertEquals("shop87", shop.getName());
                    assertEquals("shop87", shop.getPassword());
                    assertEquals("0909000087", shop.getPhoneNumber());
                    assertEquals("87 street 87th", shop.getAddress());
                    assertEquals("This is shop87.", shop.getDescription());
                    assertEquals("shop87_avatar", shop.getAvatar());
                    assertEquals("shop87_background", shop.getBackground());
                    assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", shop.getCreaterId());
                    assertNull(shop.getDeleterId());
                    assertFalse(shop.isDeleted());
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void update_by_id() {
        feature.newScenario("Update Shop by ID").withRule("Update Shop by ID")
                .Given("an existing shop ID and updated shop DTO", env -> {
                    ShopDto shopDto = new ShopDto(
                            "shop87@gmail.com",
                            "shop87",
                            "shop87",
                            "0909000087",
                            "87 street 87th",
                            "This is shop87.",
                            "shop87_avatar",
                            "shop87_background",
                            "17335ce6-af7c-4c21-af55-9eca9dc5dfb7",
                            null,
                            false
                    );
                    env.put("shopDto", shopDto);
                    env.put("id", "1013f7a0-0017-4c21-872f-c014914e6834");
                })
                .When("I update the shop by ID", env -> {
                    shopDao.updateById(env.gets("id"), env.get("shopDto", ShopDto.class));
                })
                .ThenSuccess(env -> {
                    Shop shop = shopDao.getById(env.gets("id"));
                    assertNotNull(shop);
                    assertEquals(env.gets("id"), shop.getId());
                    assertEquals("shop87@gmail.com", shop.getEmail());
                    assertEquals("shop87", shop.getName());
                    assertEquals("shop87", shop.getPassword());
                    assertEquals("0909000087", shop.getPhoneNumber());
                    assertEquals("87 street 87th", shop.getAddress());
                    assertEquals("This is shop87.", shop.getDescription());
                    assertEquals("shop87_avatar", shop.getAvatar());
                    assertEquals("shop87_background", shop.getBackground());
                    assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", shop.getCreaterId());
                    assertNull(shop.getDeleterId());
                    assertFalse(shop.isDeleted());
                })
                .Execute();
    }
}
