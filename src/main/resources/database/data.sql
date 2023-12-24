-- delete all data
DELETE FROM user_address;
DELETE FROM myuser;
DELETE FROM shop;
DELETE FROM admin;

-- myuser
INSERT INTO public.myuser (id, name, email, phone_number, password)
VALUES ('30e7e267-c791-424a-a94b-fa5e695d27e7', 'test1', 'test1@gmail.com', '0909001001',
        '5a105e8b9d40e1329780d62ea2265d8a'); --password: test1
INSERT INTO public.myuser (id, name, email, phone_number, password)
VALUES ('b8007834-0db6-4e8a-aa98-7833035bcaa0', 'test2', 'test2@gmail.com', '0909001002',
        'ad0234829205b9033196ba818f7a872b'); --password: test2
INSERT INTO public.myuser (id, name, email, phone_number, password)
VALUES ('111b08ef-3e0f-46f4-b83f-05a2443fafb7', 'test3', 'test3@gmail.com', '0909001003',
        '8ad8757baa8564dc136c1e07507f4a98'); --password: test3
INSERT INTO public.myuser (id, name, email, phone_number, password)
VALUES ('f27fa75f-2fae-412a-b4ca-f76a6077d041', 'test4', 'test4@gmail.com', '0909001004',
        '86985e105f79b95d6bc918fb45ec7727'); --password: test4
INSERT INTO public.myuser (id, name, email, phone_number, password)
VALUES ('c0e53cb5-3aa9-4607-b744-f4f233c7f652', 'test5', 'test5@gmail.com', '0909001005',
        'e3d704f3542b44a621ebed70dc0efe13'); --password: test5
INSERT INTO public.myuser (id, name, email, phone_number, password)
VALUES ('74244e64-73a8-46a7-ac69-4b20efab0d82', 'Calvin', 'calvin@gmail.com', '0909000111',
        'e6e66b8981c1030d5650da159e79539a');
--password: test5

-- user_address
INSERT INTO public.user_address (address, user_id)
VALUES ('address-test1-A', '30e7e267-c791-424a-a94b-fa5e695d27e7');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-test1-B', '30e7e267-c791-424a-a94b-fa5e695d27e7');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-test1-C', '30e7e267-c791-424a-a94b-fa5e695d27e7');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-test2-A', 'b8007834-0db6-4e8a-aa98-7833035bcaa0');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-test3-A', '111b08ef-3e0f-46f4-b83f-05a2443fafb7');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-tset4-A', 'f27fa75f-2fae-412a-b4ca-f76a6077d041');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-tset5-A', 'c0e53cb5-3aa9-4607-b744-f4f233c7f652');
INSERT INTO public.user_address (address, user_id)
VALUES ('address-calvin-A', '74244e64-73a8-46a7-ac69-4b20efab0d82');

-- admin
INSERT INTO admin (id, name, password)
VALUES ('17335ce6-af7c-4c21-af55-9eca9dc5dfb7', 'admin', '21232f297a57a5a743894a0e4a801fc3');
--password: admin

-- shop
INSERT INTO shop (id, address, phone_number, email, name, description, password, creater_id)
VALUES ('1013f7a0-0017-4c21-872f-c014914e6834', 'address1', '0909001001', 'shop1@gmail.com', 'Shop1', 'This is shop 1',
        '12186fe8a7b1dd053d95e8d3379c7271', '17335ce6-af7c-4c21-af55-9eca9dc5dfb7'); --password: shop1
INSERT INTO shop (id, address, phone_number, email, name, description, password, creater_id)
VALUES ('f0694ecf-6282-48f9-a401-49eb08067ce0', 'address2', '0909001002', 'shop2@gmail.com', 'Shop2', 'This is shop 2',
        '5370c7bc26a91164afc88362b70fce08', '17335ce6-af7c-4c21-af55-9eca9dc5dfb7'); --password: shop2