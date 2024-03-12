CREATE TABLE IF NOT EXISTS Brand
(
    brandId   INT PRIMARY KEY AUTO_INCREMENT,
    brandName VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Product
(
    productId   INT PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(255),
    brandId     INT,
    FOREIGN KEY (brandId) REFERENCES Brand (brandId)
);

CREATE TABLE IF NOT EXISTS Price
(
    priceId   INT PRIMARY KEY AUTO_INCREMENT,
    startDate DATETIME,
    endDate   DATETIME,
    productId INT,
    priority  INT,
    price     DECIMAL(10, 2),
    currency  VARCHAR(3),
    FOREIGN KEY (productId) REFERENCES Product (productId)
);

INSERT INTO Brand (brandId, brandName)
VALUES (1, 'ZARA');

INSERT INTO Product (productId, productName, brandId)
VALUES (35455, 'Product 1', 1);

INSERT INTO Price (startDate, endDate, productId, priority, price, currency)
VALUES ('2020-06-14 00:00:00', '2020-12-31 23:59:59', 35455, 0, 35.50, 'EUR'),
       ('2020-06-14 15:00:00', '2020-06-14 18:30:00', 35455, 1, 25.45, 'EUR'),
       ('2020-06-15 00:00:00', '2020-06-15 11:00:00', 35455, 1, 30.50, 'EUR'),
       ('2020-06-15 16:00:00', '2020-12-31 23:59:59', 35455, 1, 38.95, 'EUR');
