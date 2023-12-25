-- delete all data
DELETE FROM user_address;
DELETE FROM myuser;
DELETE FROM shop;
DELETE FROM admin;

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
        '0909005005', FALSE); --password: user5

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
VALUES ('17335ce6-af7c-4c21-af55-9eca9dc5dfb7', 'admin', '21232f297a57a5a743894a0e4a801fc3', FALSE); --password: admin

-- shop
INSERT INTO shop (id, email, password, name, phone_number, address, description, creater_id, is_deleted)
VALUES ('1013f7a0-0017-4c21-872f-c014914e6834', 'shop1@gmail.com', '12186fe8a7b1dd053d95e8d3379c7271', 'shop1',
        '0909001001', 'address1', 'This is shop 1', '17335ce6-af7c-4c21-af55-9eca9dc5dfb7', FALSE); --password: shop1
INSERT INTO shop (id, email, password, name, phone_number, address, description, creater_id, is_deleted)
VALUES ('f0694ecf-6282-48f9-a401-49eb08067ce0', 'shop2@gmail.com', '5370c7bc26a91164afc88362b70fce08', 'shop2',
        '0909002002', 'address2', 'This is shop 2', '17335ce6-af7c-4c21-af55-9eca9dc5dfb7', FALSE); --password: shop2