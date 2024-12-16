package wk5;

// Define the InventoryItem class
class InventoryItem {
    private String name;
    private int quantity;
    private double price;

    // Constructor
    public InventoryItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Method to display item details
    public void displayItem() {
        System.out.printf("Item: %s, Quantity: %d, Price: $%.2f%n", name, quantity, price);
    }
}
