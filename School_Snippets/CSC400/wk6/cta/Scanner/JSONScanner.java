// package wk6.cta.Scanner;

package Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JSONScanner {
    // Simple method to read JSON file as a string
    public static String readJsonFile(String filename) {
        StringBuilder json = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: JSON file not found.");
        }
        return json.toString();
    }

    public static void main(String[] args) {
        String jsonString = readJsonFile("data.json");
        System.out.println("Raw JSON String: " + jsonString);
    }
}
