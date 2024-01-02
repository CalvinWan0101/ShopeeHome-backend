package com.calvinwan.shopeehomebackend;

import com.calvinwan.shopeehomebackend.dao.ProductDao;
import com.calvinwan.shopeehomebackend.dao.ShopDao;
import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.model.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class TestDataGenerator {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ProductDao productDao;

    @Test
    public void picture() throws IOException {
        userDao.updateAvatarById("30e7e267-c791-424a-a94b-fa5e695d27e7", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user1.jpg"));
        userDao.updateAvatarById("b8007834-0db6-4e8a-aa98-7833035bcaa0", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user2.jpg"));
        userDao.updateAvatarById("111b08ef-3e0f-46f4-b83f-05a2443fafb7", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user3.jpg"));
        userDao.updateAvatarById("f27fa75f-2fae-412a-b4ca-f76a6077d041", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user4.jpg"));
        userDao.updateAvatarById("c0e53cb5-3aa9-4607-b744-f4f233c7f652", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user5.jpg"));

        shopDao.updateAvatarAndBackgroundById("1013f7a0-0017-4c21-872f-c014914e6834", ImageBase64Converter.imageToBase64("src/test/resources/image/shop/shop1_avatar.jpg"), ImageBase64Converter.imageToBase64("src/test/resources/image/shop/shop1_background.jpg"));
        shopDao.updateAvatarAndBackgroundById("f0694ecf-6282-48f9-a401-49eb08067ce0", ImageBase64Converter.imageToBase64("src/test/resources/image/shop/shop2_avatar.jpg"), ImageBase64Converter.imageToBase64("src/test/resources/image/shop/shop2_background.jpg"));

        productDao.updateImagesById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/1_iphone/iphone_1.jpg"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/1_iphone/iphone_2.jpg")
        ));
        productDao.updateImagesById("8c883a21-fad1-43af-8b15-54b2c1c7a70e", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/2_xiaomi/xiaomi_1.png"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/2_xiaomi/xiaomi_2.png")
        ));
        productDao.updateImagesById("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/3_tissue/tissue_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/3_tissue/tissue_2.webp")
        ));
        productDao.updateImagesById("9595f97a-bf11-488a-8c15-9edf4db1c450", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/4_toothbrush/toothbrush_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/4_toothbrush/toothbrush_2.webp")
        ));
        productDao.updateImagesById("4f366b46-50ea-42d9-8216-e677f43b1819", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/5_backpack/backpack_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/5_backpack/backpack_2.webp")
        ));

    }
}
