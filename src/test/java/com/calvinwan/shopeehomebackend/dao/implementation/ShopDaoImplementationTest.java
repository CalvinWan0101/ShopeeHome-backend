package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.ShopDao;
import com.calvinwan.shopeehomebackend.dto.shop.ShopDto;
import com.calvinwan.shopeehomebackend.model.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class ShopDaoImplementationTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void get_by_id() {
        Shop shop = shopDao.getById("1013f7a0-0017-4c21-872f-c014914e6834");

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
        assertEquals(null, shop.getDeleterId());
        assertFalse(shop.isDeleted());
    }

    @Test
    public void get_by_email() {
        Shop shop = shopDao.getByEmail("shop1@gmail.com");

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
        assertEquals(null, shop.getDeleterId());
        assertFalse(shop.isDeleted());
    }

    @Test
    @Transactional
    public void insert() {
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


        String id = shopDao.insert(shopDto);
        Shop shop = shopDao.getById(id);
        assertNotNull(shop);
        assertEquals(id, shop.getId());
        assertEquals("shop87@gmail.com", shop.getEmail());
        assertEquals("shop87", shop.getName());
        assertEquals("shop87", shop.getPassword());
        assertEquals("0909000087", shop.getPhoneNumber());
        assertEquals("87 street 87th", shop.getAddress());
        assertEquals("This is shop87.", shop.getDescription());
        assertEquals("shop87_avatar", shop.getAvatar());
        assertEquals("shop87_background", shop.getBackground());
        assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", shop.getCreaterId());
        assertEquals(null, shop.getDeleterId());
        assertEquals(false, shop.isDeleted());
    }

    @Test
    @Transactional
    public void update_by_id() {
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
                false);

        shopDao.updateById("1013f7a0-0017-4c21-872f-c014914e6834", shopDto);
        Shop shop = shopDao.getById("1013f7a0-0017-4c21-872f-c014914e6834");

        assertNotNull(shop);
        assertEquals("1013f7a0-0017-4c21-872f-c014914e6834", shop.getId());
        assertEquals("shop87@gmail.com", shop.getEmail());
        assertEquals("shop87", shop.getName());
        assertEquals("shop87", shop.getPassword());
        assertEquals("0909000087", shop.getPhoneNumber());
        assertEquals("87 street 87th", shop.getAddress());
        assertEquals("This is shop87.", shop.getDescription());
        assertEquals("shop87_avatar", shop.getAvatar());
        assertEquals("shop87_background", shop.getBackground());
        assertEquals("17335ce6-af7c-4c21-af55-9eca9dc5dfb7", shop.getCreaterId());
        assertEquals(null, shop.getDeleterId());
        assertEquals(false, shop.isDeleted());
    }
}