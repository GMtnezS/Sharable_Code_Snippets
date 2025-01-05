package wk8.M8CTA;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeInventory {
    private List<Home> homes;

    // Constructor to initialize the list
    public HomeInventory() {
        this.homes = new ArrayList<>();
    }

    // Add Home to Inventory
    public boolean addHome(Home home) {
        if (home != null) {
            homes.add(home);
            System.out.println("Home added successfully.");
            return true;
        }
        System.out.println("Failed to add home. Home is null.");
        return false;
    }

    // Remove Home from Inventory
    public boolean removeHome(String modelName, String address, int zipCode) {
        for (Home home : homes) {
            boolean result = home.RemoveHome(modelName, address, zipCode, false);
            if (result) {
                homes.remove(home);
                System.out.println("Home removed from inventory.");
                return true;
            }
        }
        System.out.println("No matching home found to remove.");
        return false;
    }

    // Update Home in Inventory
    public boolean updateHome(String modelName, String address, int zipCode, Home updatedHome) {
        for (Home home : homes) {
            if (home.UpdateHome(modelName, address, zipCode, updatedHome, false)) {
                System.out.println("Home updated successfully in inventory.");
                return true;
            }
        }
        System.out.println("No matching home found to update.");
        return false;
    }

    // List All Homes
    public void listHomes() {
        if (homes.isEmpty()) {
            System.out.println("No homes in inventory.");
        } else {
            for (Home home : homes) {
                home.GetHomeDetails();
            }
        }
    }

    // Export Inventory to File
    public void exportInventory(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (homes.isEmpty()) {
                writer.write("No homes in inventory.\n");
            } else {
                for (Home home : homes) {
                    writer.write(home.formatHomeDetails());
                }
            }
            System.out.println("Inventory exported successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error exporting inventory: " + e.getMessage());
        }
    }

    // Export Inventory to Multiple Files
    public void exportInventoryToMultipleFiles(String filePath) {
        if (homes.isEmpty()) {
            System.out.println("No homes in inventory to export.");
            return;
        }
        
        int index = 1;
        for (Home home : homes) {
            String indexedPath = String.format("%s_%d.txt", filePath, index);
            home.ExportToFile(indexedPath);
            index++;
        }
        System.out.println("Inventory exported to separate files successfully.");
    }
}
