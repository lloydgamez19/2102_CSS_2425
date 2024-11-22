package myproject_catering.customer;

import java.util.Scanner;
import myproject_catering.customer.OrderSystem.OrderService;

public class CustomerSystem {
    private static void customerDashboard(Scanner scanner, Customer loggedInCustomer, OrderService orderService) {
        boolean logout = false;

        while (!logout) {
            try {
                System.out.println("\nCustomer Dashboard:");
                System.out.println("1. View Services");
                System.out.println("2. Book ");
                System.out.println("3. View ");
                System.out.println("4. Checkout");
                System.out.println("5. Track Orders");
                System.out.println("6. Cancel Book");
                System.out.println("7. Logout");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1 -> orderService.displayMenu(); 
                    case 2 -> orderService.addToCart(scanner, loggedInCustomer);
                    case 3 -> orderService.viewCart(loggedInCustomer);
                    case 4 -> orderService.checkout(scanner, loggedInCustomer);
                    case 5 -> orderService.trackOrders(loggedInCustomer);
                    case 6 -> {
                        System.out.print("Enter Order ID to Cancel: ");
                        int orderId = Integer.parseInt(scanner.nextLine().trim());
                        orderService.cancelOrder(orderId, loggedInCustomer);
                    }
                    case 7 -> logout = true;
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
