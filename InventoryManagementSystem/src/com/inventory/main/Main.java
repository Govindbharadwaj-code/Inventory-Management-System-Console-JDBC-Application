package com.inventory.main;

import com.inventory.usecases.*;
import com.inventory.utility.ConsoleColors;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // ─────────────────────────────────────────────
    //  MAIN MENU
    // ─────────────────────────────────────────────
    static void showMainMenu() {
        System.out.println();
        ConsoleColors.printBanner("  INVENTORY MANAGEMENT SYSTEM  ");
        System.out.println(ConsoleColors.PURPLE +
            "  +------------------------------+\n" +
            "  | 1. Product Management        |\n" +
            "  | 2. Supplier Management       |\n" +
            "  | 3. Stock Management          |\n" +
            "  | 4. Exit                      |\n" +
            "  +------------------------------+" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "  Enter your choice : " + ConsoleColors.RESET);
        mainMenuChoice();
    }

    static void mainMenuChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Please enter a valid number.");
            showMainMenu(); return;
        }
        switch (choice) {
            case 1: showProductMenu();  break;
            case 2: showSupplierMenu(); break;
            case 3: showStockMenu();    break;
            case 4:
                System.out.println(ConsoleColors.GREEN + "\n  Thank you for using Inventory MS. Goodbye!\n" + ConsoleColors.RESET);
                System.exit(0);
            default:
                ConsoleColors.printError("Invalid option. Choose 1–4.");
                showMainMenu();
        }
    }

    // ─────────────────────────────────────────────
    //  PRODUCT MENU
    // ─────────────────────────────────────────────
    static void showProductMenu() {
        System.out.println();
        System.out.println(ConsoleColors.PURPLE +
            "  +-----------------------------------+\n" +
            "  |       PRODUCT MANAGEMENT          |\n" +
            "  |-----------------------------------||\n" +
            "  | 1. Add New Product                |\n" +
            "  | 2. View All Products              |\n" +
            "  | 3. Search Product by ID           |\n" +
            "  | 4. Search Products by Category    |\n" +
            "  | 5. Update Product (Qty & Price)   |\n" +
            "  | 6. Delete Product                 |\n" +
            "  | 7. Low Stock Alert                |\n" +
            "  | 8. Back to Main Menu              |\n" +
            "  +-----------------------------------+" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "  Enter your choice : " + ConsoleColors.RESET);
        productMenuChoice();
    }

    static void productMenuChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            choice = sc.nextInt(); sc.nextLine();
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Please enter a valid number.");
            showProductMenu(); return;
        }
        switch (choice) {
            case 1: AddProductUsecase.addProduct();          showProductMenu(); break;
            case 2: ViewProductsUsecase.viewAllProducts();   showProductMenu(); break;
            case 3: {
                System.out.print(ConsoleColors.YELLOW + "  Enter Product ID : " + ConsoleColors.RESET);
                try {
                    int id = sc.nextInt();
                    ViewProductsUsecase.searchProduct(id);
                } catch (InputMismatchException e) {
                    ConsoleColors.printError("Invalid ID.");
                }
                showProductMenu(); break;
            }
            case 4: {
                System.out.print(ConsoleColors.YELLOW + "  Enter Category Name : " + ConsoleColors.RESET);
                String cat = sc.nextLine().trim();
                ViewProductsUsecase.viewByCategory(cat);
                showProductMenu(); break;
            }
            case 5: UpdateProductUsecase.updateProduct();    showProductMenu(); break;
            case 6: DeleteProductUsecase.deleteProduct();    showProductMenu(); break;
            case 7: ViewProductsUsecase.viewLowStock();      showProductMenu(); break;
            case 8: showMainMenu(); break;
            default:
                ConsoleColors.printError("Invalid option. Choose 1–8.");
                showProductMenu();
        }
    }

    // ─────────────────────────────────────────────
    //  SUPPLIER MENU
    // ─────────────────────────────────────────────
    static void showSupplierMenu() {
        System.out.println();
        System.out.println(ConsoleColors.PURPLE +
            "  +-----------------------------------+\n" +
            "  |       SUPPLIER MANAGEMENT         |\n" +
            "  |-----------------------------------|\n" +
            "  | 1. Add New Supplier               |\n" +
            "  | 2. View All Suppliers             |\n" +
            "  | 3. Delete Supplier                |\n" +
            "  | 4. Back to Main Menu              |\n" +
            "  +-----------------------------------+" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "  Enter your choice : " + ConsoleColors.RESET);
        supplierMenuChoice();
    }

    static void supplierMenuChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            choice = sc.nextInt(); sc.nextLine();
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Please enter a valid number.");
            showSupplierMenu(); return;
        }
        switch (choice) {
            case 1: SupplierUsecase.addSupplier();       showSupplierMenu(); break;
            case 2: SupplierUsecase.viewAllSuppliers();  showSupplierMenu(); break;
            case 3: SupplierUsecase.deleteSupplier();    showSupplierMenu(); break;
            case 4: showMainMenu(); break;
            default:
                ConsoleColors.printError("Invalid option. Choose 1–4.");
                showSupplierMenu();
        }
    }

    // ─────────────────────────────────────────────
    //  STOCK MENU
    // ─────────────────────────────────────────────
    static void showStockMenu() {
        System.out.println();
        System.out.println(ConsoleColors.PURPLE +
            "  +-----------------------------------+\n" +
            "  |        STOCK MANAGEMENT           |\n" +
            "  |-----------------------------------|\n" +
            "  | 1. Stock IN  (Receive Goods)      |\n" +
            "  | 2. Stock OUT (Issue Goods)        |\n" +
            "  | 3. View Transactions by Product   |\n" +
            "  | 4. View All Transactions          |\n" +
            "  | 5. Back to Main Menu              |\n" +
            "  +-----------------------------------+" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "  Enter your choice : " + ConsoleColors.RESET);
        stockMenuChoice();
    }

    static void stockMenuChoice() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            choice = sc.nextInt(); sc.nextLine();
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Please enter a valid number.");
            showStockMenu(); return;
        }
        switch (choice) {
            case 1: StockUsecase.stockIn();                      showStockMenu(); break;
            case 2: StockUsecase.stockOut();                     showStockMenu(); break;
            case 3: StockUsecase.viewTransactionsByProduct();    showStockMenu(); break;
            case 4: StockUsecase.viewAllTransactions();          showStockMenu(); break;
            case 5: showMainMenu(); break;
            default:
                ConsoleColors.printError("Invalid option. Choose 1–5.");
                showStockMenu();
        }
    }

    // ─────────────────────────────────────────────
    //  ENTRY POINT
    // ─────────────────────────────────────────────
    public static void main(String[] args) {
        showMainMenu();
    }
}
