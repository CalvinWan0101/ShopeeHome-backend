package com.calvinwan.shopeehomebackend.dao.implementation;

import com.calvinwan.shopeehomebackend.dao.OrderDao;
import com.calvinwan.shopeehomebackend.dao.SeasoningCouponDao;
import com.calvinwan.shopeehomebackend.dao.ShippingCouponDao;
import com.calvinwan.shopeehomebackend.dto.coupon.shipping.ShippingCouponUserDto;
import com.calvinwan.shopeehomebackend.dto.order.OrderCreateDto;
import com.calvinwan.shopeehomebackend.dto.order.OrderProductDto;
import com.calvinwan.shopeehomebackend.model.order.Order;
import com.calvinwan.shopeehomebackend.model.order.OrderProduct;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import tw.teddysoft.ezspec.EzFeature;
import tw.teddysoft.ezspec.extension.junit5.EzScenario;
import tw.teddysoft.ezspec.keyword.Feature;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@EzFeature
@SpringBootTest
@Sql(scripts = "/database/data.sql")
public class OrderDaoImplementationSpec {
    @Autowired
    OrderDao orderDao;
    @Autowired
    SeasoningCouponDao seasoningCouponDao;
    @Autowired
    ShippingCouponDao shippingCouponDao;
    static Feature feature;

    @BeforeAll
    public static void setUp() {
        feature = Feature.New("OrderDaoImplementation", "As a user\n" + "  I want to retrieve order details by ID or name\n" + "  So that I can manage orders efficiently");
        feature.NewRule("Get order");
        feature.NewRule("Create order");
        feature.NewRule("Shop ship product");
        feature.NewRule("User receive product");
    }

    @EzScenario
    public void get_order_by_order_id() {
        feature.newScenario().withRule("Get order")
                .Given("an order ID", env -> {
                    env.put("id", "15aeafa1-2561-4098-ad07-e5d599c2ae3b");
                })
                .When("I retrieve the order by ID", env -> {
                    env.put("order", orderDao.getOrderByOrderId((env.gets("id"))));
                })
                .ThenSuccess(env -> {
                    Order order = env.get("order", Order.class);
                    assertNotNull(order);
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
                })
                .And("The order should have 2 products", env -> {
                    Order order = env.get("order", Order.class);
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
                })
                .Execute();
    }

    @EzScenario
    public void get_order_by_order_id_not_found() {
        feature.newScenario().withRule("Get order")
                .Given("an invalid order ID", env -> {
                    env.put("id", "invalid-id");
                }).When("I retrieve the order by ID", env -> {
                    env.put("order", orderDao.getOrderByOrderId((env.gets("id"))));
                }).ThenSuccess(env -> {
                    Order order = env.get("order", Order.class);
                    assertNull(order);
                }).Execute();
    }

    @EzScenario
    public void get_order_by_user_id() {
        feature.newScenario().withRule("Get order")
                .Given("a user ID", env -> {
                    env.put("id", "30e7e267-c791-424a-a94b-fa5e695d27e7");
                })
                .When("I retrieve the three orders by user ID", env -> {
                    List<Order> orders = orderDao.getOrderByUserId((env.gets("id"))).stream().sorted((Comparator.comparing(Order::getId))).toList();
                    env.put("orders", orders);
                })
                .ThenSuccess(env -> {
                    List<Order> orders = env.get("orders", List.class);
                    assertEquals(3, orders.size());
                })
                .And("I should get the correct order details of the first order", env -> {
                    Order order1 = (Order) env.get("orders", List.class).get(0);
                    env.put("order1", order1);
                    assertEquals("15aeafa1-2561-4098-ad07-e5d599c2ae3b", order1.getId());
                    assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order1.getAddress());
                    assertEquals("2024-01-01", order1.getStartTime().toString());
                    assertNull(order1.getDeliverTime());
                    assertEquals(100264, order1.getTotalPrice());
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order1.getUserId());
                    assertEquals(60, order1.getShippingCost());
                })
                .And("I should get the correct order details of the second order", env -> {
                    Order order2 = (Order) env.get("orders", List.class).get(1);
                    env.put("order2", order2);
                    assertEquals("629af7fc-ffd9-4201-b18a-cd9096314f05", order2.getId());
                    assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order2.getAddress());
                    assertEquals("2024-01-03", order2.getStartTime().toString());
                    assertNull(order2.getDeliverTime());
                    assertEquals(100204, order2.getTotalPrice());
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order2.getUserId());
                    assertEquals(0, order2.getShippingCost());
                })
                .And("I should get the correct order details of the third order", env -> {
                    Order order3 = (Order) env.get("orders", List.class).get(2);
                    env.put("order3", order3);
                    assertEquals("f9d01135-323e-479d-837a-889ab8916f49", order3.getId());
                    assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order3.getAddress());
                    assertNull(order3.getStartTime());
                    assertNull(order3.getDeliverTime());
                    assertEquals(10080, order3.getTotalPrice());
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order3.getUserId());
                    assertEquals(60, order3.getShippingCost());
                })
                .And("The first order should have 2 products", env -> {
                    Order order1 = env.get("order1", Order.class);
                    List<OrderProduct> products = order1.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
                    assertEquals(2, products.size());
                    assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", products.get(0).getId());
                    assertEquals("iphone", products.get(0).getName());
                    assertEquals(32103, products.get(0).getPrice());
                    assertEquals(2, products.get(0).getQuantity());
                    assertEquals("iphone_image_1", products.get(0).getImage());
                    assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", products.get(1).getId());
                    assertEquals("xiaomi", products.get(1).getName());
                    assertEquals(17999, products.get(1).getPrice());
                    assertEquals(2, products.get(1).getQuantity());
                    assertEquals("xiaomi_image_1", products.get(1).getImage());
                })
                .And("The second order should have 1 product", env -> {
                    Order order2 = env.get("order2", Order.class);
                    List<OrderProduct> products = order2.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
                    assertEquals(2, order2.getProducts().size());
                    assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", products.get(0).getId());
                    assertEquals("iphone", products.get(0).getName());
                    assertEquals(32103, products.get(0).getPrice());
                    assertEquals(2, products.get(0).getQuantity());
                    assertEquals("iphone_image_1", products.get(0).getImage());
                    assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", products.get(1).getId());
                    assertEquals("xiaomi", products.get(1).getName());
                    assertEquals(17999, products.get(1).getPrice());
                    assertEquals(2, products.get(1).getQuantity());
                    assertEquals("xiaomi_image_1", products.get(1).getImage());
                })
                .And("The third order should have 1 product", env -> {
                    Order order3 = env.get("order3", Order.class);
                    List<OrderProduct> products = order3.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
                    assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", products.get(0).getId());
                    assertEquals("iphone", products.get(0).getName());
                    assertEquals(32103, products.get(0).getPrice());
                    assertEquals(2, products.get(0).getQuantity());
                    assertEquals("iphone_image_1", products.get(0).getImage());
                    assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", products.get(1).getId());
                    assertEquals("xiaomi", products.get(1).getName());
                    assertEquals(17999, products.get(1).getPrice());
                    assertEquals(2, products.get(1).getQuantity());
                    assertEquals("xiaomi_image_1", products.get(1).getImage());
                })
                .Execute();
    }

    @EzScenario
    public void get_order_by_user_id_not_found() {
        feature.newScenario().withRule("Get order")
                .Given("an invalid user ID", env -> {
                    env.put("id", "invalid-id");
                })
                .When("I retrieve the order by user ID", env -> {
                    List<Order> orders = orderDao.getOrderByUserId((env.gets("id")));
                    env.put("orders", orders);
                })
                .ThenSuccess(env -> {
                    assertNull(env.get("orders", List.class));
                })
                .Execute();
    }

    @EzScenario
    public void get_order_by_shop_id() {
        feature.newScenario().withRule("Get order")
                .Given("a shop ID: $1013f7a0-0017-4c21-872f-c014914e6834", env -> {
                    env.put("id", env.getArg(0));
                })
                .When("I retrieve the three orders by user ID", env -> {
                    List<Order> orders = orderDao.getOrderByShopId((env.gets("id"))).stream().sorted((Comparator.comparing(Order::getId))).toList();
                    env.put("orders", orders);
                })
                .ThenSuccess(env -> {
                    List<Order> orders = env.get("orders", List.class);
                    assertEquals(3, orders.size());
                })
                .And("I should get the correct order details of the first order", env -> {
                    Order order1 = (Order) env.get("orders", List.class).get(0);
                    env.put("order1", order1);
                    assertEquals("15aeafa1-2561-4098-ad07-e5d599c2ae3b", order1.getId());
                    assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order1.getAddress());
                    assertEquals("2024-01-01", order1.getStartTime().toString());
                    assertNull(order1.getDeliverTime());
                    assertEquals(100264, order1.getTotalPrice());
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order1.getUserId());
                    assertEquals(60, order1.getShippingCost());
                })
                .And("I should get the correct order details of the second order", env -> {
                    Order order2 = (Order) env.get("orders", List.class).get(1);
                    env.put("order2", order2);
                    assertEquals("629af7fc-ffd9-4201-b18a-cd9096314f05", order2.getId());
                    assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order2.getAddress());
                    assertEquals("2024-01-03", order2.getStartTime().toString());
                    assertNull(order2.getDeliverTime());
                    assertEquals(100204, order2.getTotalPrice());
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order2.getUserId());
                    assertEquals(0, order2.getShippingCost());
                })
                .And("I should get the correct order details of the third order", env -> {
                    Order order3 = (Order) env.get("orders", List.class).get(2);
                    env.put("order3", order3);
                    assertEquals("f9d01135-323e-479d-837a-889ab8916f49", order3.getId());
                    assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order3.getAddress());
                    assertNull(order3.getStartTime());
                    assertNull(order3.getDeliverTime());
                    assertEquals(10080, order3.getTotalPrice());
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order3.getUserId());
                    assertEquals(60, order3.getShippingCost());
                })
                .And("The first order should have 2 products", env -> {
                    Order order1 = env.get("order1", Order.class);
                    List<OrderProduct> products = order1.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
                    assertEquals(2, products.size());
                    assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", products.get(0).getId());
                    assertEquals("iphone", products.get(0).getName());
                    assertEquals(32103, products.get(0).getPrice());
                    assertEquals(2, products.get(0).getQuantity());
                    assertEquals("iphone_image_1", products.get(0).getImage());
                    assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", products.get(1).getId());
                    assertEquals("xiaomi", products.get(1).getName());
                    assertEquals(17999, products.get(1).getPrice());
                    assertEquals(2, products.get(1).getQuantity());
                    assertEquals("xiaomi_image_1", products.get(1).getImage());
                })
                .And("The second order should have 1 product", env -> {
                    Order order2 = env.get("order2", Order.class);
                    List<OrderProduct> products = order2.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
                    assertEquals(2, order2.getProducts().size());
                    assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", products.get(0).getId());
                    assertEquals("iphone", products.get(0).getName());
                    assertEquals(32103, products.get(0).getPrice());
                    assertEquals(2, products.get(0).getQuantity());
                    assertEquals("iphone_image_1", products.get(0).getImage());
                    assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", products.get(1).getId());
                    assertEquals("xiaomi", products.get(1).getName());
                    assertEquals(17999, products.get(1).getPrice());
                    assertEquals(2, products.get(1).getQuantity());
                    assertEquals("xiaomi_image_1", products.get(1).getImage());
                })
                .And("The third order should have 1 product", env -> {
                    Order order3 = env.get("order3", Order.class);
                    List<OrderProduct> products = order3.getProducts().stream().sorted(Comparator.comparing(OrderProduct::getId)).toList();
                    assertEquals("6874ada1-747f-41a7-bb9a-613d2ec0ce1d", products.get(0).getId());
                    assertEquals("iphone", products.get(0).getName());
                    assertEquals(32103, products.get(0).getPrice());
                    assertEquals(2, products.get(0).getQuantity());
                    assertEquals("iphone_image_1", products.get(0).getImage());
                    assertEquals("8c883a21-fad1-43af-8b15-54b2c1c7a70e", products.get(1).getId());
                    assertEquals("xiaomi", products.get(1).getName());
                    assertEquals(17999, products.get(1).getPrice());
                    assertEquals(2, products.get(1).getQuantity());
                    assertEquals("xiaomi_image_1", products.get(1).getImage());
                })
                .Execute();
    }

    @EzScenario
    public void get_order_by_shop_id_not_found() {
        feature.newScenario().withRule("Get order")
                .Given("an invalid shop ID: $invalid-id", env -> {
                    env.put("id", env.getArg(0));
                })
                .When("I retrieve the order by shop ID", env -> {
                    List<Order> orders = orderDao.getOrderByShopId((env.gets("id")));
                    env.put("orders", orders);
                })
                .ThenSuccess(env -> {
                    assertNull(env.get("orders", List.class));
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void create_order_without_coupon() {
        feature.newScenario().withRule("Create order")
                .Given("The order details", env -> {
                    env.put("products", List.of(
                            new OrderProductDto("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", 2),
                            new OrderProductDto("9595f97a-bf11-488a-8c15-9edf4db1c450", 2)
                    ));
                    env.put("userId", "30e7e267-c791-424a-a94b-fa5e695d27e7");
                    env.put("address", "比基尼環礁比奇堡貝殼街124號鳳梨屋");
                })
                .When("I create the order", env -> {
                    OrderCreateDto orderCreateDto = new OrderCreateDto(
                            env.get("userId", String.class),
                            env.get("address", String.class),
                            null,
                            env.get("products", List.class));
                    String orderId = orderDao.createOrder(orderCreateDto);
                    env.put("id", orderId);
                    env.put("order", orderDao.getOrderByOrderId(orderId));
                })
                .ThenSuccess(env -> {
                    Order order = env.get("order", Order.class);
                    assertEquals(env.gets("id"), order.getId());
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order.getUserId());
                    assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order.getAddress());
                    assertNull(order.getCouponId());
                    assertEquals(360, order.getTotalPrice());
                    assertEquals(60, order.getShippingCost());
                    assertEquals(0, order.getRate());
                    assertEquals(0, order.getShippingLimit());
                    assertNull(order.getStartTime());
                    assertNull(order.getDeliverTime());
                })
                .And("The order should have 2 products", env -> {
                    Order order = env.get("order", Order.class);
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
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void create_order_with_shipping_coupon() {
        feature.newScenario().withRule("Create order")
                .Given("The order details", env -> {
                    env.put("products", List.of(
                            new OrderProductDto("acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3", 200),
                            new OrderProductDto("9595f97a-bf11-488a-8c15-9edf4db1c450", 200)
                    ));
                    env.put("userId", "30e7e267-c791-424a-a94b-fa5e695d27e7");
                    env.put("address", "比基尼環礁比奇堡貝殼街124號鳳梨屋");
                })
                .When("I create the order with coupon: $bfc0f61b-5e61-4604-b015-cfecbb0810cf", env -> {
                    OrderCreateDto orderCreateDto = new OrderCreateDto(
                            env.get("userId", String.class),
                            env.get("address", String.class),
                            env.getArg(0),
                            env.get("products", List.class));
                    String orderId = orderDao.createOrder(orderCreateDto);
                    env.put("id", orderId);
                    env.put("order", orderDao.getOrderByOrderId(orderId));
                })
                .ThenSuccess(env -> {
                    Order order = env.get("order", Order.class);
                    assertEquals(env.gets("id"), order.getId());
                    assertEquals("30e7e267-c791-424a-a94b-fa5e695d27e7", order.getUserId());
                    assertEquals("比基尼環礁比奇堡貝殼街124號鳳梨屋", order.getAddress());
                    assertEquals("bfc0f61b-5e61-4604-b015-cfecbb0810cf", order.getCouponId());
                    assertEquals(30000, order.getTotalPrice());
                    assertEquals(0, order.getShippingCost());
                    assertEquals(0, order.getRate());
                    assertEquals(2000, order.getShippingLimit());
                    assertNull(order.getStartTime());
                    assertNull(order.getDeliverTime());
                })
                .And("The order should have 2 products", env -> {
                    Order order = env.get("order", Order.class);
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
                })
                .And("The shipping coupon for shop: $f0694ecf-6282-48f9-a401-49eb08067ce0 should be used", env -> {
                    List<ShippingCouponUserDto> shippingCouponUserDto = shippingCouponDao.userGetByShopId("30e7e267-c791-424a-a94b-fa5e695d27e7", env.getArg(0));
                    assertEquals(2, shippingCouponUserDto.size());
                    assertEquals("9a6e090a-e561-44a3-933e-f9da089ba6e9", shippingCouponUserDto.get(0).getId());
                    assertFalse(shippingCouponUserDto.get(0).isDeleted());
                    assertFalse(shippingCouponUserDto.get(0).isClaimed());
                    assertFalse(shippingCouponUserDto.get(0).isUsed());
                    assertEquals("bfc0f61b-5e61-4604-b015-cfecbb0810cf", shippingCouponUserDto.get(1).getId());
                    assertFalse(shippingCouponUserDto.get(1).isDeleted());
                    assertTrue(shippingCouponUserDto.get(1).isClaimed());
                    assertTrue(shippingCouponUserDto.get(1).isUsed());
                })
                .Execute();
    }

    @EzScenario
    @Transactional
    public void shop_ship_product() {
        feature.newScenario().withRule("Shop ship product")
                .Given("The order ID: $15aeafa1-2561-4098-ad07-e5d599c2ae3b", env -> {
                    env.put("id", env.getArg(0));
                })
                .When("I shop ship the product", env -> {
                    orderDao.shopShipProduct(env.gets("id"));
                })
                .ThenSuccess(env -> {
                    Order order = orderDao.getOrderByOrderId(env.gets("id"));
                    assertEquals(LocalDate.now().toString(), order.getStartTime().toString());
                    assertNull(order.getDeliverTime());
                })
                .Execute();
    }
    
    @EzScenario
    @Transactional
    public void user_receive_product() {
        feature.newScenario().withRule("User receive product")
                .Given("The order ID: $15aeafa1-2561-4098-ad07-e5d599c2ae3b", env -> {
                    env.put("id", env.getArg(0));
                })
                .When("I user receive the product", env -> {
                    orderDao.userReceiveProduct(env.gets("id"));
                })
                .ThenSuccess(env -> {
                    Order order = orderDao.getOrderByOrderId(env.gets("id"));
                    assertEquals(LocalDate.now().toString(), order.getDeliverTime().toString());
                })
                .Execute();
    }
}
