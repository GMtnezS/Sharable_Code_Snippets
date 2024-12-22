package wk5.CTA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Develop a Java program that will store data in the form of daily average temperatures for one week.
public class Main {
  // Store the day and average temperature in two different arraylists. 
  static ArrayList<String> weekdays = new ArrayList<>(Arrays.asList(
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
  ));
  static ArrayList<Double> temperatures = new ArrayList<>(Arrays.asList(50.0, 60.0, 70.0, 80.0, 65.0, 75.0, 85.0));

  public static String getValidUserInput() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> options = new ArrayList<>(weekdays);
        options.add("Week");

        String choice = "";
        
        while (true) {
            System.out.print("What day would you like to know the temperature of? ");
            String input = scanner.nextLine().toLowerCase();
            
            for (String option : options) {
                // very convenient that strings have a method to ignore casing for matches!
                if (option.equalsIgnoreCase(input)) { 
                    choice = option;
                    break;
                }
            }
            
            if (!choice.isEmpty()) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid option (Monday-Sunday or 'week').");
        }
        scanner.close();
        return choice;
  }
  
  public static void getWeekTemperatures() {
      double total = 0;
      for (int i = 0; i < weekdays.size(); i++) {
          System.out.println(weekdays.get(i) + ": " + temperatures.get(i) + " F");
          total += temperatures.get(i);
      }
      double average = total / temperatures.size();
      System.out.println("Weekly Average Temperature: " + average + " F");
  }

  public static void processChoice(String choice) {
      // If "week" is entered, the output for your program should provide the temperature for each day and the weekly average. 
      if (choice.equals("Week")) {
          getWeekTemperatures();
      } else {
      // and display both the day and temperature for each day. 
          int index = weekdays.indexOf(choice);
          System.out.println(choice + "'s temperature: " + temperatures.get(index) + " F");
      }
  }
  
  public static void main(String[] args) {
  // Use the looping and decision constructs in combination with the arrays to complete this assignment.

  // Your program should prompt the user for the day of the week (Monday through Sunday) 
    String choice = getValidUserInput();
  
    processChoice(choice);
    }
}

// First, we prepare two lists to store the weekdays and their corresponding daily average temperatures. 
// These lists are hardcoded with default values as instructed, representing one full week of days and temperatures.

// Next, we set up a method to handle user input, including the option to allow the user to enter "Week"
// We prompt the user to enter a day of the week or type "week" to see all temperatures. 
// A loop continuously asks for input until the user provides a valid day or "week". 
// The spelling was tedious, so I added support for ignoring case-sensitivity to make user experience more smooth
// If the input does not match, the user is informed and prompted again
// Once valid input is received, the scanner is closed to free resources, and the selected option is returned.

// We then define a method to process the user's choice. 
// If the user selected "week", the program prints out the temperature for each day and calculates the average temperature for the week. 
// If a specific day was selected, the program finds the matching temperature and displays it. 

// To calculate and print weekly temperatures, we use a helper method that loops through each day, prints the temperature, 
// and adds it to a total to compute the weekly average at the end. 

// In the main method, the program starts by prompting the user for input using the input method. 
// Based on the returned value, the program either displays the entire week's temperatures or the temperature for a specific day. 
// This ensures the program runs interactively and provides accurate feedback to the user. 

// Finally, the program concludes by displaying the results based on user input, completing the temperature-checking task.

