package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.OrderDao;
import com.calvinwan.shopeehomebackend.dao.SeasoningCouponDao;
import com.calvinwan.shopeehomebackend.dao.ShippingCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.seasoning.SeasoningCouponUserDto;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponUserDto;
import com.calvinwan.shopeehomebackend.dto.order.OrderCreateDto;
import com.calvinwan.shopeehomebackend.dto.order.OrderProductDto;
import com.calvinwan.shopeehomebackend.model.order.Order;
import com.calvinwan.shopeehomebackend.model.order.OrderProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class OrderDaoImplementationTest {

    @Autowired
    OrderDao orderDao;

    @Autowired
    SeasoningCouponDao seasoningCouponDao;

    @Autowired
    ShippingCouponDao shippingCouponDao;

    @Test
    public void get_order_by_order_id() {
        Order order = orderDao.getOrderByOrderId("15aeafa1-2561-4098-ad07-e5d599c2ae3b");

        assertEquals("15aeafa1-2561-4098-ad07-e5d599c2ae3b", order.getId());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order.getUserId());
        assertNull(order.getCouponId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order.getAddress());
        assertEquals(100264, order.getTotalPrice());
        assertEquals(60, order.getShippingCost());
        assertEquals(0, order.getRate());
        assertEquals(0, order.getShippingLimit());
        assertEquals("2024-01-01", order.getStartTime().toString());
        assertNull(order.getDeliverTime());

        assertEquals(2, order.getProducts().size());
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", order.getProducts().get(0).getId());
        assertEquals("iphone", order.getProducts().get(0).getName());
        assertEquals(32103, order.getProducts().get(0).getPrice());
        assertEquals(2, order.getProducts().get(0).getQuantity());

        assertEquals("iphone_image_1", order.getProducts().get(0).getImage());
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", order.getProducts().get(1).getId());
        assertEquals("xiaomi", order.getProducts().get(1).getName());
        assertEquals(17999, order.getProducts().get(1).getPrice());
        assertEquals(2, order.getProducts().get(1).getQuantity());
        assertEquals("xiaomi_image_1", order.getProducts().get(1).getImage());
    }

    @Test
    public void get_order_by_order_id_not_found() {
        Order order = orderDao.getOrderByOrderId("invalid-id");
        assertNull(order);
    }

    @Test
    public void get_order_by_user_id() {
        List<Order> orders = orderDao.getOrderByUserId("30e7e267-c791-424a-a94b-fa5e695d27e7");
        orders = orders.stream().sorted(Comparator.comparing(Order::getId)).toList();

        assertEquals(3, orders.size());

        Order order1 = orders.get(0);
        assertEquals("15aeafa1-2561-4098-ad07-e5d599c2ae3b", order1.getId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order1.getAddress());
        assertEquals("2024-01-01", order1.getStartTime().toString());
        assertNull(order1.getDeliverTime());
        assertEquals(100264, order1.getTotalPrice());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order1.getUserId());
        assertEquals(60, order1.getShippingCost());

        Order order2 = orders.get(1);
        assertEquals("629af7fc-ffd9-4201-b18a-cd9096314f05", order2.getId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order2.getAddress());
        assertEquals("2024-01-03", order2.getStartTime().toString());
        assertNull(order2.getDeliverTime());
        assertEquals(100204, order2.getTotalPrice());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order2.getUserId());
        assertEquals(0, order2.getShippingCost());

        Order order3 = orders.get(2);
        assertEquals("f9d01135-323e-479d-837a-889ab8916f49", order3.getId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order3.getAddress());
        assertNull(order3.getStartTime());
        assertNull(order3.getDeliverTime());
        assertEquals(10080, order3.getTotalPrice());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order3.getUserId());
        assertEquals(60, order3.getShippingCost());

        List<OrderProduct> productsOrder1 = order1.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
        assertEquals(2, productsOrder1.size());
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productsOrder1.get(0).getId());
        assertEquals("iphone", productsOrder1.get(0).getName());
        assertEquals(32103, productsOrder1.get(0).getPrice());
        assertEquals(2, productsOrder1.get(0).getQuantity());
        assertEquals("iphone_image_1", productsOrder1.get(0).getImage());
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", productsOrder1.get(1).getId());
        assertEquals("xiaomi", productsOrder1.get(1).getName());
        assertEquals(17999, productsOrder1.get(1).getPrice());
        assertEquals(2, productsOrder1.get(1).getQuantity());
        assertEquals("xiaomi_image_1", productsOrder1.get(1).getImage());

        List<OrderProduct> productsOrder2 = order2.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
        assertEquals(2, productsOrder2.size());
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productsOrder2.get(0).getId());
        assertEquals("iphone", productsOrder2.get(0).getName());
        assertEquals(32103, productsOrder2.get(0).getPrice());
        assertEquals(2, productsOrder2.get(0).getQuantity());
        assertEquals("iphone_image_1", productsOrder2.get(0).getImage());
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", productsOrder2.get(1).getId());
        assertEquals("xiaomi", productsOrder2.get(1).getName());
        assertEquals(17999, productsOrder2.get(1).getPrice());
        assertEquals(2, productsOrder2.get(1).getQuantity());
        assertEquals("xiaomi_image_1", productsOrder2.get(1).getImage());

        List<OrderProduct> productsOrder3 = order3.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
        assertEquals(2, productsOrder3.size());
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productsOrder3.get(0).getId());
        assertEquals("iphone", productsOrder3.get(0).getName());
        assertEquals(32103, productsOrder3.get(0).getPrice());
        assertEquals(2, productsOrder3.get(0).getQuantity());
        assertEquals("iphone_image_1", productsOrder3.get(0).getImage());
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", productsOrder3.get(1).getId());
        assertEquals("xiaomi", productsOrder3.get(1).getName());
        assertEquals(17999, productsOrder3.get(1).getPrice());
        assertEquals(2, productsOrder3.get(1).getQuantity());
        assertEquals("xiaomi_image_1", productsOrder3.get(1).getImage());
    }

    @Test
    public void get_order_by_user_id_not_found() {
        assertNull(orderDao.getOrderByUserId("invalid-id"));
    }

    @Test
    public void get_order_by_shop_id() {
        List<Order> orders = orderDao.getOrderByShopId("1013f7a0-0017-4c21-872f-c014914e6834");
        orders = orders.stream().sorted(Comparator.comparing(Order::getId)).toList();

        assertEquals(3, orders.size());

        Order order1 = orders.get(0);
        assertEquals("15aeafa1-2561-4098-ad07-e5d599c2ae3b", order1.getId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order1.getAddress());
        assertEquals("2024-01-01", order1.getStartTime().toString());
        assertNull(order1.getDeliverTime());
        assertEquals(100264, order1.getTotalPrice());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order1.getUserId());
        assertEquals(60, order1.getShippingCost());

        Order order2 = orders.get(1);
        assertEquals("629af7fc-ffd9-4201-b18a-cd9096314f05", order2.getId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order2.getAddress());
        assertEquals("2024-01-03", order2.getStartTime().toString());
        assertNull(order2.getDeliverTime());
        assertEquals(100204, order2.getTotalPrice());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order2.getUserId());
        assertEquals(0, order2.getShippingCost());

        Order order3 = orders.get(2);
        assertEquals("f9d01135-323e-479d-837a-889ab8916f49", order3.getId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order3.getAddress());
        assertNull(order3.getStartTime());
        assertNull(order3.getDeliverTime());
        assertEquals(10080, order3.getTotalPrice());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order3.getUserId());
        assertEquals(60, order3.getShippingCost());

        List<OrderProduct> productsOrder1 = order1.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
        assertEquals(2, productsOrder1.size());
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productsOrder1.get(0).getId());
        assertEquals("iphone", productsOrder1.get(0).getName());
        assertEquals(32103, productsOrder1.get(0).getPrice());
        assertEquals(2, productsOrder1.get(0).getQuantity());
        assertEquals("iphone_image_1", productsOrder1.get(0).getImage());
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", productsOrder1.get(1).getId());
        assertEquals("xiaomi", productsOrder1.get(1).getName());
        assertEquals(17999, productsOrder1.get(1).getPrice());
        assertEquals(2, productsOrder1.get(1).getQuantity());
        assertEquals("xiaomi_image_1", productsOrder1.get(1).getImage());

        List<OrderProduct> productsOrder2 = order2.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
        assertEquals(2, productsOrder2.size());
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productsOrder2.get(0).getId());
        assertEquals("iphone", productsOrder2.get(0).getName());
        assertEquals(32103, productsOrder2.get(0).getPrice());
        assertEquals(2, productsOrder2.get(0).getQuantity());
        assertEquals("iphone_image_1", productsOrder2.get(0).getImage());
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", productsOrder2.get(1).getId());
        assertEquals("xiaomi", productsOrder2.get(1).getName());
        assertEquals(17999, productsOrder2.get(1).getPrice());
        assertEquals(2, productsOrder2.get(1).getQuantity());
        assertEquals("xiaomi_image_1", productsOrder2.get(1).getImage());

        List<OrderProduct> productsOrder3 = order3.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
        assertEquals(2, productsOrder3.size());
        assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", productsOrder3.get(0).getId());
        assertEquals("iphone", productsOrder3.get(0).getName());
        assertEquals(32103, productsOrder3.get(0).getPrice());
        assertEquals(2, productsOrder3.get(0).getQuantity());
        assertEquals("iphone_image_1", productsOrder3.get(0).getImage());
        assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", productsOrder3.get(1).getId());
        assertEquals("xiaomi", productsOrder3.get(1).getName());
        assertEquals(17999, productsOrder3.get(1).getPrice());
        assertEquals(2, productsOrder3.get(1).getQuantity());
        assertEquals("xiaomi_image_1", productsOrder3.get(1).getImage());
    }

    @Test
    public void get_order_by_shop_id_not_found() {
        assertNull(orderDao.getOrderByShopId("invalid-id"));
    }

    @Test
    @Transactional
    public void create_order_without_coupon() {
        List<OrderProductDto> products = List.of(
                new OrderProductDto("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", 2),
                new OrderProductDto("9595f97a-bf11-488a-8c15-9edf4db1c450", 2)
        );

        OrderCreateDto orderCreateDto = new OrderCreateDto(
                "30e7e267-c791-424a-a94b-fa5e695d27e7",
                "比基尼環礁比奇堡貝殼街124號鳳梨屋",
                null,
                products
        );

        String orderId = orderDao.createOrder(orderCreateDto);

        Order order = orderDao.getOrderByOrderId(orderId);

        assertEquals(orderId, order.getId());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order.getUserId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order.getAddress());
        assertNull(order.getCouponId());
        assertEquals(360, order.getTotalPrice());
        assertEquals(60, order.getShippingCost());
        assertEquals(0, order.getRate());
        assertEquals(0, order.getShippingLimit());
        assertNull(order.getStartTime());
        assertNull(order.getDeliverTime());

        assertEquals(2, order.getProducts().size());
        assertEquals("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", order.getProducts().get(0).getId());
        assertEquals("tissue", order.getProducts().get(0).getName());
        assertEquals(100, order.getProducts().get(0).getPrice());
        assertEquals(2, order.getProducts().get(0).getQuantity());
        assertEquals("tissue_image_1", order.getProducts().get(0).getImage());

        assertEquals("9595f97a-bf11-488a-8c15-9edf4db1c450", order.getProducts().get(1).getId());
        assertEquals("toothbrush", order.getProducts().get(1).getName());
        assertEquals(50, order.getProducts().get(1).getPrice());
        assertEquals(2, order.getProducts().get(1).getQuantity());
        assertEquals("toothbrush_image_1", order.getProducts().get(1).getImage());
    }

//    @Test
//    @Transactional
//    public void create_order_with_seasoning_coupon() {
//        // TODO: Bug Fixing
//        List<OrderProductDto> products = List.of(
//                new OrderProductDto("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", 2),
//                new OrderProductDto("9595f97a-bf11-488a-8c15-9edf4db1c450", 2)
//        );
//
//        OrderCreateDto orderCreateDto = new OrderCreateDto(
//                "30e7e267-c791-424a-a94b-fa5e695d27e7",
//                "比基尼環礁比奇堡貝殼街124號鳳梨屋",
//                "22960254-75b3-47c2-bbdc-e596a9ad3a4a",
//                products
//        );
//
//        String orderId = orderDao.createOrder(orderCreateDto);
//
//        Order order = orderDao.getOrderByOrderId(orderId);
//
//        assertEquals(orderId, order.getId());
//        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order.getUserId());
//        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order.getAddress());
//        assertEquals("22960254-75b3-47c2-bbdc-e596a9ad3a4a", order.getCouponId());
//        assertEquals(120, order.getTotalPrice());
//        assertEquals(60, order.getShippingCost());
//        assertEquals(0.2, order.getRate());
//        assertEquals(0, order.getShippingLimit());
//        assertNull(order.getStartTime());
//        assertNull(order.getDeliverTime());
//
//        assertEquals(2, order.getProducts().size());
//        assertEquals("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", order.getProducts().get(0).getId());
//        assertEquals("tissue", order.getProducts().get(0).getName());
//        assertEquals(100, order.getProducts().get(0).getPrice());
//        assertEquals(2, order.getProducts().get(0).getQuantity());
//        assertEquals("tissue_image_1", order.getProducts().get(0).getImage());
//
//        assertEquals("9595f97a-bf11-488a-8c15-9edf4db1c450", order.getProducts().get(1).getId());
//        assertEquals("toothbrush", order.getProducts().get(1).getName());
//        assertEquals(50, order.getProducts().get(1).getPrice());
//        assertEquals(2, order.getProducts().get(1).getQuantity());
//        assertEquals("toothbrush_image_1", order.getProducts().get(1).getImage());
//
//        List<SeasoningCouponUserDto> seasoningCouponUserDtos = seasoningCouponDao.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", "f0694ecf-6282-48f9-a401-49eb08067ce0");
//        assertEquals(2, seasoningCouponUserDtos.size());
//        assertEquals("66fbc0ec-8357-43b6-89dc-dc8082c5dcca", seasoningCouponUserDtos.get(0).getId());
//        assertFalse(seasoningCouponUserDtos.get(0).isDeleted());
//        assertFalse(seasoningCouponUserDtos.get(0).isClaimed());
//        assertFalse(seasoningCouponUserDtos.get(0).isUsed());
//        assertEquals("22960254-75b3-47c2-bbdc-e596a9ad3a4a", seasoningCouponUserDtos.get(1).getId());
//        assertFalse(seasoningCouponUserDtos.get(1).isDeleted());
//        assertTrue(seasoningCouponUserDtos.get(1).isClaimed());
//        assertTrue(seasoningCouponUserDtos.get(1).isUsed());
//    }

    @Test
    @Transactional
    public void create_order_with_shipping_coupon() {

        List<OrderProductDto> products = List.of(
                new OrderProductDto("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", 200),
                new OrderProductDto("9595f97a-bf11-488a-8c15-9edf4db1c450", 200)
        );

        OrderCreateDto orderCreateDto = new OrderCreateDto(
                "30e7e267-c791-424a-a94b-fa5e695d27e7",
                "比基尼環礁比奇堡貝殼街124號鳳梨屋",
                "bfc0f61b-5e61-4604-b015-cfecbb0810cf",
                products
        );

        String orderId = orderDao.createOrder(orderCreateDto);

        Order order = orderDao.getOrderByOrderId(orderId);

        assertEquals(orderId, order.getId());
        assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order.getUserId());
        assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order.getAddress());
        assertEquals("bfc0f61b-5e61-4604-b015-cfecbb0810cf", order.getCouponId());
        assertEquals(30000, order.getTotalPrice());
        assertEquals(0, order.getShippingCost());
        assertEquals(0, order.getRate());
        assertEquals(2000, order.getShippingLimit());
        assertNull(order.getStartTime());
        assertNull(order.getDeliverTime());

        assertEquals(2, order.getProducts().size());
        assertEquals("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", order.getProducts().get(0).getId());
        assertEquals("tissue", order.getProducts().get(0).getName());
        assertEquals(100, order.getProducts().get(0).getPrice());
        assertEquals(200, order.getProducts().get(0).getQuantity());
        assertEquals("tissue_image_1", order.getProducts().get(0).getImage());

        assertEquals("9595f97a-bf11-488a-8c15-9edf4db1c450", order.getProducts().get(1).getId());
        assertEquals("toothbrush", order.getProducts().get(1).getName());
        assertEquals(50, order.getProducts().get(1).getPrice());
        assertEquals(200, order.getProducts().get(1).getQuantity());
        assertEquals("toothbrush_image_1", order.getProducts().get(1).getImage());

        List<ShippingCouponUserDto> shippingCouponUserDto = shippingCouponDao.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", "f0694ecf-6282-48f9-a401-49eb08067ce0");
        assertEquals(2, shippingCouponUserDto.size());
        assertEquals("9a6e090a-e561-44a3-933e-f9da089ba6e9", shippingCouponUserDto.get(0).getId());
        assertFalse(shippingCouponUserDto.get(0).isDeleted());
        assertFalse(shippingCouponUserDto.get(0).isClaimed());
        assertFalse(shippingCouponUserDto.get(0).isUsed());
        assertEquals("bfc0f61b-5e61-4604-b015-cfecbb0810cf", shippingCouponUserDto.get(1).getId());
        assertFalse(shippingCouponUserDto.get(1).isDeleted());
        assertTrue(shippingCouponUserDto.get(1).isClaimed());
        assertTrue(shippingCouponUserDto.get(1).isUsed());
    }

    @Test
    @Transactional
    public void shop_ship_product() {
        orderDao.shopShipProduct("15aeafa1-2561-4098-ad07-e5d599c2ae3b");
        Order order = orderDao.getOrderByOrderId("15aeafa1-2561-4098-ad07-e5d599c2ae3b");
        assertEquals(LocalDate.now().toString(), order.getStartTime().toString());
        assertNull(order.getDeliverTime());
    }

    @Test
    @Transactional
    public void user_receive_product() {
        orderDao.userReceiveProduct("15aeafa1-2561-4098-ad07-e5d599c2ae3b");
        Order order = orderDao.getOrderByOrderId("15aeafa1-2561-4098-ad07-e5d599c2ae3b");
        assertEquals("2024-01-01", order.getStartTime().toString());
        assertEquals(LocalDate.now().toString(), order.getDeliverTime().toString());
    }
}