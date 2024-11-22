package myproject_catering;

import java.util.ArrayList;
import java.util.List;
import myproject_catering.customer.MenuItem;

public class Order {
    private final int orderId;
    private final int customerId;
    private final List<MenuItem> orderItems;
    private final double totalCost;
    private String status;

    // Constructor
    public Order(int orderId, int customerId, List<MenuItem> orderItems, double totalCost, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderItems = orderItems != null ? orderItems : new ArrayList<>(); // Prevent null list
        this.totalCost = totalCost;
        this.status = status != null ? status : "Pending"; // Default status to "Pending" if null
    }

    public Order(int i, int id, ArrayList<MenuItem> arrayList, double totalCost, String pending, String paymentMethod) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status != null ? status : this.status; 
    }

    @Override
    public String toString() {
        StringBuilder itemsStr = new StringBuilder();

        if (orderItems == null || orderItems.isEmpty()) {
            StringBuilder append = itemsStr.append("No items");
        } else {
            for (MenuItem item : orderItems) {
                itemsStr.append("\n- ").append(item.getName())
                        .append(" (PHP ").append(String.format("%.2f", item.getPrice())).append(")");
            }
        }

        return String.format(
            "Order ID: %d\nCustomer ID: %d\nTotal Cost: PHP %.2f\nStatus: %s\nItems:%s",
            orderId, customerId, totalCost, status, itemsStr.toString()
        );
    }
}
