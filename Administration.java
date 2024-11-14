package com.mycompany.catering_system_services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Administration {

    public static void showAdminHomePage() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Admin Home Page");
            System.out.println("1. View All Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. Delete Order");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            //ALL FUNCTIONS HERE
            switch (choice) {
                case 1:
                    viewAllOrders();
                    break;
                case 2:
                    updateOrderStatus();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice... Please try again!");
                    break;
            }
        }
    }

public static void viewAllOrders() {
    try {
        List<Order> orders = OrderService.viewAllOrders();
        System.out.println("***********************************");
        System.out.println("           ORDER LIST              ");
        System.out.println("***********************************");
        for (Order order : orders) {
            System.out.println("===================================");
            System.out.println("Order ID:        " + order.id);
            System.out.println("Customer:        " + order.customerName);
            System.out.println("Items:           " + order.items);
            System.out.printf("Total:           $%.2f\n", order.total);
            System.out.println("Status:          " + order.status);
            System.out.println("Ordered At:      " + order.orderTime);
            System.out.println("Last Updated:    " + order.updateTime);
            System.out.println("===================================");
        }
    } catch (SQLException e) {
        System.out.println("An error occurred while retrieving orders. Please try again.");
        e.printStackTrace(); // Optionally log the stack trace for debugging
    }
}


    public static void updateOrderStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new status: ");
        String status = scanner.nextLine();

        try {
            OrderService.updateOrderStatus(orderId, status);
            System.out.println();
            System.out.println("Order status updated successfully.");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Order ID to delete: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            OrderService.deleteOrder(orderId);
            System.out.println();
            System.out.println("Order deleted successfully.");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Show home page demo
        showAdminHomePage();
    }
}
