package wk6;

import java.util.Scanner;

public class Main {

    public static int getValidNumber() {
        Scanner scanner = new Scanner(System.in);
        int number;

        while (true) {
            System.out.print("Enter a number: ");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        scanner.close();
        return number;
    }

    public static void main(String[] args) {
        int validNumber = getValidNumber();
        System.out.println("You entered: " + validNumber);
    }
}
