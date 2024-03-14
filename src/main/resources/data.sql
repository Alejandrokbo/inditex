CREATE TABLE IF NOT EXISTS brand
(
    brand_id    INT PRIMARY KEY AUTO_INCREMENT,
    brand_number INT,
    brand_name  VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product
(
    product_id   INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255),
    brand_id     INT,
    FOREIGN KEY (brand_id) REFERENCES brand (brand_id)
);

CREATE TABLE IF NOT EXISTS price
(
    price_list   INT PRIMARY KEY AUTO_INCREMENT,
    start_date DATETIME,
    end_date   DATETIME,
    product_id INT,
    priority   INT,
    price      DECIMAL(10, 2),
    currency   VARCHAR(3),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

INSERT INTO brand (brand_id, brand_name)
VALUES (1, 'ZARA');

INSERT INTO product (product_id, product_name, brand_id)
VALUES (35455, 'Product 1', 1);

INSERT INTO price (start_date, end_date, product_id, priority, price, currency)
VALUES ('2020-06-14 00:00:00', '2020-12-31 23:59:59', 35455, 0, 35.50, 'EUR'),
       ('2020-06-14 15:00:00', '2020-06-14 18:30:00', 35455, 1, 25.45, 'EUR'),
       ('2020-06-15 00:00:00', '2020-06-15 11:00:00', 35455, 1, 30.50, 'EUR'),
       ('2020-06-15 16:00:00', '2020-12-31 23:59:59', 35455, 1, 38.95, 'EUR');
