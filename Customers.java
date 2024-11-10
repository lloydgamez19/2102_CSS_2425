package com.mycompany.catering_system_services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Customers {

    // Database connection method
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/CATERING";
        String username = "root";
        String password = "Dikoalam.com3";
        return DriverManager.getConnection(url, username, password);
    }

    // Register a new customer
    public static boolean registerCustomer(String username, String password) throws SQLException {
        Connection connection = getConnection();
        String checkQuery = "SELECT * FROM users WHERE username = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
        checkStmt.setString(1, username);
        ResultSet checkRs = checkStmt.executeQuery();

        if (checkRs.next()) {
            return false;
        } else {
            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, 'customer')";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.executeUpdate();
            connection.close();
            return true;
        }
    }

    // Validate customer login
    public static boolean validateCustomer(String username, String password) throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = 'customer'";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        boolean result = rs.next();
        connection.close();
        return result;
    }

    // Home page for customers
    public static void showCustomerHomePage() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        List<String> cart = new ArrayList<>();

        while (!exit) {
            System.out.println("Customer Home Page");
            System.out.println("1. View MenuItems");
            System.out.println("2. View Cart");
            System.out.println("3. View Orders");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMenuItems(cart);
                    break;
                case 2:
                    viewCart(cart);
                    break;
                case 3:
                    viewOrders();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Function to view menu items and add to cart
    public static void viewMenuItems(List<String> cart) {
        Scanner scanner = new Scanner(System.in);
        String[] menuItems = {"Pizza", "Burger", "Pasta", "Sushi", "Steak"};
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu Items");
            for (int i = 0; i < menuItems.length; i++) {
                System.out.println((i + 1) + ". " + menuItems[i]);
            }
            System.out.println((menuItems.length + 1) + ". Go back to Home Page");
            System.out.print("Choose an item to add to cart: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= menuItems.length) {
                cart.add(menuItems[choice - 1]);
                System.out.println(menuItems[choice - 1] + " added to cart.");
            } else if (choice == menuItems.length + 1) {
                exit = true;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Function to view cart
    public static void viewCart(List<String> cart) {
        System.out.println("Your Cart:");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (String item : cart) {
                System.out.println("- " + item);
            }
        }
    }

    // Function to view orders
    public static void viewOrders() {
        System.out.println("Your Orders:");
        // Add functionality for viewing customer's own orders here
        // This is a placeholder for now.
        System.out.println("Order 1: Pizza, Burger");
        System.out.println("Order 2: Sushi, Pasta");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Customer System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                if (choice == 1) {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    boolean registered = registerCustomer(username, password);
                    if (registered) {
                        System.out.println("Customer registered successfully!");
                    } else {
                        System.out.println("Customer already exists!");
                    }
                } else if (choice == 2) {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    boolean loggedIn = validateCustomer(username, password);
                    if (loggedIn) {
                        System.out.println("Login successful!");
                        showCustomerHomePage();
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                } else if (choice == 3) {
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}