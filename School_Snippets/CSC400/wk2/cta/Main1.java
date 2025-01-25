package wk2.cta;

public class Main1 {
   // Main method to test the Bag implementation
  public static void main(String[] args) {
      // Create an instance of the `Bag` class.
      Bag<String> bag = new Bag<>();

      // Add several elements to the bag, including duplicates.
      bag.add("Apple");
      bag.add("Banana");
      bag.add("Apple");
      bag.add("Orange");
      bag.add("Banana");
      bag.add("Apple");
      bag.add("Grapes");
      bag.add("Mango");
      bag.add("Pineapple");
      bag.add("Banana");
      bag.add("Orange");
      bag.add("Apple");
      bag.add("Watermelon");
      bag.add("Mango");
      bag.add("Grapes");

      // Print bag contents
      System.out.println("Bag contents: " + bag);

      // Test the `contains` method for a few elements.
      System.out.println("Contains 'Apple': " + bag.contains("Apple")); // true
      System.out.println("Contains 'Watermelon': " + bag.contains("Watermelon")); // true
      System.out.println("Contains 'Banana': " + bag.contains("Banana")); // true
      System.out.println("Contains 'Pomegranate': " + bag.contains("Pomegranate")); // true

      // Test the `count` method for a few elements.
      System.out.println("Count of 'Apple': " + bag.count("Apple")); // 4
      System.out.println("Count of 'Watermelon': " + bag.count("Watermelon")); // 1
      System.out.println("Count of 'Banana': " + bag.count("Banana")); // 2
      System.out.println("Count of 'Pomegranate': " + bag.count("Pomegranate")); // 2

      // Print bag contents
      System.out.println("Bag contents: " + bag);

      String removeItem = "Apple";

      // Remove an element from the bag.
      bag.remove(removeItem);

      // Print the bag contents again
      System.out.println(String.format("Bag contents after removing '%s': %s", removeItem, bag));

      // Test the `contains` method for the removed element.
      System.out.println(String.format("Does the bag still contain '%s'? %b", removeItem, bag.contains(removeItem))); // true

      // Test the `count` method for the removed element.
      System.out.println(String.format("Remaining count of '%s' in the bag: %d", removeItem, bag.count(removeItem))); // 3
  
  }
}
