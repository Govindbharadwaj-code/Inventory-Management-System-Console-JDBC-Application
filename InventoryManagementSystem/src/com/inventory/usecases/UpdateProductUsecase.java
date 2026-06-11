package com.inventory.usecases;

import com.inventory.dao.ProductDao;
import com.inventory.dao.ProductDaoImpl;
import com.inventory.exceptions.ProductException;
import com.inventory.utility.ConsoleColors;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateProductUsecase {

    public static void updateProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN + "\n  --- Update Product Quantity & Price ---" + ConsoleColors.RESET);
        try {
            System.out.print(ConsoleColors.YELLOW + "  Enter Product ID : " + ConsoleColors.RESET);
            int id = sc.nextInt();
            System.out.print(ConsoleColors.YELLOW + "  New Quantity     : " + ConsoleColors.RESET);
            int qty = sc.nextInt();
            System.out.print(ConsoleColors.YELLOW + "  New Price (Rs.)  : " + ConsoleColors.RESET);
            double price = sc.nextDouble();
            sc.nextLine();

            ProductDao dao = new ProductDaoImpl();
            String result = dao.updateProduct(id, qty, price);
            ConsoleColors.printSuccess(result);
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Invalid input. Please enter numeric values.");
        } catch (ProductException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }
}
