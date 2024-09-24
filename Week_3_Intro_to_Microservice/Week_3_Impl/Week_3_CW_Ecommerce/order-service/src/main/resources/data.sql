INSERT INTO orders (total_price, order_status) VALUES
    (799.99, 'CONFIRMED'),
    (299.99, 'CANCELLED'),
    (549.99, 'PENDING'),
    (1499.99, 'CANCELLED'),
    (89.99, 'CONFIRMED'),
    (1199.99, 'PENDING'),
    (149.99, 'CONFIRMED'),
    (999.99, 'CANCELLED'),
    (49.99, 'PENDING'),
    (129.99, 'CONFIRMED'),
    (249.99, 'DELIVERED'),
    (699.99, 'PENDING'),
    (399.99, 'DELIVERED');



INSERT INTO order_item (order_id, product_id, quantity) VALUES
(1, 3, 2),
(1, 5, 1),
(2, 4, 1),
(3, 1, 3),
(4, 2, 2),
(5, 10, 1),
(6, 9, 2),
(7, 8, 1),
(8, 7, 2),
(9, 6, 3),
(10, 5, 2),
(11, 4, 1),
(12, 3, 3),
(13, 2, 1);