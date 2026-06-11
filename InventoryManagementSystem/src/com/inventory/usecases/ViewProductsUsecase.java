package com.inventory.usecases;

import com.inventory.bean.Product;
import com.inventory.dao.ProductDao;
import com.inventory.dao.ProductDaoImpl;
import com.inventory.exceptions.ProductException;
import com.inventory.utility.ConsoleColors;
import java.util.List;

public class ViewProductsUsecase {

    public static void viewAllProducts() {
        System.out.println(ConsoleColors.CYAN + "\n  --- All Products ---" + ConsoleColors.RESET);
        try {
            ProductDao dao = new ProductDaoImpl();
            List<Product> products = dao.getAllProducts();
            if (products.isEmpty()) {
                ConsoleColors.printInfo("No products found in inventory.");
                return;
            }
            printHeader();
            for (Product p : products) printProductRow(p);
            printFooter(products.size());
        } catch (ProductException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    public static void viewLowStock() {
        System.out.println(ConsoleColors.CYAN + "\n  --- Low Stock Alert ---" + ConsoleColors.RESET);
        try {
            ProductDao dao = new ProductDaoImpl();
            List<Product> products = dao.getLowStockProducts();
            if (products.isEmpty()) {
                ConsoleColors.printSuccess("All products are above reorder level. No alerts!");
                return;
            }
            System.out.println(ConsoleColors.RED + "  ⚠ " + products.size() + " product(s) need restocking!" + ConsoleColors.RESET);
            printHeader();
            for (Product p : products) printProductRow(p);
            printFooter(products.size());
        } catch (ProductException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    public static void viewByCategory(String category) {
        System.out.println(ConsoleColors.CYAN + "\n  --- Products in Category: " + category + " ---" + ConsoleColors.RESET);
        try {
            ProductDao dao = new ProductDaoImpl();
            List<Product> products = dao.getProductsByCategory(category);
            if (products.isEmpty()) {
                ConsoleColors.printInfo("No products found in category '" + category + "'.");
                return;
            }
            printHeader();
            for (Product p : products) printProductRow(p);
            printFooter(products.size());
        } catch (ProductException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    public static void searchProduct(int id) {
        try {
            ProductDao dao = new ProductDaoImpl();
            Product p = dao.getProductById(id);
            System.out.println();
            ConsoleColors.printDivider();
            ConsoleColors.printRow("Product ID",   p.getProductId());
            ConsoleColors.printRow("Name",         p.getProductName());
            ConsoleColors.printRow("Category",     p.getCategory());
            ConsoleColors.printRow("Supplier",     p.getSupplier());
            ConsoleColors.printRow("Quantity",     p.getQuantity());
            ConsoleColors.printRow("Price (Rs.)",  String.format("%.2f", p.getPrice()));
            ConsoleColors.printRow("Reorder Level",p.getReorderLevel());
            String status = p.getQuantity() <= p.getReorderLevel()
                ? ConsoleColors.RED + "LOW STOCK!" + ConsoleColors.RESET
                : ConsoleColors.GREEN + "OK" + ConsoleColors.RESET;
            ConsoleColors.printRow("Stock Status", status);
            ConsoleColors.printDivider();
        } catch (ProductException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    private static void printHeader() {
        System.out.println(ConsoleColors.BLUE);
        System.out.printf("  %-5s  %-22s  %-14s  %-15s  %6s  %10s  %8s%n",
            "ID", "Product Name", "Category", "Supplier", "Qty", "Price(Rs.)", "Reorder");
        System.out.println("  " + "─".repeat(90));
        System.out.print(ConsoleColors.RESET);
    }

    private static void printProductRow(Product p) {
        String qtyColor = p.getQuantity() <= p.getReorderLevel() ? ConsoleColors.RED : ConsoleColors.GREEN;
        System.out.printf("  %-5d  %-22s  %-14s  %-15s  " + qtyColor + "%6d" + ConsoleColors.RESET + "  %10.2f  %8d%n",
            p.getProductId(), p.getProductName(), p.getCategory(),
            p.getSupplier(), p.getQuantity(), p.getPrice(), p.getReorderLevel());
    }

    private static void printFooter(int count) {
        System.out.println(ConsoleColors.BLUE + "  " + "─".repeat(90) + ConsoleColors.RESET);
        ConsoleColors.printInfo("Total records: " + count);
    }
}
