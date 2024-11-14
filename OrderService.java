package com.mycompany.catering_system_services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    // Database connection method
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/CATERING";
        String username = "root";
        String password = "Dikoalam.com3";
        return DriverManager.getConnection(url, username, password);
    }

    // Method to view all orders....
    public static List<Order> viewAllOrders() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM orders";
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        List<Order> orders = new ArrayList<>();

        while (rs.next()) {
            Timestamp orderTime = rs.getTimestamp("order_time");
            Timestamp updateTime = rs.getTimestamp("update_time");

            // Handle NULL timestamps
            if (orderTime == null) {
                orderTime = new Timestamp(System.currentTimeMillis());
            }
            if (updateTime == null) {
                updateTime = new Timestamp(System.currentTimeMillis());
            }

            Order order = new Order(
                rs.getInt("id"),
                rs.getString("customerName"),
                rs.getString("items"),
                rs.getDouble("total"),
                rs.getString("status"),
                orderTime,
                updateTime
            );
            orders.add(order);
        }
        connection.close();
        return orders;
    }

    // Method to add an order
    public static void addOrder(Order order) throws SQLException {
        Connection connection = getConnection();
        String query = "INSERT INTO orders (customerName, items, total, status, order_time) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, order.customerName);
        pst.setString(2, order.items);
        pst.setDouble(3, order.total);
        pst.setString(4, order.status);
        pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
        pst.executeUpdate();
        connection.close();
    }

    // Method to update order status
    public static void updateOrderStatus(int orderId, String status) throws SQLException {
        Connection connection = getConnection();
        String query = "UPDATE orders SET status = ?, update_time = ? WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, status);
        pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        pst.setInt(3, orderId);
        pst.executeUpdate();
        connection.close();
    }

    // Method to delete an order
    public static void deleteOrder(int orderId) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM orders WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, orderId);
        pst.executeUpdate();
        connection.close();
    }
}
