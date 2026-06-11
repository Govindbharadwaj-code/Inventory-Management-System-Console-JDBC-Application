package com.inventory.usecases;

import com.inventory.dao.ProductDao;
import com.inventory.dao.ProductDaoImpl;
import com.inventory.exceptions.ProductException;
import com.inventory.utility.ConsoleColors;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteProductUsecase {

    public static void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN + "\n  --- Delete Product ---" + ConsoleColors.RESET);
        try {
            System.out.print(ConsoleColors.YELLOW + "  Enter Product ID to delete : " + ConsoleColors.RESET);
            int id = sc.nextInt(); sc.nextLine();
            System.out.print(ConsoleColors.YELLOW + "  Confirm delete? (yes/no)   : " + ConsoleColors.RESET);
            String confirm = sc.nextLine().trim();
            if (confirm.equalsIgnoreCase("yes")) {
                ProductDao dao = new ProductDaoImpl();
                String result = dao.deleteProduct(id);
                ConsoleColors.printSuccess(result);
            } else {
                ConsoleColors.printInfo("Delete cancelled.");
            }
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Invalid ID entered.");
        } catch (ProductException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }
}
