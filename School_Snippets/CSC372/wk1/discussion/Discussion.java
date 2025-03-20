package discussion;

import classes.*;

// Main method to demonstrate inheritance
public class Discussion {
    public static void main(String[] args) {

        Smartphone iPhone = new Smartphone();
        iPhone.name = "iPhone 15 Pro";
        iPhone.callHistory = new String[]{"Dad", "Bank", "Boss"};
        iPhone.appCount = 150;
        iPhone.showCallHistory();
        iPhone.getBatteryLife();

        Laptop macbook = new Laptop();
        macbook.name = "MacBook Pro";
        macbook.processor = "M2 Chip";
        macbook.RAM = 16;
        macbook.runSoftware("Photoshop");

        Smartwatch appleWatch = new Smartwatch();
        appleWatch.name = "Apple Watch Series 9";
        appleWatch.time = "10:45 AM";
        appleWatch.showTime();

    }
}
