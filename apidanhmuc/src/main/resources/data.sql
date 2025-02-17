USE api_category;

-- Chèn danh mục sản phẩm
INSERT INTO categories (id, name) VALUES (1, 'Điện thoại'), (2, 'Laptop'), (3, 'Phụ kiện');

-- Chèn sản phẩm
INSERT INTO products (id, name, category_id, price, quantity_in_stock, created_at) VALUES
(1, 'iPhone 15 Pro Max', 1, 35000000, 10, NOW() - INTERVAL 5 DAY),
(2, 'Samsung Galaxy S23', 1, 28000000, 15, NOW() - INTERVAL 10 DAY),
(3, 'MacBook Pro M2', 2, 45000000, 5, NOW() - INTERVAL 3 DAY),
(4, 'Dell XPS 13', 2, 30000000, 8, NOW() - INTERVAL 7 DAY),
(5, 'AirPods Pro 2', 3, 5000000, 20, NOW() - INTERVAL 2 DAY),
(6, 'Chuột Logitech MX Master 3', 3, 2500000, 30, NOW());

-- Chèn đơn hàng
INSERT INTO orders (id, order_date) VALUES
(1, NOW() - INTERVAL 1 DAY),
(2, NOW() - INTERVAL 2 DAY),
(3, NOW() - INTERVAL 3 DAY);

-- Chèn chi tiết đơn hàng (sản phẩm đã bán)
INSERT INTO order_details (order_id, product_id, quantity) VALUES
(1, 1, 2), -- iPhone 15 Pro Max
(1, 3, 1), -- MacBook Pro M2
(2, 2, 3), -- Samsung Galaxy S23
(2, 5, 5), -- AirPods Pro 2
(3, 6, 4); -- Chuột Logitech MX Master 3
