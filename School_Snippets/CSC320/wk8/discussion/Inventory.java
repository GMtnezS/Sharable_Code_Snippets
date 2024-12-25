package wk8.discussion;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Automobile> cars;

    // Constructor
    public Inventory() {
        this.cars = new ArrayList<>();
    }

    // Add a Car to Inventory
    public void addCar(Automobile car) {
        cars.add(car);
        System.out.println(car.getVin() + " added to inventory.");
    }

    // Remove a Car by VIN
    public void removeCar(String vin) {
        cars.removeIf(car -> car.getVin().equals(vin));
        System.out.println(vin + " removed from inventory.");
    }

    // List All Cars
    public void displayInventory() {
        System.out.println("---------------------");
        if (cars.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Automobile car : cars) {
                System.out.println(car);
            }
        }
        System.out.println("---------------------");
    }
}
