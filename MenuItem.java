package myproject_catering.customer;

public class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;

    // menu initialization
    public MenuItem(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    
    public int getId() {
        return id;
    }

    
    public String getName() {
        return name;
    }

   
    public String getDescription() {
        return description;
    }

   
    public double getPrice() {
        return price;
    }

    // for easier printinggg
    @Override
    public String toString() {
        return String.format("MenuItem{id=%d, name='%s', description='%s', price=%.2f}", id, name, description, price);
    }
}
