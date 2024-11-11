package com.mycompany.catering_system_services;

public class Order {
    int id;
    String customerName;
    String items;
    double total;
    String status;

    public Order(int id, String customerName, String items, double total, String status) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.total = total;
        this.status = status;
    }

    // Getters and setters (if needed)
}
