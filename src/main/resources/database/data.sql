-- delete all data
DELETE
FROM user_address;
DELETE
FROM myuser;
DELETE
FROM product_image;
DELETE
FROM product;
DELETE
FROM shop;
DELETE
FROM admin;

-- myuser
INSERT INTO myuser (id, email, password, name, phone_number, is_deleted)
VALUES ('30e7e267-c791-424a-a94b-fa5e695d27e7', 'user1@gmail.com', '24c9e15e52afc47c225b757e7bee1f9d', 'user1',
        '0909001001', FALSE); --password: user1
INSERT INTO myuser (id, email, password, name, phone_number, is_deleted)
VALUES ('b8007834-0db6-4e8a-aa98-7833035bcaa0', 'user2@gmail.com', '7e58d63b60197ceb55a1c487989a3720', 'user2',
        '0909002002', FALSE); --password: user2
INSERT INTO myuser (id, email, password, name, phone_number, is_deleted)
VALUES ('111b08ef-3e0f-46f4-b83f-05a2443fafb7', 'user3@gmail.com', '92877af70a45fd6a2ed7fe81e1236b78', 'user3',
        '0909003003', FALSE); --password: user3
INSERT INTO myuser (id, email, password, name, phone_number, is_deleted)
VALUES ('f27fa75f-2fae-412a-b4ca-f76a6077d041', 'user4@gmail.com', '3f02ebe3d7929b091e3d8ccfde2f3bc6', 'user4',
        '0909004004', FALSE); --password: user4
INSERT INTO myuser (id, email, password, name, phone_number, is_deleted)
VALUES ('c0e53cb5-3aa9-4607-b744-f4f233c7f652', 'user5@gmail.com', '0a791842f52a0acfbb3a783378c066b8', 'user5',
        '0909005005', FALSE);
--password: user5

-- user_address
INSERT INTO public.user_address (address, user_id)
VALUES ('address-user1-A', '30e7e267-c791-424a-a94b-fa5e695d27e7');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-user1-B', '30e7e267-c791-424a-a94b-fa5e695d27e7');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-user1-C', '30e7e267-c791-424a-a94b-fa5e695d27e7');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-user2-A', 'b8007834-0db6-4e8a-aa98-7833035bcaa0');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-user3-A', '111b08ef-3e0f-46f4-b83f-05a2443fafb7');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-user4-A', 'f27fa75f-2fae-412a-b4ca-f76a6077d041');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-user5-A', 'c0e53cb5-3aa9-4607-b744-f4f233c7f652');

-- admin
INSERT INTO admin (id, name, password, is_deleted)
VALUES ('17335ce6-af7c-4c21-af55-9eca9dc5dfb7', 'admin', '21232f297a57a5a743894a0e4a801fc3', FALSE);
--password: admin

-- shop
INSERT INTO shop (id, email, password, name, phone_number, address, description, creater_id, is_deleted)
VALUES ('1013f7a0-0017-4c21-872f-c014914e6834', 'shop1@gmail.com', '12186fe8a7b1dd053d95e8d3379c7271', 'shop1',
        '0909001001', 'address1', 'This is shop 1', '17335ce6-af7c-4c21-af55-9eca9dc5dfb7', FALSE); --password: shop1
INSERT INTO shop (id, email, password, name, phone_number, address, description, creater_id, is_deleted)
VALUES ('f0694ecf-6282-48f9-a401-49eb08067ce0', 'shop2@gmail.com', '5370c7bc26a91164afc88362b70fce08', 'shop2',
        '0909002002', 'address2', 'This is shop 2', '17335ce6-af7c-4c21-af55-9eca9dc5dfb7', FALSE);
--password: shop2

-- product
INSERT INTO product (id, name, amount, price, description, discount_rate, discount_date, shop_id)
VALUES ('6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 'iphone', 90, 36900, 'This is iphone', 0.87, '2024-07-31',
        '1013f7a0-0017-4c21-872f-c014914e6834');
INSERT INTO product (id, name, amount, price, description, discount_rate, discount_date, shop_id)
VALUES ('8c883a21-fad1-43af-8b15-54b2c1c7a70e', 'xiaomi', 140, 19999, 'This is xiaomi', 0.9, '2024-06-30',
        '1013f7a0-0017-4c21-872f-c014914e6834');
INSERT INTO product (id, name, amount, price, description, discount_rate, discount_date, shop_id)
VALUES ('acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3', 'tissue', 52123, 100, 'This is tissue', NULL, NULL,
        'f0694ecf-6282-48f9-a401-49eb08067ce0');
INSERT INTO product (id, name, amount, price, description, discount_rate, discount_date, shop_id)
VALUES ('9595f97a-bf11-488a-8c15-9edf4db1c450', 'toothbrush', 279123, 50, 'This is toothbrush', NULL, NULL,
        'f0694ecf-6282-48f9-a401-49eb08067ce0');


-- product_image
-- iphone_1
INSERT INTO product_image (product_id, id, image)
VALUES ('6874ada1-747f-41a7-bb9a-613d2ec0ce1d', 'acfe49b5-4d14-403b-a4f4-866730067c6a', '1');
-- iphone_2
INSERT INTO product_image (product_id, id, image)
VALUES ('6874ada1-747f-41a7-bb9a-613d2ec0ce1d', '4b8959b8-53c0-4572-83ec-a72bafda5d10', '1');
-- xiaomi_1
INSERT INTO product_image (product_id, id, image)
VALUES ('8c883a21-fad1-43af-8b15-54b2c1c7a70e', 'f459381e-e9cc-4ad4-a349-a8df87bae08c', '1');
-- xiaomi_2
INSERT INTO product_image (product_id, id, image)
VALUES ('8c883a21-fad1-43af-8b15-54b2c1c7a70e', '57cfec63-d9e3-4acc-971a-e22e34d8a802', '1');
-- tissue_1
INSERT INTO product_image (product_id, id, image)
VALUES ('acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3', 'd2991c4b-86ec-4c19-94e5-8e1ccf203355', '1');
-- tissue_2
INSERT INTO product_image (product_id, id, image)
VALUES ('acbe9e99-76db-4b1f-a9f4-3fe850c3d3f3', '8ac398a8-afe3-401f-bef3-01191bdd5743', '1');
-- toothbrush_1
INSERT INTO product_image (product_id, id, image)
VALUES ('9595f97a-bf11-488a-8c15-9edf4db1c450', '9414b8a5-8033-4769-b680-e471ef54fb1d', '1');
-- toothbrush_2
INSERT INTO product_image (product_id, id, image)
VALUES ('9595f97a-bf11-488a-8c15-9edf4db1c450', '767baa08-94fc-40ee-bf46-af6d69e340ff', '1');