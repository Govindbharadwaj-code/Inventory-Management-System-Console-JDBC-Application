package com.inventory.usecases;

import com.inventory.bean.Product;
import com.inventory.dao.ProductDao;
import com.inventory.dao.ProductDaoImpl;
import com.inventory.exceptions.ProductException;
import com.inventory.utility.ConsoleColors;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddProductUsecase {

    public static void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN + "\n  --- Add New Product ---" + ConsoleColors.RESET);
        try {
            System.out.print(ConsoleColors.YELLOW + "  Product Name   : " + ConsoleColors.RESET);
            String name = sc.nextLine().trim();
            System.out.print(ConsoleColors.YELLOW + "  Category       : " + ConsoleColors.RESET);
            String category = sc.nextLine().trim();
            System.out.print(ConsoleColors.YELLOW + "  Supplier Name  : " + ConsoleColors.RESET);
            String supplier = sc.nextLine().trim();
            System.out.print(ConsoleColors.YELLOW + "  Initial Qty    : " + ConsoleColors.RESET);
            int qty = sc.nextInt();
            System.out.print(ConsoleColors.YELLOW + "  Price (Rs.)    : " + ConsoleColors.RESET);
            double price = sc.nextDouble();
            System.out.print(ConsoleColors.YELLOW + "  Reorder Level  : " + ConsoleColors.RESET);
            int reorder = sc.nextInt();
            sc.nextLine(); // consume newline

            Product p = new Product(0, name, category, supplier, qty, price, reorder);
            ProductDao dao = new ProductDaoImpl();
            String result = dao.addProduct(p);
            ConsoleColors.printSuccess(result);
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Invalid input type. Please enter correct values.");
        } catch (ProductException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }
}
