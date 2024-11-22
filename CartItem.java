package myproject_catering.customer;

import myproject_catering.MenuItem;

public class CartItem {
    private MenuItem menuItem;
    private int quantity;

    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return menuItem.getName() + " x " + quantity + " (Php " + (menuItem.getPrice() * quantity) + ")";
    }
}
