package wk2.cta;

public class Main2 {
   // Main method to test the Bag implementation
  public static void main(String[] args) {

      // Create two instances of the `Bag` class.
      Bag<String> bag1 = new Bag<>();
      Bag<String> bag2 = new Bag<>();

      // Add elements to each bag, including duplicates.
      bag1.add("Apple");
      bag1.add("Banana");
      bag1.add("Apple");
      bag1.add("Orange");
      bag1.add("Banana");
      bag1.add("Apple");
      bag1.add("Grapes");
      bag1.add("Mango");
      bag2.add("Pineapple");
      bag2.add("Banana");
      bag2.add("Orange");
      bag2.add("Apple");
      bag2.add("Watermelon");
      bag2.add("Mango");
      bag2.add("Grapes");

      // Print bag contents
      System.out.println("Bag 2 contents: " + bag2);

      // Print the size of each bag using the `size` method.
      System.out.println("Bag 1 size: " + bag1.size()); // 8
      System.out.println("Bag 2 size: " + bag2.size()); // 7

      // Merge the two bags together using the `merge` method.
      bag1.merge(bag2);

      // Print the merged bag contents.
      System.out.println("Merged Bag 1 contents: " + bag1);
  
      // Create a new bag containing only the distinct elements using the `distinct` method.
      Bag<String> distinctBag = bag1.distinct();

      // Print the distinct bag contents.
      System.out.println("Distinct bag: " + distinctBag);
      
  }
}
