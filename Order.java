package com.mycompany.catering_system_services;

import java.sql.Timestamp;

public class Order {
    int id;
    String customerName;
    String items;
    double total;
    String status;
    Timestamp orderTime;
    Timestamp updateTime;

    public Order(int id, String customerName, String items, double total, String status) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.total = total;
        this.status = status;
    }

    public Order(int id, String customerName, String items, double total, String status, Timestamp orderTime, Timestamp updateTime) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.total = total;
        this.status = status;
        this.orderTime = orderTime;
        this.updateTime = updateTime;
    }
}
