package wk4;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> values = new ArrayList<>();

        int count = 0;

        System.out.println("Enter 5 numbers values:");

        while (count < 5) {
            System.out.print("Enter value " + (count + 1) + ": ");
            try {
                double value = Double.parseDouble(scanner.nextLine());
                values.add(value);
                count++;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid floating-point number.");
            }
        }

        double total = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for (double value : values) {
            total += value;
            if (value > max) max = value;
            if (value < min) min = value;
        }

        double average = total / values.size();
        double interest = total * 0.20;

        System.out.println("\nResults:");
        System.out.printf("Total: %.2f%n", total);
        System.out.printf("Average: %.2f%n", average);
        System.out.printf("Maximum: %.2f%n", max);
        System.out.printf("Minimum: %.2f%n", min);
        System.out.printf("Interest on total at 20%%: %.2f%n", interest);

        scanner.close();
    }

}

// First, we initiate the Scanner.
// Then we prompt the user to enter their 5 numbers. 
// while we don't have 5 numbers yet, keep asking user for number - if not a valid number was provided do not count input and continue prompting for valid input. 

// Leveraging for loop to calculate the total, and find the min and max numbers in the process. 
// then we define average and interest.

// Finally, we print the results of the calculations.
// Last but not least, we close the Scanner to finalize.
