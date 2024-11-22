package myproject_catering;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import myproject_catering.customer.Customer;
import myproject_catering.customer.OrderSystem.OrderService;

public class MyProject_CATERING {
    private static final List<Customer> customers = new ArrayList<>();
    private static Admin admin = new Admin(); 
    private static OrderService orderService = new OrderService(); 
    private static int customerIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the Catering System.");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("\nChoose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> signUp(scanner);
                case 2 -> signIn(scanner);
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void signUp(Scanner scanner) {
        System.out.println("Are you registering as:");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.print("Choose an option: ");

        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (roleChoice) {
            case 1 -> {
                System.out.print("Enter Admin username: ");
                String username = scanner.nextLine();
                System.out.print("Enter Admin password: ");
                String password = scanner.nextLine();

                // Set up admin credentials (overwrite existing for simplicity)
                admin = new Admin(username, password);
                System.out.println("\nAdmin registration successful!");
            }
            case 2 -> {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                System.out.print("Enter phone: ");
                String phone = scanner.nextLine();
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                Customer customer = new Customer(customerIdCounter++, name, address, phone, username, password);
                customers.add(customer);
                System.out.println("\nCustomer registration successful! You can now sign in.");
            }
            default -> System.out.println("Invalid choice! Returning to main menu.");
        }
    }

    private static void signIn(Scanner scanner) {
        System.out.println("Are you signing in as:");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.print("Choose an option: ");

        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (roleChoice) {
            case 1 -> signInAsAdmin(scanner);
            case 2 -> signInAsCustomer(scanner);
            default -> System.out.println("Invalid choice! Returning to main menu.");
        }
    }

    private static void signInAsAdmin(Scanner scanner) {
        System.out.print("Enter Admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin password: ");
        String password = scanner.nextLine();

        // Validate admin credentials
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            System.out.println("Welcome, Admin!");
            showAdminMenu(scanner);
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void signInAsCustomer(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Customer customer = customers.stream()
                .filter(c -> c.getUsername().equals(username) && c.checkPassword(password))
                .findFirst().orElse(null);

        if (customer == null) {
            System.out.println("Invalid customer credentials.");
        } else {
            System.out.println("Welcome, " + customer.getName() + "!");
            showCustomerDashboard(scanner, customer);
        }
    }

    private static void showAdminMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Book Management");
            System.out.println("2. Order Management");
            System.out.println("3. Logout");
            System.out.print("\nChoose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> admin.showMenuManagement(scanner); // Manage menu via Admin class
                case 2 -> admin.showOrderManagement(scanner, orderService.getOrders());
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void showCustomerDashboard(Scanner scanner, Customer customer) {
        boolean logout = false;

        while (!logout) {
            System.out.println("\nCustomer Dashboard:");
            System.out.println("1. View Services");
            System.out.println("2. Add Service");
            System.out.println("3. View added service");
            System.out.println("4. Checkout");
            System.out.println("5. Track ");
            System.out.println("6. Logout");
            System.out.print("\nChoose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> orderService.displayMenu();
                case 2 -> orderService.addToCart(scanner, customer);
                case 3 -> orderService.viewCart(customer);
                case 4 -> orderService.checkout(scanner, customer);
                case 5 -> orderService.trackOrders(customer);
                case 6 -> {
                    System.out.println("Goodbye, " + customer.getName() + "!");
                    logout = true;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
