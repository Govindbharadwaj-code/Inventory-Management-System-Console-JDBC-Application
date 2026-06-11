package com.inventory.usecases;

import com.inventory.bean.Supplier;
import com.inventory.dao.SupplierDao;
import com.inventory.dao.SupplierDaoImpl;
import com.inventory.exceptions.InventoryException;
import com.inventory.utility.ConsoleColors;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SupplierUsecase {

    public static void addSupplier() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN + "\n  --- Add New Supplier ---" + ConsoleColors.RESET);
        try {
            System.out.print(ConsoleColors.YELLOW + "  Supplier Name   : " + ConsoleColors.RESET);
            String name = sc.nextLine().trim();
            System.out.print(ConsoleColors.YELLOW + "  Contact Person  : " + ConsoleColors.RESET);
            String contact = sc.nextLine().trim();
            System.out.print(ConsoleColors.YELLOW + "  Phone           : " + ConsoleColors.RESET);
            String phone = sc.nextLine().trim();
            System.out.print(ConsoleColors.YELLOW + "  Email           : " + ConsoleColors.RESET);
            String email = sc.nextLine().trim();
            System.out.print(ConsoleColors.YELLOW + "  City            : " + ConsoleColors.RESET);
            String city = sc.nextLine().trim();

            SupplierDao dao = new SupplierDaoImpl();
            Supplier s = new Supplier(0, name, contact, phone, email, city);
            String result = dao.addSupplier(s);
            ConsoleColors.printSuccess(result);
        } catch (InventoryException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    public static void viewAllSuppliers() {
        System.out.println(ConsoleColors.CYAN + "\n  --- All Suppliers ---" + ConsoleColors.RESET);
        try {
            SupplierDao dao = new SupplierDaoImpl();
            List<Supplier> list = dao.getAllSuppliers();
            if (list.isEmpty()) { ConsoleColors.printInfo("No suppliers found."); return; }
            System.out.println(ConsoleColors.BLUE);
            System.out.printf("  %-5s  %-20s  %-18s  %-13s  %-25s  %-12s%n",
                "ID", "Supplier Name", "Contact Person", "Phone", "Email", "City");
            System.out.println("  " + "─".repeat(100));
            System.out.print(ConsoleColors.RESET);
            for (Supplier s : list) {
                System.out.printf("  %-5d  %-20s  %-18s  %-13s  %-25s  %-12s%n",
                    s.getSupplierId(), s.getSupplierName(), s.getContactPerson(),
                    s.getPhone(), s.getEmail(), s.getCity());
            }
            System.out.println(ConsoleColors.BLUE + "  " + "─".repeat(100) + ConsoleColors.RESET);
            ConsoleColors.printInfo("Total: " + list.size() + " supplier(s)");
        } catch (InventoryException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }

    public static void deleteSupplier() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.CYAN + "\n  --- Delete Supplier ---" + ConsoleColors.RESET);
        try {
            System.out.print(ConsoleColors.YELLOW + "  Enter Supplier ID to delete : " + ConsoleColors.RESET);
            int id = sc.nextInt(); sc.nextLine();
            SupplierDao dao = new SupplierDaoImpl();
            String result = dao.deleteSupplier(id);
            ConsoleColors.printSuccess(result);
        } catch (InputMismatchException e) {
            ConsoleColors.printError("Invalid ID.");
        } catch (InventoryException e) {
            ConsoleColors.printError(e.getMessage());
        }
    }
}
