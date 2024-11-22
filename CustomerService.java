package myproject_catering.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
    private static List<Customer> customers = new ArrayList<>();

    public void signUp(Scanner scanner) {
        System.out.println("\n--- Sign Up ---");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();
        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        int newId = customers.size() + 1;
        Customer newCustomer = new Customer(newId, name, address, phone, username, password);
        customers.add(newCustomer);

        System.out.println("Sign-up successful! You can now log in.");
    }

    public Customer login(Scanner scanner) {
        System.out.println("\n--- Login ---");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Customer customer = customers.stream()
                .filter(c -> c.getUsername().equals(username) && c.checkPassword(password))
                .findFirst()
                .orElse(null);

        if (customer != null) {
            System.out.println("Welcome back, " + customer.getName() + "!");
            return customer;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return null;
        }
    }

    public static void preloadCustomers() {
        customers.add(new Customer(1, "John Doe", "123 Main St", "1234567890", "john123", "pass123"));
        customers.add(new Customer(2, "Jane Smith", "456 Elm St", "0987654321", "jane456", "pass456"));
    }
}

