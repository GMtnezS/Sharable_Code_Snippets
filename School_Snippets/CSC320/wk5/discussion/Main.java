package wk5.discussion;

public class Main {
    public static void main(String[] args) {

        InventoryItem inventory1 = new InventoryItem("Laptop", 10, 999.99);
        InventoryItem inventory2 = new InventoryItem("Mouse", 50, 19.99);
        InventoryItem inventory3 = new InventoryItem("Keyboard", 30, 49.99);

        InventoryItem[] inventory = {inventory1, inventory2, inventory3};

        System.out.println("Inventory Items:");
        for (InventoryItem item : inventory) {
            item.displayItem();
        }
    }
}
