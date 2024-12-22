package wk6.CTA;

import wk6.CTA.Home;

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
      System.out.println("Home Creation Test:");
        try {
            Home home1 = new Home(2500, "123 Oak St", "Denver", "CO", 80211, "Classic", "Available");
            //  with a valid home created, we can perform other actions..
            // GET 
            home1.GetHomeDetails();
            System.out.println("---------------------");
            // PUT
            Home updatedHome = new Home(2200, "456 Pine St", "Austin", "TX", 73301, "Modern", "sold");
            System.out.println("Update Attempts: ");
            System.out.println("Failed Update: ");
            boolean update1Result = home1.UpdateHome("Modern", "456 Pine St", 73301, updatedHome);
            System.out.println(update1Result);
            System.out.println("---------------------");
            System.out.println("Successful Update: ");
            boolean update2Result = home1.UpdateHome("Classic", "123 Oak St", 80211, updatedHome);
            System.out.println(update2Result);
            System.out.println("---------------------");

            // EXPORT
            home1.ExportToFile("Home1.txt");
            System.out.println("---------------------");
            // REMOVE
            System.out.println("Remove Attempts: ");
            System.out.println("Failed Remove: ");
            boolean remove1Result = home1.RemoveHome("Classic", "123 Oak St", 80211);
            System.out.println(remove1Result);
            System.out.println("---------------------");
            System.out.println("Successful Remove: ");
            boolean remove2Result = home1.RemoveHome("Modern", "456 Pine St", 73301);
            System.out.println(remove2Result);
            System.out.println("---------------------");
            // Made sure to include some examples of failed updates and removals too, to reflect the behavior that HomeInventory will be handling when working with the Home Class
        } catch (IllegalArgumentException e) {
            System.out.println("Error during home creation: " + e.getMessage());
        }
      }
}
