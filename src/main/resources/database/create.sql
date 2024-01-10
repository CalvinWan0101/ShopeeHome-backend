CREATE TABLE myuser
(
    id           VARCHAR(50)  NOT NULL PRIMARY KEY,
    email        VARCHAR(100) NOT NULL,
    password     VARCHAR(100) NOT NULL,
    name         VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    avatar       VARCHAR(10000000),
    is_deleted   BOOLEAN      NOT NULL
);

CREATE TABLE user_address
(
    address VARCHAR(200) NOT NULL,
    user_id VARCHAR(50) REFERENCES myuser (id),
    PRIMARY KEY (address, user_id)
);

CREATE TABLE admin
(
    id         VARCHAR(50)  NOT NULL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    password   VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN      NOT NULL
);

CREATE TABLE shop
(
    id           VARCHAR(50)  NOT NULL PRIMARY KEY,
    email        VARCHAR(100) NOT NULL,
    password     VARCHAR(100) NOT NULL,
    name         VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    address      VARCHAR(100) NOT NULL,
    description  VARCHAR(1000),
    avatar       VARCHAR(10000000),
    background   VARCHAR(10000000),
    creater_id   VARCHAR(50) REFERENCES admin (id),
    deleter_id   VARCHAR(50) REFERENCES admin (id),
    is_deleted   BOOLEAN      NOT NULL
);

CREATE TABLE product
(
    id            VARCHAR(50)  NOT NULL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    amount        INT          NOT NULL,
    sales         INT          NOT NULL,
    price         INT          NOT NULL,
    description   VARCHAR(1000),
    discount_rate DOUBLE PRECISION,
    discount_date DATE,
    shop_id       VARCHAR(50) REFERENCES shop (id),
    is_deleted    BOOLEAN      NOT NULL
);

CREATE TABLE product_image
(
    product_id  VARCHAR(50) REFERENCES product (id),
    image_order INT               NOT NULL,
    image       VARCHAR(10000000) NOT NULL,
    PRIMARY KEY (product_id, image_order)
);

CREATE TABLE coupon
(
    id         VARCHAR(50) NOT NULL PRIMARY KEY,
    date       DATE,
    shop_id    VARCHAR(50) REFERENCES shop (id),
    is_deleted BOOLEAN     NOT NULL
);

CREATE TABLE shipping_coupon
(
    coupon_id      VARCHAR(50) REFERENCES coupon (id),
    shipping_limit INT NOT NULL,
    PRIMARY KEY (coupon_id)
);

CREATE TABLE seasoning_coupon
(
    coupon_id VARCHAR(50) REFERENCES coupon (id),
    rate      DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (coupon_id)
);

CREATE TABLE user_has_coupon
(
    user_id   VARCHAR(50) REFERENCES myuser (id),
    coupon_id VARCHAR(50) REFERENCES coupon (id),
    is_used   BOOLEAN NOT NULL,
    PRIMARY KEY (user_id, coupon_id)
);

---------------------------------------------------

CREATE TABLE in_shopping_cart
(
    user_id    VARCHAR(50) REFERENCES myuser (id),
    product_id VARCHAR(50) REFERENCES product (id),
    quantity   INT NOT NULL,
    PRIMARY KEY (user_id, product_id)
);

---------------------------------------------------

CREATE TABLE myorder
(
    id            VARCHAR(50)  NOT NULL PRIMARY KEY,
    address       VARCHAR(100) NOT NULL,
    start_time    DATE,
    deliver_time  DATE,
    total_price   INT          NOT NULL,
    user_id       VARCHAR(50) REFERENCES myuser (id),
    shipping_cost INT          NOT NULL
);

CREATE TABLE order_include_product
(
    order_id   VARCHAR(50) REFERENCES myorder (id),
    product_id VARCHAR(50) REFERENCES product (id),
    quantity   INT NOT NULL,
    price      INT NOT NULL,
    PRIMARY KEY (product_id, order_id)
);

CREATE TABLE order_use_coupon
(
    order_id  VARCHAR(50) REFERENCES myorder (id),
    coupon_id VARCHAR(50) REFERENCES coupon (id),
    PRIMARY KEY (order_id, coupon_id)
);
