package com.mycompany.catering_system_services;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class AdminFrame extends JFrame {
    
    private JPanel mainPanel;
    private JLabel welcomeLabel;

    public AdminFrame() {
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        welcomeLabel = new JLabel("Welcome");

        mainPanel.add(welcomeLabel);
        add(mainPanel);

        setTitle("Admin Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
            }
        });
    }
}
