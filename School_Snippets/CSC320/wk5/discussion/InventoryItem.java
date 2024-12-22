package wk5.discussion;

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

    // Method to display item details
    public void displayItem() {
        System.out.printf("Item: %s, Quantity: %d, Price: $%.2f%n", name, quantity, price);
    }
}
