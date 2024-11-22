/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import javax.swing.JOptionPane;
/**
 *
 * @author lloyd
 */
public class Tables {
    public static void main(String[]  args){
               try {
            String userTable = "CREATE TABLE user (" +
                               "id INT AUTO_INCREMENT PRIMARY KEY, " +
                               "name VARCHAR(200), " +
                               "email VARCHAR(200) UNIQUE, " +
                               "mobileNumber VARCHAR(10), " +
                               "address VARCHAR(200), " +
                               "password VARCHAR(200), " +
                               "securityQuestion VARCHAR(200), " +
                               "answer VARCHAR(200), " + 
                               "status VARCHAR(20)" +
                               ")";
            DbOperations.setDataOrDelete(userTable, "User Table Created Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
