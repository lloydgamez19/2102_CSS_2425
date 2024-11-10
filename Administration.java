package com.mycompany.catering_system_services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Administration {

    // Database connection method
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/CATERING";
        String username = "root";
        String password = "Dikoalam.com3";
        return DriverManager.getConnection(url, username, password);
    }

    // Register a new user
    public static boolean registerUser(String username, String password) throws SQLException {
        Connection connection = getConnection();
        // Check if username already exists
        String checkQuery = "SELECT * FROM users WHERE username = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
        checkStmt.setString(1, username);
        ResultSet checkRs = checkStmt.executeQuery();

        if (checkRs.next()) {
            // Username already exists
            return false;
        } else {
            // Register the new user
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.executeUpdate();
            connection.close();
            return true;
        }
    }

    // Validate user login
    public static boolean validateUser(String username, String password) throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        boolean result = rs.next();
        connection.close();
        return result;
    }

    // Home page functionality
    public static void showHomePage() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Home Page");
            System.out.println("1. View Orders");
            System.out.println("2. Manage Menu");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewOrders();
                    break;
                case 2:
                    System.out.println("Managing Menu...");
                    // Add functionality for managing menu here
                    break;
                case 3:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Function to view orders
    public static void viewOrders() {
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM ordertable"; // Update to use the correct table name 'ordertable'
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            System.out.println("Customer Orders:");
            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                String customerName = rs.getString("customerName");
                String items = rs.getString("items");
                double total = rs.getDouble("total");
                System.out.println("Order ID: " + orderId + ", Customer: " + customerName + ", Items: " + items + ", Total: " + total);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Catering System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                if (choice == 1) {
                    // Registration
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    boolean registered = registerUser(username, password);
                    if (registered) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("User already exists!");
                    }
                } else if (choice == 2) {
                    // Login
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    boolean loggedIn = validateUser(username, password);
                    if (loggedIn) {
                        System.out.println("Login successful!");
                        // Redirect to home page
                        showHomePage();
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                } else if (choice == 3) {
                    // Exit
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
