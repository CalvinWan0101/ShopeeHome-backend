-- delete all data
DELETE
FROM user_address;
DELETE
FROM product_image;
DELETE
FROM shipping_coupon;
DELETE
FROM seasoning_coupon;
DELETE
FROM user_has_coupon;
DELETE
FROM in_shopping_cart;
DELETE
FROM order_include_product;
DELETE
FROM product;
DELETE
FROM order_use_coupon;
DELETE
FROM coupon;
DELETE
FROM shop;
DELETE
FROM admin;
DELETE
FROM myorder;
DELETE
FROM myuser;

-- myuser
INSERT INTO myuser (id, email, password, name, phone_number, avatar, is_deleted)
VALUES ('30e7e267-c791-424a-a94b-fa5e695d27e7', 'user1@gmail.com', '24c9e15e52afc47c225b757e7bee1f9d', 'user1',
        '0909001001', 'user1_avatar', FALSE), --password: user1
       ('b8007834-0db6-4e8a-aa98-7833035bcaa0', 'user2@gmail.com', '7e58d63b60197ceb55a1c487989a3720', 'user2',
        '0909002002', 'user2_avatar', FALSE), --password: user2
       ('111b08ef-3e0f-46f4-b83f-05a2443fafb7', 'user3@gmail.com', '92877af70a45fd6a2ed7fe81e1236b78', 'user3',
        '0909003003', 'user3_avatar', FALSE), --password: user3
       ('f27fa75f-2fae-412a-b4ca-f76a6077d041', 'user4@gmail.com', '3f02ebe3d7929b091e3d8ccfde2f3bc6', 'user4',
        '0909004004', 'user4_avatar', FALSE), --password: user4
       ('c0e53cb5-3aa9-4607-b744-f4f233c7f652', 'user5@gmail.com', '0a791842f52a0acfbb3a783378c066b8', 'user5',
        '0909005005', 'user5_avatar', FALSE);
--password: user5

-- user_address
INSERT INTO public.user_address (address, user_id)
VALUES ('address-user1-A', '30e7e267-c791-424a-a94b-fa5e695d27e7'),
       ('address-user1-B', '30e7e267-c791-424a-a94b-fa5e695d27e7'),
       ('address-user1-C', '30e7e267-c791-424a-a94b-fa5e695d27e7'),
       ('address-user2-A', 'b8007834-0db6-4e8a-aa98-7833035bcaa0'),
       ('address-user3-A', '111b08ef-3e0f-46f4-b83f-05a2443fafb7'),
       ('address-user4-A', 'f27fa75f-2fae-412a-b4ca-f76a6077d041'),
       ('address-user5-A', 'c0e53cb5-3aa9-4607-b744-f4f233c7f652');

-- admin
INSERT INTO admin (id, name, password, is_deleted)
VALUES ('17335ce6-af7c-4c21-af55-9eca9dc5dfb7', 'admin', '21232f297a57a5a743894a0e4a801fc3', FALSE);
--password: admin

-- shop
INSERT INTO shop (id, email, password, name, phone_number, address, description, avatar, background, creater_id,
                  is_deleted)
VALUES ('1013f7a0-0017-4c21-872f-c014914e6834', 'shop1@gmail.com', '12186fe8a7b1dd053d95e8d3379c7271', 'shop1',
        '0909001001', 'address1', 'This is shop 1', 'shop1_avatar', 'shop1_background',
        '17335ce6-af7c-4c21-af55-9eca9dc5dfb7', FALSE), --password: shop1
       ('f0694ecf-6282-48f9-a401-49eb08067ce0', 'shop2@gmail.com', '5370c7bc26a91164afc88362b70fce08', 'shop2',
        '0909002002', 'address2', 'This is shop 2', 'shop1_avatar', 'shop1_background',
        '17335ce6-af7c-4c21-af55-9eca9dc5dfb7', FALSE);
--password: shop2

-- product
INSERT INTO product (id, name, amount, sales, price, description, discount_rate, discount_date, shop_id, is_deleted)
VALUES ('6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 'iphone', 90, 27, 36900, 'This is iphone', 0.87, '2024-07-31',
        '1013f7a0-0017-4c21-872f-c014914e6834', FALSE),
       ('8c883a21-fad1-43af-8b15-54b2c1c7a70e', 'xiaomi', 140, 30, 19999, 'This is xiaomi', 0.9, '2024-06-30',
        '1013f7a0-0017-4c21-872f-c014914e6834', FALSE),
       ('acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3', 'tissue', 52123, 29134, 100, 'This is tissue', NULL, NULL,
        'f0694ecf-6282-48f9-a401-49eb08067ce0', FALSE),
       ('9595f97a-bf11-488a-8c15-9edf4db1c450', 'toothbrush', 279123, 34126, 50, 'This is toothbrush', 0.1,
        '2023-08-07',
        'f0694ecf-6282-48f9-a401-49eb08067ce0', FALSE),
       ('4f366b46-50ea-42d9-8216-e677f43b1819', 'backpack', 297, 26, 2100, 'This is backpack', NULL, NULL,
        'f0694ecf-6282-48f9-a401-49eb08067ce0', TRUE);


-- product_image
-- iphone_1
INSERT INTO product_image (product_id, image_order, image)
VALUES ('6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 1, 'iphone_image_1'),     -- iphone_1
       ('6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 2, 'iphone_image_2'),     -- iphone_2
       ('8c883a21-fad1-43af-8b15-54b2c1c7a70e', 1, 'xiaomi_image_1'),     -- xiaomi_1
       ('8c883a21-fad1-43af-8b15-54b2c1c7a70e', 2, 'xiaomi_image_2'),     -- xiaomi_2
       ('acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3', 1, 'tissue_image_1'),     -- tissue_1
       ('acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3', 2, 'tissue_image_2'),     -- tissue_2
       ('9595f97a-bf11-488a-8c15-9edf4db1c450', 1, 'toothbrush_image_1'), -- toothbrush_1
       ('9595f97a-bf11-488a-8c15-9edf4db1c450', 2, 'toothbrush_image_2');
-- toothbrush_2

-- coupon
INSERT INTO coupon (id, date, shop_id, is_deleted)
VALUES ('3bfd295f-3215-4585-b935-6e253ad1e54f', '2024-01-01', '1013f7a0-0017-4c21-872f-c014914e6834',
        FALSE), --shop1 ship A1
       ('ca752e58-0387-4116-9f93-e9043db87b52', '2024-02-01', '1013f7a0-0017-4c21-872f-c014914e6834',
        FALSE), --shop1 ship A2
       ('efbec3f1-563b-4b71-892b-a6db85bf76dc', '2024-03-01', '1013f7a0-0017-4c21-872f-c014914e6834',
        FALSE), --shop1 seas A3
       ('838db05f-07fe-44e4-907f-cf7919cf6777', '2024-04-01', '1013f7a0-0017-4c21-872f-c014914e6834',
        FALSE), --shop1 seas A4
       ('7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca', '1997-05-01', '1013f7a0-0017-4c21-872f-c014914e6834',
        FALSE), --shop1 ship A5
       ('9a6e090a-e561-44a3-933e-f9da089ba6e9', '2024-01-01', 'f0694ecf-6282-48f9-a401-49eb08067ce0',
        FALSE), --shop2 ship B1
       ('bfc0f61b-5e61-4604-b015-cfecbb0810cf', '2024-02-01', 'f0694ecf-6282-48f9-a401-49eb08067ce0',
        FALSE), --shop2 ship B2
       ('66fbc0ec-8357-43b6-89dc-dc8082c5dcca', '2024-03-01', 'f0694ecf-6282-48f9-a401-49eb08067ce0',
        FALSE), --shop2 seas B3
       ('22960254-75b3-47c2-bbdc-e596a9ad3a4a', '2024-04-01', 'f0694ecf-6282-48f9-a401-49eb08067ce0',
        FALSE), --shop2 seas B4
       ('f62870eb-1d71-48f4-ba28-471a80f62f67', '1997-05-01', 'f0694ecf-6282-48f9-a401-49eb08067ce0', FALSE);
--shop2 seas B5

-- user_has_coupon
INSERT INTO user_has_coupon (user_id, coupon_id, is_used)
VALUES ('30e7e267-c791-424a-a94b-fa5e695d27e7', '3bfd295f-3215-4585-b935-6e253ad1e54f', FALSE), -- user1 A1
       ('b8007834-0db6-4e8a-aa98-7833035bcaa0', 'ca752e58-0387-4116-9f93-e9043db87b52', FALSE), -- user2 A2
       ('30e7e267-c791-424a-a94b-fa5e695d27e7', 'efbec3f1-563b-4b71-892b-a6db85bf76dc', FALSE), -- user1 A3
       ('b8007834-0db6-4e8a-aa98-7833035bcaa0', '838db05f-07fe-44e4-907f-cf7919cf6777', FALSE), -- user2 A4
       ('30e7e267-c791-424a-a94b-fa5e695d27e7', '7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca', FALSE), -- user1 A5
       ('b8007834-0db6-4e8a-aa98-7833035bcaa0', '9a6e090a-e561-44a3-933e-f9da089ba6e9', FALSE), -- user2 B1
       ('30e7e267-c791-424a-a94b-fa5e695d27e7', 'bfc0f61b-5e61-4604-b015-cfecbb0810cf', FALSE), -- user1 B2
       ('b8007834-0db6-4e8a-aa98-7833035bcaa0', '66fbc0ec-8357-43b6-89dc-dc8082c5dcca', FALSE), -- user2 B3
       ('30e7e267-c791-424a-a94b-fa5e695d27e7', '22960254-75b3-47c2-bbdc-e596a9ad3a4a', FALSE), -- user1 B4
       ('b8007834-0db6-4e8a-aa98-7833035bcaa0', 'f62870eb-1d71-48f4-ba28-471a80f62f67', FALSE);
-- user2 B5


-- shipping_coupon
INSERT INTO shipping_coupon (coupon_id, shipping_limit)
VALUES ('3bfd295f-3215-4585-b935-6e253ad1e54f', 1000),
       ('ca752e58-0387-4116-9f93-e9043db87b52', 2000),
       ('9a6e090a-e561-44a3-933e-f9da089ba6e9', 1000),
       ('bfc0f61b-5e61-4604-b015-cfecbb0810cf', 2000),
       ('7fb7d00f-af49-4fcf-86ea-0a44cbcbe2ca', 1000);

-- seasoning_coupon
INSERT INTO seasoning_coupon (coupon_id, rate)
VALUES ('efbec3f1-563b-4b71-892b-a6db85bf76dc', 0.1),
       ('838db05f-07fe-44e4-907f-cf7919cf6777', 0.2),
       ('66fbc0ec-8357-43b6-89dc-dc8082c5dcca', 0.1),
       ('22960254-75b3-47c2-bbdc-e596a9ad3a4a', 0.2),
       ('f62870eb-1d71-48f4-ba28-471a80f62f67', 0.1);

-- in_shopping_cart
INSERT INTO in_shopping_cart (user_id, product_id, quantity)
VALUES ('30e7e267-c791-424a-a94b-fa5e695d27e7', '6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 1),
       ('30e7e267-c791-424a-a94b-fa5e695d27e7', '8c883a21-fad1-43af-8b15-54b2c1c7a70e', 2),
       ('30e7e267-c791-424a-a94b-fa5e695d27e7', 'acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3', 3),
       ('30e7e267-c791-424a-a94b-fa5e695d27e7', '9595f97a-bf11-488a-8c15-9edf4db1c450', 4);

-- myorder
INSERT INTO myorder (id, address, start_time, deliver_time, total_price, user_id, shipping_cost)
VALUES ('15aeafa1-2561-4098-ad07-e5d599c2ae3b', '比基尼環礁比奇堡貝殼街124號鳳梨屋', '2024-01-01', NULL, 100264,
        '30e7e267-c791-424a-a94b-fa5e695d27e7', 60), -- no coupon
       ('f9d01135-323e-479d-837a-889ab8916f49', '比基尼環礁比奇堡貝殼街124號鳳梨屋', NULL, NULL, 10080,
        '30e7e267-c791-424a-a94b-fa5e695d27e7', 60), -- seasoning coupon 0.1
       ('629af7fc-ffd9-4201-b18a-cd9096314f05', '比基尼環礁比奇堡貝殼街124號鳳梨屋', '2024-01-03', NULL, 100204,
        '30e7e267-c791-424a-a94b-fa5e695d27e7', 0);
-- shipping coupon

-- order_user_coupon
INSERT INTO order_use_coupon (order_id, coupon_id)
VALUES ('f9d01135-323e-479d-837a-889ab8916f49', 'efbec3f1-563b-4b71-892b-a6db85bf76dc'),
       ('629af7fc-ffd9-4201-b18a-cd9096314f05', '3bfd295f-3215-4585-b935-6e253ad1e54f');

-- order_include_product
INSERT INTO order_include_product (order_id, product_id, quantity, price)
VALUES ('15aeafa1-2561-4098-ad07-e5d599c2ae3b', '6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 2, 32103),
       ('15aeafa1-2561-4098-ad07-e5d599c2ae3b', '8c883a21-fad1-43af-8b15-54b2c1c7a70e', 2, 17999),
       ('f9d01135-323e-479d-837a-889ab8916f49', '6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 2, 32103),
       ('f9d01135-323e-479d-837a-889ab8916f49', '8c883a21-fad1-43af-8b15-54b2c1c7a70e', 2, 17999),
       ('629af7fc-ffd9-4201-b18a-cd9096314f05', '6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 2, 32103),
       ('629af7fc-ffd9-4201-b18a-cd9096314f05', '8c883a21-fad1-43af-8b15-54b2c1c7a70e', 2, 17999);

