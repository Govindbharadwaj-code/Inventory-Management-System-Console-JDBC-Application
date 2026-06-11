package com.inventory.utility;

public class ConsoleColors {
    public static final String RESET   = "\033[0m";
    public static final String RED     = "\033[0;31m";
    public static final String GREEN   = "\033[0;32m";
    public static final String YELLOW  = "\033[0;33m";
    public static final String BLUE    = "\033[0;34m";
    public static final String PURPLE  = "\033[0;35m";
    public static final String CYAN    = "\033[0;36m";
    public static final String WHITE   = "\033[0;37m";
    public static final String GREEN_BG  = "\033[42m";
    public static final String RED_BG    = "\033[41m";
    public static final String YELLOW_BG = "\033[43m";
    public static final String BOLD    = "\033[1m";

    public static void printBanner(String text) {
        int width = text.length() + 4;
        String border = "+";
        for (int i = 0; i < width; i++) border += "-";
        border += "+";
        System.out.println(PURPLE + border);
        System.out.println("| " + BOLD + text + RESET + PURPLE + " |");
        System.out.println(border + RESET);
    }

    public static void printSuccess(String msg) {
        System.out.println(GREEN_BG + " SUCCESS: " + msg + " " + RESET);
    }

    public static void printError(String msg) {
        System.out.println(RED_BG + " ERROR: " + msg + " " + RESET);
    }

    public static void printInfo(String msg) {
        System.out.println(CYAN + "[INFO] " + msg + RESET);
    }

    public static void printRow(String label, Object value) {
        System.out.printf(YELLOW + "  %-20s" + RESET + ": %s%n", label, value);
    }

    public static void printDivider() {
        System.out.println(BLUE + "  " + "─".repeat(50) + RESET);
    }
}
