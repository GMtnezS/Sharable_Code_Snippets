package wk8.discussion;

public class Main {
      public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Create Automobiles
        Automobile car1 = new Automobile("Toyota", "Tundra", 2008,"VIN123", true);
        Automobile car2 = new Automobile("Honda", "Accord", 2015, "VIN321", true);

        // Add to Inventory
        inventory.addCar(car1);
        inventory.addCar(car2);

        // Display Inventory
        inventory.displayInventory();

        // Remove a Car
        inventory.removeCar("VIN123");

        // Display Again
        inventory.displayInventory();
    }
}
