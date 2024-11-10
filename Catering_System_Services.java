package com.mycompany.catering_system_services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Catering_System_Services {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/GILBERT";
        String username = "root";
        String password = "Dikoalam.com3";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GILBERT", "root", "Dikoalam.com3");
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
