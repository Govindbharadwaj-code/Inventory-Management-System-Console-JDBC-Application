Inventory Management System — Console JDBC Application
A Java console application built using JDBC + MySQL following the same layered architecture pattern as the Bus Ticket Reservation System.

Project Structure
InventoryManagementSystem/
├── database_setup.sql                  ← Run this first in MySQL
├── README.md
└── src/
    └── com/inventory/
        ├── bean/
        │   ├── Product.java            ← Product entity (POJO)
        │   ├── Supplier.java           ← Supplier entity (POJO)
        │   └── StockTransaction.java   ← Transaction entity (POJO)
        ├── dao/
        │   ├── ProductDao.java         ← Interface for product operations
        │   ├── ProductDaoImpl.java     ← JDBC implementation
        │   ├── SupplierDao.java        ← Interface for supplier operations
        │   ├── SupplierDaoImpl.java    ← JDBC implementation
        │   ├── StockDao.java           ← Interface for stock operations
        │   └── StockDaoImpl.java       ← JDBC implementation
        ├── exceptions/
        │   ├── ProductException.java   ← Custom product exception
        │   └── InventoryException.java ← General inventory exception
        ├── usecases/
        │   ├── AddProductUsecase.java
        │   ├── UpdateProductUsecase.java
        │   ├── DeleteProductUsecase.java
        │   ├── ViewProductsUsecase.java
        │   ├── SupplierUsecase.java
        │   └── StockUsecase.java
        ├── utility/
        │   ├── DBUtil.java             ← JDBC connection provider
        │   └── ConsoleColors.java      ← ANSI color helpers
        └── main/
            └── Main.java               ← Entry point + all menus
Database Setup
Open MySQL Workbench or MySQL CLI
Run the SQL file:
SOURCE /path/to/database_setup.sql;
This creates database inventorydb with 3 tables + sample data
How to Run (Eclipse or IntelliJ)
Import as a Java Project
Add mysql-connector-j-8.x.x.jar to the build path
In DBUtil.java, update credentials if needed:
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
Run Main.java
Features
Product Management
Add new product (name, category, supplier, qty, price, reorder level)
View all products in a formatted table
Search product by ID — shows full details + stock status
Search products by category
Update quantity and price
Delete product (with confirmation prompt)
Low Stock Alert — highlights products at or below reorder level in RED
Supplier Management
Add new supplier
View all suppliers in a formatted table
Delete supplier
Stock Management
Stock IN — receive goods, updates product quantity + logs transaction
Stock OUT — issue goods, validates available stock before deducting
View transaction history by product ID
View all stock transactions (color-coded IN=green, OUT=red)
Architecture Pattern (same as Bus Ticket System)
Main.java (menus + navigation)
    └─> Usecases (input + orchestration)
            └─> DaoImpl (SQL + JDBC)
                    └─> DBUtil (connection)
                    └─> Bean (data transfer)
SQL Queries Used
-- Add product
INSERT INTO product(productName, category, supplier, quantity, price, reorderLevel)
VALUES (?,?,?,?,?,?);

-- Update product
UPDATE product SET quantity=?, price=? WHERE productId=?;

-- Get low stock
SELECT * FROM product WHERE quantity <= reorderLevel ORDER BY quantity;

-- Stock IN
INSERT INTO stock_transaction(productId, transactionType, quantity, transactionDate, remarks)
VALUES (?, 'IN', ?, CURDATE(), ?);
UPDATE product SET quantity = quantity + ? WHERE productId = ?;

-- Stock OUT (with availability check)
SELECT quantity FROM product WHERE productId=?;
INSERT INTO stock_transaction ... VALUES (?, 'OUT', ?, CURDATE(), ?);
UPDATE product SET quantity = quantity - ? WHERE productId = ?;
