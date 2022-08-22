INSERT INTO abstract_user (role, firstname, lastname, password, username)
VALUES ('MANAGER', 'ali', 'alavi', '12345', 'manager'),
       ('CUSTOMER', 'Mahsa', 'Najaei', '12345', 'customer'),
       ('CUSTOMER', 'Mahla', 'jafari', '12345', 'customer1'),
       ('CUSTOMER', 'Hesam', 'bavafa', '12345', 'customer2');

INSERT INTO provider (name)
VALUES ('New Man'),
       ('Wayfair'),
       ('Mohtasham Kashan'),
       ('Pedal Pals'),
       ('Sony'),
       ('LG'),
       ('Room Essentials'),
       ('Fossil'),
       ('Skechers');

INSERT INTO product (name, price, stock_quantity, provider_id, votable, commentable, publicly_reviewable,
                     publicly_representable)
VALUES ('Leather Wallet', 2000000, 10, 1, false, true, true, true),
       ('Bike', 10500000, 4, 4, true, true, true, true),
       ('Napkin', 30000, 8, 200, false, false, false, true),
       ('Headphone', 1000000, 8, 2, true, true, false, true),
       ('Iranian Carpet', 15000000, 3, 3, true, false, true, true),
       ('Candle', 55000, 5, 7, true, true, true, true),
       ('Hand Clock', 6500000, 10, 8, true, false, false, true),
       ('Tablet', 21500000, 6, 5, false, false, false, true),
       ('Sport Shoes', 3500000, 20, true, true, true, true, false);

INSERT INTO orders (created_date, purchaser_id)
VALUES (CURRENT_TIMESTAMP, 1),
       (CURRENT_TIMESTAMP, 2),
       (CURRENT_TIMESTAMP, 3),
       (CURRENT_TIMESTAMP, 4);

INSERT INTO order_item (product_id, order_id, quantity)
VALUES (1, 1, 1),
       (4, 1, 1),
       (8, 1, 2),
       (5, 2, 1),
       (5, 3, 1),
       (2, 3, 1),
       (7, 4, 1),
       (6, 4, 5),
       (3, 4, 2);

INSERT INTO review (content, last_updated_date, product_id, reviewer_id, review_type, confirmation_status)
VALUES ('Nice wallet, plenty of pockets and big enough for change. Good design love it!', CURRENT_TIMESTAMP, 1, 3,
        'COMMENT', 'CONFIRMED'),
       ('Not worth the headache!', CURRENT_TIMESTAMP, 4, 1, "COMMENT", 'CONFIRMED'),
       ('10', CURRENT_TIMESTAMP, 6, 4, 'VOTE', 'CONFIRMED'),
       ('9', CURRENT_TIMESTAMP, 5, 2, 'VOTE', 'NOT_CONFIRMED'),
       ('8', CURRENT_TIMESTAMP, 1, 3, 'VOTE', 'CONFIRMED'),
       ('Good bike, good looking, amazing ride!', CURRENT_TIMESTAMP, 2, 2, 'COMMENT', 'CONFIRMED'),
       ('My absolute favourite. ', CURRENT_TIMESTAMP, 6, 4, 'COMMENT', 'CONFIRMED'),
       ('9.5', CURRENT_TIMESTAMP, 5, 4, 'VOTE', 'REJECTED'),
       ('10', CURRENT_TIMESTAMP, 5, 3, 'VOTE', 'CONFIRMED'),
       ('8.5', CURRENT_TIMESTAMP, 6, 3, 'VOTE', 'CONFIRMED'),
       ('6', CURRENT_TIMESTAMP, 5, 1, 'VOTE', 'CONFIRMED'),
       ('7.5', CURRENT_TIMESTAMP, 7, 4, 'VOTE', 'CONFIRMED'),
       ('I was able to get this bike on a great sale and it is well worth the amount that I spent on it!',
        CURRENT_TIMESTAMP, 2, 3, 'COMMENT', 'CONFIRMED'),
       ('Perfect bike for myself great build great quality great components', CURRENT_TIMESTAMP, 2, 1, 'COMMENT',
        'CONFIRMED'),
       ('5', CURRENT_TIMESTAMP, 1, 4, 'VOTE', 'NOT_CONFIRMED'),
       ('10', CURRENT_TIMESTAMP, 1, 1, 'VOTE', 'CONFIRMED'),
       ('It smells good but whatever. How long you let them burn at a time is one side of the wax up.',
        CURRENT_TIMESTAMP, 6, 2, 'COMMENT', 'CONFIRMED'),
       ('The seat kept slipped down, the gears were clunky and the breaks couldn’t be relied upon', CURRENT_TIMESTAMP,
        2, 4, 'COMMENT', 'CONFIRMED'),
       ('Its scent is great for relaxing!', CURRENT_TIMESTAMP, 6, 1, 'COMMENT', 'NOT_CONFIRMED'),
       ('4', CURRENT_TIMESTAMP, 6, 1, 'VOTE', 'REJECTED'),
       ('I Was so looking forward to trying this candle but was so disappointed.', CURRENT_TIMESTAMP, 6, 3, 'COMMENT',
        'CONFIRMED'),
       ('7.5', CURRENT_TIMESTAMP, 6, 2, 'VOTE', 'CONFIRMED');
