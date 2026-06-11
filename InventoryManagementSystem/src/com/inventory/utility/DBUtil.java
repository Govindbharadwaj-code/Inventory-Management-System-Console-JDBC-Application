package com.inventory.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL      = "jdbc:mysql://localhost:3306/inventorydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection provideConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(ConsoleColors.RED + "[ERROR] MySQL Driver not found: " + e.getMessage() + ConsoleColors.RESET);
        }
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "[ERROR] DB Connection failed: " + e.getMessage() + ConsoleColors.RESET);
        }
        return conn;
    }
}
