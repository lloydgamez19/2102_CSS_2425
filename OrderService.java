package com.mycompany.catering_system_services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    // Method to view all orders
    public static List<Order> viewAllOrders() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM orders";
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order(
                rs.getInt("id"),
                rs.getString("customerName"),
                rs.getString("items"),
                rs.getDouble("total"),
                rs.getString("status")
            );
            orders.add(order);
        }
        connection.close();
        return orders;
    }

    // Method to update order status
    public static void updateOrderStatus(int orderId, String status) throws SQLException {
        Connection connection = getConnection();
        String query = "UPDATE orders SET status = ? WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, status);
        pst.setInt(2, orderId);
        pst.executeUpdate();
        connection.close();
    }

    static void addOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
