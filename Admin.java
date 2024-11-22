package myproject_catering;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import myproject_catering.customer.MenuItem;
import myproject_catering.customer.OrderSystem.OrderService;

public class Admin {

    private String username;
    private String password;
    private static final List<MenuItem> menuItems = new ArrayList<>();

    static {
        // Initialize menu items with data from OrderService
        menuItems.addAll(OrderService.getMenu());
    }

    public Admin() {}

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<MenuItem> getMenu() {
        return menuItems;
    }

    public void showMenuManagement(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu Management:");
            System.out.println("1. Add new service");
            System.out.println("2. Remove service");
            System.out.println("3. View All Menu");
            System.out.println("4. Back to Admin Menu");
            System.out.print("\nChoose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addMenuItem(scanner);
                case 2 -> removeMenuItem(scanner);
                case 3 -> viewAllMenuItems();
                case 4 -> exit = true;
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void addMenuItem(Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
        System.out.print("Enter item price: ");

        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid price. Please enter a numeric value.");
            scanner.nextLine(); 
            return;
        }

        double price = scanner.nextDouble();
        scanner.nextLine(); 

        int id = menuItems.size() + 1;
        MenuItem menuItem = new MenuItem(id, name, description, price);
        menuItems.add(menuItem);

        OrderService.setMenu(menuItems); 
        System.out.println("Menu item added successfully!");
    }

    private void removeMenuItem(Scanner scanner) {
        System.out.print("Enter item ID to remove: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid numeric ID.");
            scanner.nextLine(); 
            return;
        }

        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        MenuItem menuItem = menuItems.stream().filter(item -> item.getId() == id).findFirst().orElse(null);

        if (menuItem != null) {
            menuItems.remove(menuItem);
            OrderService.setMenu(menuItems); // Update the shared menu in OrderService
            System.out.println("Menu item removed successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    private void viewAllMenuItems() {
        System.out.println("\n--- Menu Service ---");
        if (menuItems.isEmpty()) {
            System.out.println("The menu is currently empty.");
        } else {
            for (MenuItem item : menuItems) {
                System.out.println(item);
            }
        }
    }

    public void showOrderManagement(Scanner scanner, List<Order> orders) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nBook Management:");
            System.out.println("1. View all Books");
            System.out.println("2. Mark Book as Completed");
            System.out.println("3. Back to Admin Menu");
            System.out.print("\nChoose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAllOrders(orders);
                case 2 -> markOrderAsCompleted(scanner, orders);
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void viewAllOrders(List<Order> orders) {
        System.out.println("\n--- All Books ---");
        if (orders.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    private void markOrderAsCompleted(Scanner scanner, List<Order> orders) {
        System.out.print("Enter Order ID to mark as completed: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid numeric Order ID.");
            scanner.nextLine(); 
            return;
        }

        int orderId = scanner.nextInt();
        scanner.nextLine(); 

        Order order = orders.stream().filter(o -> o.getOrderId() == orderId).findFirst().orElse(null);

        if (order != null) {
            order.setStatus("Completed");
            System.out.println("Your book marked as completed.");
        } else {
            System.out.println("Order not found.");
        }
    }
}
