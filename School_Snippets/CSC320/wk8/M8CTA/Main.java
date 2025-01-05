package wk8.M8CTA;

public class Main {
      public static void main(String[] args) {
      // Here we simulate what Home Inventory will be able to do with Home once they are integrated together: 
      // Attempting to Create an Invalid Home, Home Class validates what is valid home information and throws an exception if the information was not valid.
      // Such will prevent Home Inventory from adding unvalidated homes to the inventory.
      System.out.println("Invalid Home Creation Test:");
      try {
          new Home(-1500, "", "Austin", "TX", 73301, "Modern", "sold");
      } catch (IllegalArgumentException e) {
          System.out.println("Expected Error: " + e.getMessage());
      }

      System.out.println("=====================");
      System.out.println("Home Inventory Creation Test:");
        try {
            HomeInventory inventory = new HomeInventory();

            Home home1 = new Home(2500, "123 Oak St", "Denver", "CO", 80211, "Classic", "Available");
            Home home2 = new Home(1800, "456 Pine St", "Austin", "TX", 73301, "Modern", "Sold");

            //  with valid homes created, we can populate our inventory and perform other actions..
            // POST
            inventory.addHome(home1);
            inventory.addHome(home2);

            // GET 
            System.out.println("---------------------");
            inventory.listHomes();
            System.out.println("---------------------");

            // PUT
            Home updatedHome1 = new Home(2200, "456 Pine St", "Austin", "TX", 73301, "Modern", "sold");
            Home updatedHome2 = new Home(2200, "101112 Pine St", "Miami", "FL", 33456, "Classic", "Available");
            
            System.out.println("Update Attempts: ");
            System.out.println("Failed Update: ");
            inventory.updateHome("Modern", "789 Pine St", 73301, updatedHome1);
            System.out.println("---------------------");
            System.out.println("Successful Update: ");
            inventory.updateHome("Classic", "123 Oak St", 80211, updatedHome2);
            System.out.println("---------------------");

            // EXPORT
            inventory.listHomes();
            System.out.println("---------------------");
            inventory.exportInventory("./output/inventory_output.txt");
            System.out.println("---------------------");
            inventory.exportInventoryToMultipleFiles("./output/inventory_output");
            System.out.println("---------------------");
            
            // REMOVE
            System.out.println("Remove Attempts: ");
            System.out.println("Failed Remove: ");
            inventory.removeHome("Classic", "123 Oak St", 80211);
            System.out.println("---------------------");
            System.out.println("Successful Remove: ");
            inventory.removeHome("Modern", "456 Pine St", 73301);
            System.out.println("---------------------");
            
            // Made sure to include some examples of failed updates and removals too, to reflect the behavior that HomeInventory will be handling when working with the Home Class
            inventory.listHomes();
        } catch (IllegalArgumentException e) {
            System.out.println("Error during home creation: " + e.getMessage());
        }
      }
}
