package myproject_catering.customer;

import java.util.List;
import java.time.LocalDateTime;
import myproject_catering.customer.CartItem;

public class Order {
    private int orderId;
    private Customer customer;
    private List<CartItem> items;
    private double total;
    private String status;
    private LocalDateTime orderDate;

    public Order(int orderId, Customer customer, List<CartItem> items, double total, String status, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.total = total;
        this.status = status;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + "\nCustomer: " + customer.getName() + "\nItems: " + items + "\nTotal: Php " + total + "\nStatus: " + status + "\nOrder Date: " + orderDate;
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        for (CartItem cartItem : items) {
            details.append(cartItem.toString()).append("\n");
        }
        details.append("Total: Php ").append(total).append("\nStatus: ").append(status)
                .append("\nOrder Date: ").append(orderDate);
        return details.toString();
    }
}
