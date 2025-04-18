package classes;
import java.util.Scanner;

/**
 *
 * This program prompts the user to enter five long integers,
 * calculates the product using recursion, and displays the result.
 *
 */
public class RecursiveProduct {

    /**
     * The main method orchestrates the flow of the program:
     * it gets input, computes the product, and displays the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] numbers = getInput(scanner);
        long product = computeProduct(numbers, 0);
        displayResult(product);
        scanner.close();
    }


    /**
     * Prompts the user to enter five long integers and returns them in an array.
     *
     * @return an array containing the five long integers entered by the user
     */
    public static long[] getInput(Scanner scanner) {
        long[] numbers = new long[5];
        System.out.println("Enter 5 numbers:");

        int count = 0;
        while (count < numbers.length) {
            System.out.print("Number " + (count + 1) + ": ");
            if (scanner.hasNextLong()) {
                numbers[count] = scanner.nextLong();
                count++;
            } else {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next(); // clear invalid input
            }
        }
        return numbers;
    }


    /**
     * Recursively calculates the product of the elements in the array starting from a given index.
     *
     * @param nums  the array of long integers
     * @param index the current index in the array
     * @return the product of the elements from index to the end of the array
     */
    public static long computeProduct(long[] nums, int index) {
        if (index == nums.length - 1) {
            return nums[index];
        }
        return nums[index] * computeProduct(nums, index + 1);
    }

    /**
     * Displays the final product result to the user and returns the message string.
     *
     * @param product the computed product of the array
     * @return the message that was displayed
     */
    public static String displayResult(long product) {
        String message = "The product of the numbers is: " + product;
        System.out.println(message);
        return message;
    }

}
