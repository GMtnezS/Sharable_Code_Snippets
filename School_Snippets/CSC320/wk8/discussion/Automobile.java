package wk8.discussion;

public class Automobile {
    private String make;
    private String model;
    private int year;
    private String vin;
    private boolean available;

    // Default Constructor
    public Automobile() {
        this.make = "Unknown";
        this.model = "Unknown";
        this.year = 0;
        this.vin = "N/A";
        this.available = true;
    }

    // Parameterized Constructor
    public Automobile(String make, String model, int year, String vin, boolean available) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
        this.available = available;
    }

    public String getVin() {
        return vin;
    }

    public String toString() {
        return year + " " + make + " " + model + " | " + (available ? "Available" : "Sold");
    }
}
