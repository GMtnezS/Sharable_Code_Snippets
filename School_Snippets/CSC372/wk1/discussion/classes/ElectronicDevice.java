package classes; 

// Superclass
public class ElectronicDevice {
    public String name, model, color, brand;
    public int year, battery;

    public void turnOn() { System.out.println(name + " is now ON."); }
    public void turnOff() { System.out.println(name + " is now OFF."); }
    public void getBatteryLife() { System.out.println(name + " has " + battery + "% battery."); }
}

