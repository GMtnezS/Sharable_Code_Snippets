package classes;

// Subclass: Smartwatch
public class Smartwatch extends ElectronicDevice {
    public String[] callHistory;
    public String time;
    
    public void showTime() {
        System.out.println(name + " current time: " + time);
    }
}