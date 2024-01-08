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
                "12186fe8a7b1dd053d95e8d3379c7271",
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
                "5370c7bc26a91164afc88362b70fce08",
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
        // item1 ATH-M50x
        productDao.insert(new ProductDto(
                "ATH-M50x",
                51,
                5200,
                "搭載大口徑的高磁力Ø45mmCCAW音圈單體，展現出卓越的高解析度播放能力，呈現豐富的音訊細節。耳機使用橢圓形耳罩，提升隔音效果，使長時間使用仍能保持舒適的監聽體驗。\n" +
                        "其獨特的90度反轉監聽機構使單耳監聽成為可能，同時在耳機掛在頸部時，前後90度反轉監聽的機構也為使用者提供了極大的方便性。頭墊和耳墊材質的新採用確保了高度的耐用性，為長時間的使用提供了良好的舒適感。\n" +
                        "耳機還擁有可拆式導線，方便進行保養和維護，為使用者提供更多的便利性。",
                0.93,
                Date.valueOf("2024-05-02"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/audio_technica/audio_technica_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/audio_technica/audio_technica_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/audio_technica/audio_technica_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/audio_technica/audio_technica_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/audio_technica/audio_technica_5.jpg")
                ),
                false));
        // item2 Dyson
        productDao.insert(new ProductDto(
                "Dyson",
                34,
                15309,
                "具有強大的吸力，能夠輕鬆地吸起粉塵，甚至可以嘗試將痱子粉灑在地上進行測試。提供五年的保固期，保固內不包括人為損壞，例如吸水及無清理損壞等情況，並且可享有免費的維修服務。\n" +
                        "在缺料時，提供可替代使用的待用機，確保您在需要時仍能方便使用。免費提供機器維修的收送服務，為您提供更便利的售後服務體驗。\n",
                0.85,
                Date.valueOf("2024-04-08"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dyson/dyson_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dyson/dyson_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dyson/dyson_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dyson/dyson_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dyson/dyson_5.webp")
                ),
                false));
        // item3 G502
        productDao.insert(new ProductDto(
                "G502",
                51,
                5200,
                "設計專為極致舒適性和個性化需求而打造的滑鼠，能夠在長時間的遊戲中完美處理每個細節。從一開始使用的那一刻，\n" +
                        "這款滑鼠就散發出一種獨特的舒適感，搭配橡膠握把、精確的追蹤與適中的重量、多功能按鈕映射、靈敏度調整等功能，提供了全方位的遊戲體驗。\n" +
                        "不僅如此，滑鼠還結合了自訂的照明選項，讓玩家可以根據個人偏好調整外觀，成為一個獨特而強大的遊戲工具，引領玩家度過一場又一場充滿動作和冒險的遊戲季。",
                0.84,
                Date.valueOf("2024-03-02"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g502/g502_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g502/g502_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g502/g502_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g502/g502_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g502/g502_5.png")
                ),
                false));
        // item4 Wacom_Small
        productDao.insert(new ProductDto(
                "Wacom-Intuos-Small",
                20,
                2500,
                "超輕薄且簡潔的設計，讓這款數位板不論在何處都像在家使用般方便舒適。 不只外型迷人，這款數位板依舊耐用且強大如昔。 延伸至邊緣的作業區，讓你擁有更多的桌面空間以及更大的創作空間。",
                0.79,
                Date.valueOf("2024-03-05"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_small/wacom_small_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_small/wacom_small_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_small/wacom_small_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_small/wacom_small_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_small/wacom_small_5.jpg")
                ),
                false));
        //item5 Wacom_One_13
        productDao.insert(new ProductDto(
                "Wacom-One-13",
                65,
                18880,
                "和Wacom One一起探索大膽的新世界。我們的液晶繪圖螢幕提供給你進行數位作畫、繪圖編輯影像、筆記以及與他人聯手創作的自由。\n" +
                        "從我們卓越的液晶繪圖螢幕產品陣容中選擇適合的機型，提供給你直接在高解析度螢幕上使用精確數位筆進行無視差創作的體驗。見證你的藝術創作栩栩如生，並帶有令人屏息的精準度與細節，有如你將想像力無縫轉譯到面前的螢幕上。",
                0.92,
                Date.valueOf("2024-06-17"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_one/wacom_one_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_one/wacom_one_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_one/wacom_one_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_one/wacom_one_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wacom_one/wacom_one_5.png")
                ),
                false));
        //item6 Cintiq Pro 16HD touch
        productDao.insert(new ProductDto(
                "Cintiq Pro 16HD touch",
                20,
                39000,
                "當創意有如潮水襲來，您會想要輕鬆呈現出腦海裡的意象。因此，我們改善了Wacom Cintiq Pro 16，讓您用起來更加自然順手。強化的人體工學設計與直覺式螢幕繪圖體驗，讓您能完全陶醉在自己的創作天地。",
                0.84,
                Date.valueOf("2024-07-19"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/cintiq_pro/cintiq_pro_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/cintiq_pro/cintiq_pro_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/cintiq_pro/cintiq_pro_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/cintiq_pro/cintiq_pro_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/cintiq_pro/cintiq_pro_5.png")
                ),
                false));
        // item7 RTE-100
        productDao.insert(new ProductDto(
                "RTE-100",
                30,
                1280,
                "4.8x3inch黃金比例書寫區、6.3mm纖薄機身攜帶方便、全面板貼合工藝 外形美觀 書寫更流暢全新 免電池數位筆 無須充電",
                0.5,
                Date.valueOf("2024-07-04"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rte_100/rte_100_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rte_100/rte_100_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rte_100/rte_100_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rte_100/rte_100_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rte_100/rte_100_5.jpg")
                ),
                false));
        // item8 RTS-300
        productDao.insert(new ProductDto(
                "RTS-300",
                80,
                2380,
                "此組合包含 RTS-300繪圖板、Painter Essentials 8 繪圖軟體序號卡(終身版)、專用筆套、繪圖板保護套。基礎繪圖板與初學者使用的繪圖軟體，非常適合剛學習電腦繪圖的您\n",
                0.83,
                Date.valueOf("2024-05-12"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rts_300/rts_300_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rts_300/rts_300_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rts_300/rts_300_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rts_300/rts_300_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rts_300/rts_300_5.png")
                ),
                false));
        // item9 H950P
        productDao.insert(new ProductDto(
                "INSPIROY H950P",
                15,
                2280,
                "8192級壓力靈敏度，4倍細膩精彩表現，5080LPI手寫分辨率，提供專業性能。無電池數位筆，防誤觸按鍵設計，盡情揮灑創作靈感，8mm極致輕薄，機身全覆板貼合設計",
                0.84,
                Date.valueOf("2024-02-15"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/h950p/h950p_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/h950p/h950p_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/h950p/h950p_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/h950p/h950p_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/h950p/h950p_5.png")
                ),
                false));
        // item10 KAMVAS PRO 19
        productDao.insert(new ProductDto(
                "KAMVAS PRO 19",
                20,
                20800,
                "4K(3840x2160)解析度和18.4吋螢幕、Huion PenTech 4.0改善整體書寫和繪畫體驗、2代低閃點防眩光蝕刻玻璃、螢幕全貼合和紙質質感、多點觸控可實現滑動、旋轉等操作 更流暢的操作體驗、韌體校色和ΔE<1.5色準值保證了螢幕色彩精確度",
                0.97,
                Date.valueOf("2024-03-27"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_pro_19/kamvas_pro_19_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_pro_19/kamvas_pro_19_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_pro_19/kamvas_pro_19_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_pro_19/kamvas_pro_19_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_pro_19/kamvas_pro_19_5.jpg")
                ),
                false));
        // item11 G930L
        productDao.insert(new ProductDto(
                "G930L",
                64,
                8800,
                "LCD顯示板、輕鬆掌握資訊回饋、藍牙5.0無線連接，讓靈感時刻穩定線上、降噪設計自訂按鍵，賦予安靜有效率的創作、18小時續航，滿足一整天無線創作需求、PenTech3.0筆技術，提供真實自然的紙筆繪畫體驗 \n",
                0.96,
                Date.valueOf("2024-05-12"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g930l/g930l_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g930l/g930l_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g930l/g930l_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g930l/g930l_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/g930l/g930l_5.png")
                ),
                false));
        // item12 KAMVAS Studio 16
        productDao.insert(new ProductDto(
                "KAMVAS Studio 16",
                10,
                49800,
                "一體式設計，同時擁有繪圖螢幕/電腦/平板優秀性能、16GB DDR4超大記憶體，工作更流暢 　●2.5 倍圖像素，為您帶來清晰圖像品質，不同手勢實現相應功能，多點觸控簡化工作流程 　●100% Adobe RGB色域，色彩精準表現",
                0.82,
                Date.valueOf("2024-01-25"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_studio_16/kamvas_studio_16_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_studio_16/kamvas_studio_16_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_studio_16/kamvas_studio_16_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_studio_16/kamvas_studio_16_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/kamvas_studio_16/kamvas_studio_16_5.png")
                ),
                false));
        // item13 HM200
        productDao.insert(new ProductDto(
                "HM200",
                77,
                1680,
                "快充1.5小時，可達130小時電力續航、4096級壓力感應，支援傾斜書寫、採用微軟通用耐磨筆芯，附贈筆芯組",
                0.81,
                Date.valueOf("2024-02-17"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hm_200/hm_200_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hm_200/hm_200_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hm_200/hm_200_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hm_200/hm_200_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hm_200/hm_200_5.jpg")
                ),
                false));
        // item14 Moonstone Ace L
        productDao.insert(new ProductDto(
                "Moonstone Ace L",
                32,
                2990,
                "光滑啞光表面，確保滑鼠滑動快速且精準降噪設計，滑鼠滑動過程格外安靜卓越耐用，由防衝擊、防刮之鋼化玻璃製成",
                0.92,
                Date.valueOf("2024-09-05"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/moonstone_ace_l/moonstone_ace_l_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/moonstone_ace_l/moonstone_ace_l_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/moonstone_ace_l/moonstone_ace_l_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/moonstone_ace_l/moonstone_ace_l_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/moonstone_ace_l/moonstone_ace_l_5.webp")
                ),
                false));
        // item15 Zenbook Pro 16X
        productDao.insert(new ProductDto(
                "Zenbook Pro 16X",
                12,
                79900,
                "16 吋, 4K (3840 x 2400) OLED 16:10 螢幕長寬比, 0.2ms 疾速反應時間, 60Hz 螢幕更新率, 550 尼特峰值亮度, DCI-P3：100% 廣色域, 1,000,000:1 高對比, VESA DisplayHDR™ True Black 500 認證, 10.7 億種顏色, 通過 PANTONE 認證, 鏡面螢幕, 70% 降低有害藍光, 德國萊因 (TÜV Rheinland) 低藍光護眼認證, SGS 認證護眼螢幕, 觸控螢幕, 螢幕佔比 90%",
                0.98,
                Date.valueOf("2024-02-12"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_pro_16x/zenbook_pro_16x_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_pro_16x/zenbook_pro_16x_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_pro_16x/zenbook_pro_16x_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_pro_16x/zenbook_pro_16x_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_pro_16x/zenbook_pro_16x_5.png")
                ),
                false));
        // item16 Zenbook 14X
        productDao.insert(new ProductDto(
                "Zenbook 14X",
                23,
                59900,
                "14.5 吋, 2.8K (2880 x 1800) OLED 16:10 螢幕長寬比, 0.2ms 疾速反應時間, 120Hz 更新率, 400 尼特, 600尼特峰值亮度, DCI-P3：100% 廣色域,\n" +
                        "1,000,000:1 高對比, VESA DisplayHDR™ True Black 600 認證, 10.7 億種顏色, 通過 PANTONE 認證, 鏡面螢幕, 70% 降低有害藍光, 德國萊因 (TÜVRheinland) 低藍光護眼認證, SGS 認證護眼螢幕, 螢幕佔比 89%",
                0.85,
                Date.valueOf("2024-04-08"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_14x/zenbook_14x_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_14x/zenbook_14x_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_14x/zenbook_14x_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_14x/zenbook_14x_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/zenbook_14x/zenbook_14x_5.webp")
                ),
                false));
        // item17 ROG Phone 7
        productDao.insert(new ProductDto(
                "ROG Phone 7 ",
                45,
                33990,
                "6.78吋 20.4:9 (2448x1080) AMOLED螢幕，搭載康寧 Gorilla Glass Victus 具備165Hz螢幕更新率、1毫秒反應時間、720Hz觸控採樣率 色彩誤差值\n" +
                        "Delta-E<1 戶外亮度1000尼特，峰值亮度1500尼特 支援HDR10",
                0.79,
                Date.valueOf("2024-04-08"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_phone_7/rog_phone_7_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_phone_7/rog_phone_7_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_phone_7/rog_phone_7_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_phone_7/rog_phone_7_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_phone_7/rog_phone_7_5.webp")
                ),
                false));
        // item18 WT300
        productDao.insert(new ProductDto(
                "WT300",
                120,
                590,
                "小巧且符合人體工學的設計，確保您在長時間的使用後，也保持舒適\n" +
                        "具有兩種設置（1000 DPI 和 1600 DPI）的光學感測器，可藉由同時按下滾輪和右鍵來自動切換\n" +
                        "根據每天使用 8 小時計算。實際電池續航力會依據使用者的習慣和電腦的情況而有不同。",
                0.91,
                Date.valueOf("2024-03-02"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wt300/wt300_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wt300/wt300_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wt300/wt300_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wt300/wt300_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/wt300/wt300_5.webp")
                ),
                false));
        // item19 ASUS S500TE
        productDao.insert(new ProductDto(
                "ASUS S500TE",
                10,
                20990,
                "Intel® Core™ i5-13400 Processor 2.5GHz (20M Cache, up to 4.6GHz, 10 cores), 2x DDR4 U-DIMM slot, 8GB DDR4 U-DIMM, 記憶體最高64GB",
                0.82,
                Date.valueOf("2024-05-23"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s500te/asus_s500te_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s500te/asus_s500te_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s500te/asus_s500te_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s500te/asus_s500te_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s500te/asus_s500te_5.webp")
                ),
                false));
        // item20 ASUS S501MD
        productDao.insert(new ProductDto(
                "ASUS S501MD",
                12,
                18990,
                "Intel® Core™ i5-12400 Processor 2.5 GHz\n" +
                        "8GB DDR4 U-DIMM (Memory Max. 64GB)",
                0.85,
                Date.valueOf("2024-02-10"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s501md/asus_s501md_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s501md/asus_s501md_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s501md/asus_s501md_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s501md/asus_s501md_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/asus_s501md/asus_s501md_5.webp")
                ),
                false));
        // item21 Hyperion GR701
        productDao.insert(new ProductDto(
                "Hyperion GR701",
                32,
                14090,
                "支援 420 公釐雙散熱器、四個 140 公釐風扇並內建風扇集線器，可提供最大的氣流，具備超大空間以安裝大型顯示卡；並具備 34 公釐深與 46 公釐寬的佈線通道，提供充足的纜線管理空間，鉸鏈式免工具側板內建收納抽屜及整合式顯示卡支撐架，讓您享受絕佳的組裝體驗",
                0.71,
                Date.valueOf("2024-11-23"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hyperion_gr701/hyperion_gr701_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hyperion_gr701/hyperion_gr701_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hyperion_gr701/hyperion_gr701_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hyperion_gr701/hyperion_gr701_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/hyperion_gr701/hyperion_gr701_5.webp")
                ),
                false));
        // item22 ROG Strix Helios
        productDao.insert(new ProductDto(
                "ROG Strix Helios",
                41,
                9090,
                "卓越的設計與美學：配備三片鋼化玻璃面板、髮絲紋鋁合金結構及整合式前面板 Aura Sync RGB 燈效，專為展現玩家精彩的DIY組裝而打造，輕鬆建構俐落內裝：搭載GPU 固定器、兩件式PSU 護罩及半透明後纜線蓋的多功能外蓋，提供最完善的纜線管理系統",
                0.82,
                Date.valueOf("2024-06-09"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_strix_helios/rog_strix_helios_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_strix_helios/rog_strix_helios_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_strix_helios/rog_strix_helios_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_strix_helios/rog_strix_helios_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_strix_helios/rog_strix_helios_5.webp")
                ),
                false));
        // item23 ROG DELTA S
        productDao.insert(new ProductDto(
                "ROG DELTA S",
                3,
                4590,
                "具備 ASUS AI 降噪麥克風，提供極致清晰的遊戲語音、獨家 ASUS Essence 驅動單體、氣密腔體設計以及 Audio Signal Diversion 技術，為你帶來「聲」歷其境的音效體驗",
                0.76,
                Date.valueOf("2024-10-01"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_delta_s/rog_delta_s_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_delta_s/rog_delta_s_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_delta_s/rog_delta_s_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_delta_s/rog_delta_s_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/rog_delta_s/rog_delta_s_5.webp")
                ),
                false));
        // item24  MX Master 3S
        productDao.insert(new ProductDto(
                "MX Master 3S",
                34,
                3690,
                "MX Master 3S 配備我們有史以來最準確的感應器，具有新一代的精確度和靈敏度。8,000 DPI 光學感應器幾乎可在任何地方進行追蹤，即使玻璃也沒問題。",
                0.67,
                Date.valueOf("2024-05-13"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_master_3s/mx_master_3s_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_master_3s/mx_master_3s_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_master_3s/mx_master_3s_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_master_3s/mx_master_3s_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_master_3s/mx_master_3s_5.png")
                ),
                false));
        //item25 Logitech LIFT
        productDao.insert(new ProductDto(
                "Logitech LIFT",
                21,
                2490,
                "享受極致舒適的手握體驗。調整到更自然的使用姿勢。Lift 人體工學垂直滑鼠，舒適設計讓您長保專注。適合中小手型",
                0.82,
                Date.valueOf("2024-12-20"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_lift/logitech_lift_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_lift/logitech_lift_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_lift/logitech_lift_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_lift/logitech_lift_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_lift/logitech_lift_5.jpg")
                ),
                false));
        // item26 SIGNATURE M650
        productDao.insert(new ProductDto(
                "SIGNATURE M650",
                64,
                1090,
                "升級獲得更智慧的捲動、更好的舒適性、以及更高的生產力。Signature M650 具備 SmartWheel 捲動功能，可視您的需要，提供精確或高速捲動。",
                0.62,
                Date.valueOf("2024-07-06"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_m650/signature_m650_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_m650/signature_m650_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_m650/signature_m650_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_m650/signature_m650_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_m650/signature_m650_5.png")
                ),
                false));
        // item27 SIGNATURE K650
        productDao.insert(new ProductDto(
                "SIGNATURE K650",
                25,
                1390,
                "SIGNATURE K650 具有升級的快捷鍵和一體成型手托，適合日常工作需求，並符合長時間辦公的舒適性。獲得長時間辦公工作所需的舒適性。具有防潑濺*、容易清潔設計以及長達 36 個月的電池壽命**，您可以輕鬆移動鍵盤，不受連接線干擾。",
                0.85,
                Date.valueOf("2024-01-25"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_k650/signature_k650_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_k650/signature_k650_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_k650/signature_k650_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_k650/signature_k650_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/signature_k650/signature_k650_5.webp")
                ),
                false));
        // item28 DW ERGO K860
        productDao.insert(new ProductDto(
                "DW ERGO K860",
                55,
                3990,
                "隆重介紹 ERGO K860，一款專為更好的姿勢、更少的勞損以及更多的支撐性而設計的分離式人體工學鍵盤。利用改善打字姿勢的弧形、分離式按鍵框架，讓您可以更自然地進行打字。",
                0.86,
                Date.valueOf("2024-04-04"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dw_ergo_k860/dw_ergo_k860_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dw_ergo_k860/dw_ergo_k860_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dw_ergo_k860/dw_ergo_k860_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dw_ergo_k860/dw_ergo_k860_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/dw_ergo_k860/dw_ergo_k860_5.jpg")
                ),
                false));
        // item29 MX Keys Mini
        productDao.insert(new ProductDto(
                "MX Keys Mini",
                28,
                3490,
                "瞭解 MX Keys Mini - 針對創作者打造的精巧型鍵盤。精巧的尺寸與更智慧的按鍵，提供創作、製作和執行的更強大方式。",
                0.89,
                Date.valueOf("2024-02-13"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_keys_mini/mx_keys_mini_1.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_keys_mini/mx_keys_mini_2.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_keys_mini/mx_keys_mini_3.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_keys_mini/mx_keys_mini_4.webp"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/mx_keys_mini/mx_keys_mini_5.webp")
                ),
                false));
        // item30 Logitech K780
        productDao.insert(new ProductDto(
                "Logitech K780",
                36,
                2490,
                "瞭解專為現今多工世代而設計的 K780 精簡鍵盤。相容於最多三種裝置，您可以在一個簡單的工作流程於手機、平板電腦和電腦上切換打字。",
                0.79,
                Date.valueOf("2024-09-12"),
                "1013f7a0-0017-4c21-872f-c014914e6834",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_k780/logitech_k780_16x_1.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_k780/logitech_k780_16x_2.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_k780/logitech_k780_16x_3.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_k780/logitech_k780_16x_4.png"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop1/logitech_k780/logitech_k780_16x_5.png")
                ),
                false));
    }

    @Test
    public void shop2_create_product() throws IOException {
        // 洗衣精
        productDao.insert(new ProductDto(
                "洗衣精",
                100,
                120,
                "高效去污，呵護衣物，適用於各種衣物材質。",
                0.85,
                Date.valueOf("2021-06-01"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/laundry_detergent/laundry_detergent_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/laundry_detergent/laundry_detergent_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/laundry_detergent/laundry_detergent_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/laundry_detergent/laundry_detergent_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/laundry_detergent/laundry_detergent_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "沐浴乳",
                150,
                80,
                "深層潔淨，保濕滋潤，讓肌膚倍添柔嫩。",
                0.75,
                Date.valueOf("2021-07-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/shower_gel/shower_gel_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/shower_gel/shower_gel_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/shower_gel/shower_gel_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/shower_gel/shower_gel_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/shower_gel/shower_gel_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "餅乾",
                200,
                50,
                "香脆可口，多種口味，適合下午茶點心。",
                0.9,
                Date.valueOf("2021-08-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/cookies/cookies_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/cookies/cookies_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/cookies/cookies_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/cookies/cookies_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/cookies/cookies_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "香皂",
                80,
                25,
                "柔和清新，適合各種膚質，深層潔淨，保濕滋潤。",
                0.88,
                Date.valueOf("2021-07-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/soap/soap_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/soap/soap_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/soap/soap_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/soap/soap_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/soap/soap_5.jpg")
                ),
                false));


        productDao.insert(new ProductDto(
                "大塊巧克力",
                180,
                70,
                "純正巧克力風味，口感濃郁，適合巧克力愛好者。",
                0.85,
                Date.valueOf("2021-09-05"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chocolate/chocolate_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chocolate/chocolate_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chocolate/chocolate_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chocolate/chocolate_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chocolate/chocolate_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "高纖果汁",
                250,
                45,
                "天然果汁，富含維生素，促進消化，清新口感。",
                0.90,
                Date.valueOf("2021-10-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/juice/juice_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/juice/juice_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/juice/juice_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/juice/juice_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/juice/juice_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "養生茶包",
                120,
                30,
                "多種草本植物混合，有助於放鬆身心，提升免疫力。",
                0.88,
                Date.valueOf("2021-11-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/tea/tea_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/tea/tea_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/tea/tea_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/tea/tea_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/tea/tea_5.jpg")
                ),
                false));

// 商品7 - 香脆薯片
        productDao.insert(new ProductDto(
                "香脆薯片",
                90,
                18,
                "脆口可口，多種口味，適合電影夜。",
                0.85,
                Date.valueOf("2021-12-05"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chips/chips_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chips/chips_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chips/chips_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chips/chips_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/chips/chips_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "藍莓酸奶",
                150,
                25,
                "新鮮藍莓，濃郁酸奶，營養豐富，適合早餐。",
                0.90,
                Date.valueOf("2022-01-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/yogurt/yogurt_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/yogurt/yogurt_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/yogurt/yogurt_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/yogurt/yogurt_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/yogurt/yogurt_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "天然果醬",
                120,
                20,
                "新鮮水果製成，無添加糖分，健康美味。",
                0.88,
                Date.valueOf("2022-02-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jam/jam_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jam/jam_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jam/jam_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jam/jam_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jam/jam_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "有機咖啡豆",
                250,
                35,
                "精選有機咖啡豆，風味獨特，適合咖啡愛好者。",
                0.95,
                Date.valueOf("2022-03-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coffee/coffee_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coffee/coffee_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coffee/coffee_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coffee/coffee_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coffee/coffee_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "美容面膜",
                200,
                50,
                "深層清潔，保濕修護，提升肌膚彈性。",
                0.92,
                Date.valueOf("2022-04-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/facial_mask/facial_mask_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/facial_mask/facial_mask_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/facial_mask/facial_mask_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/facial_mask/facial_mask_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/facial_mask/facial_mask_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "天然椰子水",
                80,
                30,
                "100%純天然椰子水，清涼解渴，無添加糖分。",
                0.87,
                Date.valueOf("2022-05-25"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coconut_water/coconut_water_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coconut_water/coconut_water_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coconut_water/coconut_water_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coconut_water/coconut_water_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/coconut_water/coconut_water_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "精選紅茶",
                120,
                40,
                "高山茶葉製成，香氣濃郁，經典英式紅茶。",
                0.88,
                Date.valueOf("2022-06-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/black_tea/black_tea_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/black_tea/black_tea_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/black_tea/black_tea_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/black_tea/black_tea_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/black_tea/black_tea_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "柔軟抱枕",
                150,
                28,
                "舒適柔軟，適合午睡，辦公室休息。",
                0.90,
                Date.valueOf("2022-07-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/pillow/pillow_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/pillow/pillow_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/pillow/pillow_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/pillow/pillow_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/pillow/pillow_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "運動水壺",
                60,
                15,
                "輕巧方便，適合運動，防漏設計。",
                0.85,
                Date.valueOf("2022-08-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sports_bottle/sports_bottle_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sports_bottle/sports_bottle_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sports_bottle/sports_bottle_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sports_bottle/sports_bottle_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sports_bottle/sports_bottle_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "迷你風扇",
                40,
                12,
                "小巧便攜，隨時隨地降溫。",
                0.82,
                Date.valueOf("2022-09-05"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fan/fan_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fan/fan_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fan/fan_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fan/fan_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fan/fan_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "保暖羊毛襪",
                30,
                10,
                "柔軟舒適，保暖效果佳，適合冬天穿著。",
                0.86,
                Date.valueOf("2022-11-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/socks/socks_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/socks/socks_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/socks/socks_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/socks/socks_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/socks/socks_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "木質相框",
                25,
                8,
                "自然木紋，適合裝飾家居，多尺寸可選。",
                0.80,
                Date.valueOf("2022-12-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/photo_frame/photo_frame_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/photo_frame/photo_frame_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/photo_frame/photo_frame_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/photo_frame/photo_frame_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/photo_frame/photo_frame_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "輕便行李箱",
                180,
                60,
                "輕量化設計，360度旋轉輪，方便移動。",
                0.93,
                Date.valueOf("2023-01-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/luggage/luggage_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/luggage/luggage_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/luggage/luggage_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/luggage/luggage_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/luggage/luggage_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "精品陶瓷杯",
                50,
                15,
                "高質陶瓷，簡約設計，適合咖啡或茶飲。",
                0.85,
                Date.valueOf("2023-02-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/ceramic_mug/ceramic_mug_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/ceramic_mug/ceramic_mug_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/ceramic_mug/ceramic_mug_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/ceramic_mug/ceramic_mug_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/ceramic_mug/ceramic_mug_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "舒適沙發",
                800,
                380,
                "人體工學設計，可拆洗套，家居休閒首選。",
                0.90,
                Date.valueOf("2023-04-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sofa/sofa_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sofa/sofa_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sofa/sofa_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sofa/sofa_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sofa/sofa_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "LED檯燈",
                35,
                12,
                "節能環保，多亮度調節，眼睛保護。",
                0.85,
                Date.valueOf("2023-05-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk_lamp/desk_lamp_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk_lamp/desk_lamp_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk_lamp/desk_lamp_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk_lamp/desk_lamp_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk_lamp/desk_lamp_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "青春運動鞋",
                120,
                45,
                "時尚潮流，輕量透氣，適合運動或日常穿搭。",
                0.92,
                Date.valueOf("2023-06-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sneakers/sneakers_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sneakers/sneakers_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sneakers/sneakers_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sneakers/sneakers_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sneakers/sneakers_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "經典墨水筆",
                25,
                8,
                "金屬筆桿，平滑書寫，適合簽名或書寫。",
                0.80,
                Date.valueOf("2023-07-25"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fountain_pen/fountain_pen_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fountain_pen/fountain_pen_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fountain_pen/fountain_pen_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fountain_pen/fountain_pen_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fountain_pen/fountain_pen_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "高級香氛蠟燭",
                40,
                18,
                "天然香氣，舒緩壓力，提升空間氛圍。",
                0.88,
                Date.valueOf("2023-10-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/candle/candle_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/candle/candle_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/candle/candle_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/candle/candle_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/candle/candle_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "藝術畫冊",
                15,
                5,
                "優質紙張，多種風格，適合素描或塗鴉。",
                0.82,
                Date.valueOf("2023-11-25"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sketchbook/sketchbook_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sketchbook/sketchbook_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sketchbook/sketchbook_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sketchbook/sketchbook_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sketchbook/sketchbook_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "水果切割器",
                35,
                15,
                "多功能刀片，方便快捷，輕鬆切割水果。",
                0.86,
                Date.valueOf("2023-02-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fruit_cutter/fruit_cutter_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fruit_cutter/fruit_cutter_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fruit_cutter/fruit_cutter_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fruit_cutter/fruit_cutter_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/fruit_cutter/fruit_cutter_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "陶瓷花瓶",
                60,
                20,
                "精緻手工，多種款式，裝飾鮮花或乾燥花。",
                0.88,
                Date.valueOf("2023-03-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/vase/vase_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/vase/vase_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/vase/vase_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/vase/vase_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/vase/vase_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "人造皮革手提包",
                80,
                25,
                "高品質人造皮，時尚設計，輕便實用。",
                0.90,
                Date.valueOf("2023-04-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/handbag/handbag_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/handbag/handbag_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/handbag/handbag_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/handbag/handbag_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/handbag/handbag_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "折疊遮陽帽",
                25,
                10,
                "輕巧折疊，防曬遮陽，適合戶外活動。",
                0.85,
                Date.valueOf("2023-05-25"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sun_hat/sun_hat_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sun_hat/sun_hat_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sun_hat/sun_hat_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sun_hat/sun_hat_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/sun_hat/sun_hat_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "多功能廚房計時器",
                20,
                8,
                "計時提醒，可設定多個時間，廚房必備。",
                0.82,
                Date.valueOf("2023-06-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/kitchen_timer/kitchen_timer_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/kitchen_timer/kitchen_timer_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/kitchen_timer/kitchen_timer_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/kitchen_timer/kitchen_timer_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/kitchen_timer/kitchen_timer_5.jpg")
                ),
                false));


        productDao.insert(new ProductDto(
                "純棉浴巾",
                40,
                12,
                "柔軟吸水，緊密織法，呵護肌膚。",
                0.86,
                Date.valueOf("2023-07-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/towel/towel_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/towel/towel_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/towel/towel_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/towel/towel_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/towel/towel_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "按摩頸枕",
                50,
                18,
                "提供舒適頸部支撐，內建按摩功能，緩解疲勞。",
                0.88,
                Date.valueOf("2023-08-10"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/neck_pillow/neck_pillow_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/neck_pillow/neck_pillow_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/neck_pillow/neck_pillow_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/neck_pillow/neck_pillow_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/neck_pillow/neck_pillow_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "桌上遊戲",
                30,
                15,
                "家庭娛樂，適合多人玩樂，增進親子互動。",
                0.85,
                Date.valueOf("2023-09-05"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/board_game/board_game_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/board_game/board_game_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/board_game/board_game_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/board_game/board_game_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/board_game/board_game_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "多層收納架",
                70,
                28,
                "節省空間，大容量，整理家居雜物。",
                0.90,
                Date.valueOf("2023-10-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/storage_rack/storage_rack_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/storage_rack/storage_rack_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/storage_rack/storage_rack_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/storage_rack/storage_rack_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/storage_rack/storage_rack_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "輕便折疊椅",
                45,
                22,
                "輕巧便攜，多用途，戶外露營、野餐。",
                0.88,
                Date.valueOf("2023-11-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/folding_chair/folding_chair_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/folding_chair/folding_chair_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/folding_chair/folding_chair_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/folding_chair/folding_chair_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/folding_chair/folding_chair_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "經典牛仔褲",
                180,
                50,
                "高品質牛仔布，經典修身款式，適合各種場合。",
                0.85,
                Date.valueOf("2023-05-25"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jeans/jeans_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jeans/jeans_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jeans/jeans_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jeans/jeans_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/jeans/jeans_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "優雅花裙",
                200,
                60,
                "輕盈優雅，精選花朵圖案，適合夏季活動。",
                0.92,
                Date.valueOf("2023-06-15"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/dress/dress_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/dress/dress_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/dress/dress_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/dress/dress_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/dress/dress_5.jpg")
                ),
                false));

        productDao.insert(new ProductDto(
                "木製書桌",
                250,
                80,
                "天然木材，簡約風格，適合書房或工作區。",
                0.90,
                Date.valueOf("2023-07-20"),
                "f0694ecf-6282-48f9-a401-49eb08067ce0",
                List.of(
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk/desk_1.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk/desk_2.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk/desk_3.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk/desk_4.jpg"),
                        ImageBase64Converter.imageToBase64("src/test/resources/image/product/shop2/desk/desk_5.jpg")
                ),
                false));
    }

}
