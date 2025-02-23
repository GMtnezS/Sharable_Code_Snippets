// package wk6.cta;

// import wk6.cta.Scanner.JSONScanner;
import Scanner.JSONScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A file input handler that extends JSONScanner and provides functionality
 * to read a file and extract integer values.
 */
public class FileInputHandler extends JSONScanner {
    
    /**
     * Reads a file and extracts integer values from each line.
     * @param filename The name of the file to read.
     * @return A list of integers extracted from the file.
     */
    public static List<Integer> readIntegersFromFile(String filename) {
        if (filename == null) { 
            throw new IllegalArgumentException("Filename cannot be null");
        }

        List<Integer> numbers = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) {  
            System.err.println("Error: File not found at " + file.getAbsolutePath());
            return numbers;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                try {
                    int number = Integer.parseInt(scanner.nextLine().trim());
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid entry: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        }

        return numbers;
    }
}
