package classes;

// Subclass: Smartphone
public class Smartphone extends ElectronicDevice {
    public String[] callHistory;
    public int appCount;
    
    public void showCallHistory() {
        System.out.println(name + " Call History: " + String.join(", ", callHistory));
    }
}
