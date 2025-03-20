package classes;

// Subclass: Laptop
public class Laptop extends ElectronicDevice {
    public String processor;
    public int RAM;
    
    public void runSoftware(String software) {
        System.out.println(name + " is running " + software + "...");
    }
}