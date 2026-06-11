-- =====================================================
--  INVENTORY MANAGEMENT SYSTEM - Database Setup SQL
--  Run this in MySQL before starting the application
-- =====================================================

CREATE DATABASE IF NOT EXISTS inventorydb;
USE inventorydb;

-- -------------------------------------------------------
-- TABLE: supplier
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS supplier (
    supplierId    INT AUTO_INCREMENT PRIMARY KEY,
    supplierName  VARCHAR(100) NOT NULL,
    contactPerson VARCHAR(100),
    phone         VARCHAR(15),
    email         VARCHAR(100),
    city          VARCHAR(50)
);

-- -------------------------------------------------------
-- TABLE: product
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS product (
    productId    INT AUTO_INCREMENT PRIMARY KEY,
    productName  VARCHAR(100) NOT NULL,
    category     VARCHAR(50)  NOT NULL,
    supplier     VARCHAR(100),
    quantity     INT          NOT NULL DEFAULT 0,
    price        DECIMAL(10,2) NOT NULL,
    reorderLevel INT          NOT NULL DEFAULT 10
);

-- -------------------------------------------------------
-- TABLE: stock_transaction
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS stock_transaction (
    transactionId   INT AUTO_INCREMENT PRIMARY KEY,
    productId       INT         NOT NULL,
    transactionType ENUM('IN','OUT') NOT NULL,
    quantity        INT         NOT NULL,
    transactionDate DATE        NOT NULL,
    remarks         VARCHAR(200),
    FOREIGN KEY (productId) REFERENCES product(productId) ON DELETE CASCADE
);

-- -------------------------------------------------------
-- SAMPLE DATA
-- -------------------------------------------------------
INSERT INTO supplier (supplierName, contactPerson, phone, email, city) VALUES
('TechWorld Pvt Ltd',  'Ravi Kumar',   '9876543210', 'ravi@techworld.com',  'Patna'),
('QuickSupply Co.',    'Priya Sharma', '9123456789', 'priya@quicksupply.in','Delhi'),
('GlobalMart',         'Anil Gupta',   '9988776655', 'anil@globalmart.com', 'Mumbai');

INSERT INTO product (productName, category, supplier, quantity, price, reorderLevel) VALUES
('Laptop HP 250',      'Electronics', 'TechWorld Pvt Ltd',  15, 45000.00, 5),
('Mouse Logitech M90', 'Electronics', 'TechWorld Pvt Ltd',  50, 450.00,   10),
('A4 Paper Ream',      'Stationery',  'QuickSupply Co.',    100, 250.00,  20),
('Ballpoint Pen Box',  'Stationery',  'QuickSupply Co.',    200, 120.00,  30),
('USB Hub 4-Port',     'Electronics', 'GlobalMart',         25, 799.00,   8),
('Headset Sony',       'Electronics', 'TechWorld Pvt Ltd',  3,  2500.00,  5),   -- Low stock!
('Sticky Notes Pack',  'Stationery',  'QuickSupply Co.',    8,  80.00,    10);   -- Low stock!

SELECT 'Database setup complete!' AS Status;
