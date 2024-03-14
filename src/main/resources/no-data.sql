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
