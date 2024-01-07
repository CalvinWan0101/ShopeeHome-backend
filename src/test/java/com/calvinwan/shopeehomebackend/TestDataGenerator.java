package com.calvinwan.shopeehomebackend;

import com.calvinwan.shopeehomebackend.dao.ProductDao;
import com.calvinwan.shopeehomebackend.dao.ShopDao;
import com.calvinwan.shopeehomebackend.dao.UserDao;
import com.calvinwan.shopeehomebackend.dto.product.ProductDto;
import com.calvinwan.shopeehomebackend.dto.shop.ShopDto;
import com.calvinwan.shopeehomebackend.dto.user.UserDto;
import com.calvinwan.shopeehomebackend.model.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@SpringBootTest
@Sql(scripts = "/database/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class TestDataGenerator {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ProductDao productDao;

    @Test
    public void user_information() throws IOException {
        UserDto userDto = new UserDto(
                "user1@gmail.com",
                "24c9e15e52afc47c225b757e7bee1f9d",
                "海綿寶寶",
                "0909001001",
                ImageBase64Converter.imageToBase64("src/test/resources/image/user/user1.jpg"),
                List.of("比基尼環礁比奇堡貝殼街124號鳳梨屋", "比基尼環礁比奇堡貝殼街120號石頭屋", "比基尼環礁比奇堡貝殼街122號復活島人像屋"),
                false
        );

        userDao.updateById("30e7e267-c791-424a-a94b-fa5e695d27e7", userDto);
    }

    @Test
    public void user_avatar() throws IOException {
        userDao.updateAvatarById("30e7e267-c791-424a-a94b-fa5e695d27e7", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user1.jpg"));
        userDao.updateAvatarById("b8007834-0db6-4e8a-aa98-7833035bcaa0", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user2.jpg"));
        userDao.updateAvatarById("111b08ef-3e0f-46f4-b83f-05a2443fafb7", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user3.jpg"));
        userDao.updateAvatarById("f27fa75f-2fae-412a-b4ca-f76a6077d041", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user4.jpg"));
        userDao.updateAvatarById("c0e53cb5-3aa9-4607-b744-f4f233c7f652", ImageBase64Converter.imageToBase64("src/test/resources/image/user/user5.jpg"));
    }

    @Test
    public void shop_information() throws IOException {
        ShopDto shopDto = new ShopDto(
                "shop1@gmail.com",
                "shop1",
                "華光商場",
                "0909001001",
                "臺北市中正區市民大道三段8號",
                """
                        華光商場的3C專賣店歡迎您！我們致力於提供最優質的電子產品和服務，滿足您對先進科技的需求。無論您是尋找最新的智能手機、平板電腦、筆記型電腦，還是其他3C產品，我們都擁有多樣的選擇，滿足不同客戶的需求。
                        在華光商場的3C專賣店，我們注重品質和性能，只提供來自知名品牌的優質商品。無論您是尋找高效處理器、清晰顯示屏還是卓越攝影功能，我們都能滿足您的期望。同時，我們的專業團隊隨時為您提供專業建議和售後服務，確保您的購物體驗無懈可擊。
                        除了3C產品的銷售，我們還提供各種配件和周邊產品，以滿足您對科技生活的多元需求。無論是保護套、耳機、還是其他創新的科技配件，我們都有不同款式和價位，助您打造個性化的科技生活。
                        歡迎蒞臨華光商場的3C專賣店，讓我們一同探索數碼世界，提升您的科技生活品質！""",
                ImageBase64Converter.imageToBase64("src/test/resources/image/shop/shop1_avatar.jpg"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/shop/shop1_background.jpg"),
                "17335ce6-af7c-4c21-af55-9eca9dc5dfb7",
                null,
                false);

        shopDao.updateById("1013f7a0-0017-4c21-872f-c014914e6834", shopDto);

        shopDto = new ShopDto(
                "shop2@gmail.com",
                "shop2",
                "福樂家",
                "0909002002",
                "台北市大同區酒泉街105號",
                """
                        福樂家，您日常生活的最佳夥伴！我們致力於提供高品質的日用品，滿足您生活的各個面向。在福樂家，我們深知您對於家居生活的需求，因此提供豐富多樣的商品，包括清潔用品、生活小工具、廚房用具等，讓您的生活更加方便、舒適。
                        我們的清潔用品系列包羅萬象，從環保清潔劑到多功能掃把，都能滿足您對於居家環境的要求。福樂家的生活小工具更是精心挑選，簡單實用的設計，讓您輕鬆處理日常琐事。而我們的廚房用具則致力於為您打造一個美味、健康的烹飪空間，讓每一餐都成為一場享受。
                        福樂家不僅關注品質，更注重用心服務。我們的團隊樂於助您尋找最適合的商品，並提供專業建議。無論是打理家務，還是打造溫馨家居，福樂家都是您可靠的伙伴。
                        歡迎蒞臨福樂家，讓我們共同締造一個更美好、更舒適的家居生活！""",
                ImageBase64Converter.imageToBase64("src/test/resources/image/shop/shop2_avatar.jpg"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/shop/shop2_background.jpg"),
                "17335ce6-af7c-4c21-af55-9eca9dc5dfb7",
                null,
                false);

        shopDao.updateById("f0694ecf-6282-48f9-a401-49eb08067ce0", shopDto);
    }

    @Test
    public void shop1_product_image() throws IOException {
        productDao.updateImagesById("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/iphone/iphone_1.jpg"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/iphone/iphone_2.jpg")
        ));
        productDao.updateImagesById("8c883a21-fad1-43af-8b15-54b2c1c7a70e", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/xiaomi/xiaomi_1.png"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/xiaomi/xiaomi_2.png")
        ));

    }

    @Test
    public void shop2_product_image() throws IOException {
        productDao.updateImagesById("4f366b46-50ea-42d9-8216-e677f43b1819", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/backpack/backpack_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/backpack/backpack_2.webp")
        ));
        productDao.updateImagesById("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/tissue/tissue_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/tissue/tissue_2.webp")
        ));
        productDao.updateImagesById("9595f97a-bf11-488a-8c15-9edf4db1c450", List.of(
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/toothbrush/toothbrush_1.webp"),
                ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/toothbrush/toothbrush_2.webp")
        ));
    }

    @Test
    public void shop1_create_product() throws IOException {
        // samsung
        productDao.insert(new ProductDto(
                "samsung s21",
                89,
                26900,
                "三星（Samsung）是一家全球知名的電子產品製造商，其手機系列廣受用戶歡迎。三星手機以卓越的技術創新、高品質的製造工藝和多樣化的功能而聞名。透過不斷的研發和設計，三星致力於提供先進的智能手機體驗。\n" +
                        "三星手機擁有令人印象深刻的顯示技術，其 Super AMOLED 螢幕呈現出生動、清晰且色彩豐富的影像，為用戶帶來極致的視覺享受。其攝影技術也一直處於行業領先地位，搭載高像素鏡頭、先進的影像處理技術，用戶能夠捕捉清晰、細緻的照片和錄製高質量的影片。\n" +
                        "在性能方面，三星手機通常配備強大的處理器和大容量的內存，以確保流暢運行各種應用和多任務處理。此外，其電池續航能力也得到了優化，使用戶能夠長時間使用手機而無需擔心電量問題。",
                0.91,
                Date.valueOf("2021-06-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/samsung/samsung_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/samsung/samsung_2.webp")
                ),
                false));
    }
}
