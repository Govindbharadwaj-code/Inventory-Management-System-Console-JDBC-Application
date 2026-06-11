package com.inventory.usecases;

import com.inventory.bean.StockTransaction;
import com.inventory.dao.StockDao;
import com.inventory.dao.StockDaoImpl;
import com.inventory.exceptions.InventoryException;
import com.inventory.utility.ConsoleColors;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StockUsecase {

    public static void stockIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN + "\n  --- Stock IN (Receive Goods) ---" + ConsoleColors.RESET);
        try {
            System.out.print(ConsoleColors.YELLOW + "  Product ID      : " + ConsoleColors.RESET);
            int id = sc.nextInt(); sc.nextLine();
            System.out.print(ConsoleColors.YELLOW + "  Quantity to Add : " + ConsoleColors.RESET);
            int qty = sc.nextInt(); sc.nextLine();
            System.out.print(ConsoleColors.YELLOW + "  Remarks         : " + ConsoleColors.RESET);
            String remarks = sc.nextLine().trim();

            StockDao dao = new StockDaoImpl();
            String result = dao.stockIn(id, qty, remarks);
            ConsoleColors.printSuccess(result);
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Invalid input. Numeric values required.");
        } catch (InventoryException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    public static void stockOut() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN + "\n  --- Stock OUT (Issue Goods) ---" + ConsoleColors.RESET);
        try {
            System.out.print(ConsoleColors.YELLOW + "  Product ID          : " + ConsoleColors.RESET);
            int id = sc.nextInt(); sc.nextLine();
            System.out.print(ConsoleColors.YELLOW + "  Quantity to Remove  : " + ConsoleColors.RESET);
            int qty = sc.nextInt(); sc.nextLine();
            System.out.print(ConsoleColors.YELLOW + "  Remarks             : " + ConsoleColors.RESET);
            String remarks = sc.nextLine().trim();

            StockDao dao = new StockDaoImpl();
            String result = dao.stockOut(id, qty, remarks);
            ConsoleColors.printSuccess(result);
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Invalid input. Numeric values required.");
        } catch (InventoryException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    public static void viewTransactionsByProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN + "\n  --- Transaction History by Product ---" + ConsoleColors.RESET);
        try {
            System.out.print(ConsoleColors.YELLOW + "  Enter Product ID : " + ConsoleColors.RESET);
            int id = sc.nextInt(); sc.nextLine();
            StockDao dao = new StockDaoImpl();
            List<StockTransaction> list = dao.getTransactionsByProduct(id);
            if (list.isEmpty()) { ConsoleColors.printInfo("No transactions found for Product ID " + id); return; }
            printTransactionTable(list);
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Invalid ID.");
        } catch (InventoryException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    public static void viewAllTransactions() {
        System.out.println(ConsoleColors.CYAN + "\n  --- All Stock Transactions ---" + ConsoleColors.RESET);
        try {
            StockDao dao = new StockDaoImpl();
            List<StockTransaction> list = dao.getAllTransactions();
            if (list.isEmpty()) { ConsoleColors.printInfo("No transactions recorded yet."); return; }
            printTransactionTable(list);
        } catch (InventoryException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    private static void printTransactionTable(List<StockTransaction> list) {
        System.out.println(ConsoleColors.BLUE);
        System.out.printf("  %-6s  %-10s  %-8s  %-8s  %-12s  %-30s%n",
            "TxnID", "ProductID", "Type", "Qty", "Date", "Remarks");
        System.out.println("  " + "─".repeat(80));
        System.out.print(ConsoleColors.RESET);
        for (StockTransaction t : list) {
            String typeColor = t.getTransactionType().equals("IN") ? ConsoleColors.GREEN : ConsoleColors.RED;
            System.out.printf("  %-6d  %-10d  " + typeColor + "%-8s" + ConsoleColors.RESET + "  %-8d  %-12s  %-30s%n",
                t.getTransactionId(), t.getProductId(), t.getTransactionType(),
                t.getQuantity(), t.getTransactionDate(), t.getRemarks());
        }
        System.out.println(ConsoleColors.BLUE + "  " + "─".repeat(80) + ConsoleColors.RESET);
        ConsoleColors.printInfo("Total: " + list.size() + " transaction(s)");
    }
}
