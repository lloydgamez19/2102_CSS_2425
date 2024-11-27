/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jframe;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author lloyd
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/catering"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connection to the database established successfully!");
        return connection;
    }

    private static class DriverManager {

        private static Connection getConnection(String URL, String USER, String PASSWORD) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public DriverManager() {
        }
    }
}
