package wk1.cta;

import java.util.HashMap;

// We talked a lot about leveraging arrays and ArrayLists for bag creation, 
// but it didn't feel quite ideal so I ended up going for a hashmap instead due to its performance benefits. 
// Hope it's ok! if not, i can redo it!

public class Bag<T> {
  private HashMap<T, Integer> items; // HashMap to store items and their counts

  // Constructor to set the HashMap
  public Bag() {
      this.items = new HashMap<>();
  }

  // Method to Add an item to the bag
  public void add(T item) {
      items.put(item, items.getOrDefault(item, 0) + 1);
  }

  // Method to Remove one occurrence of an item from the bag
  public void remove(T item) {
      if (items.containsKey(item)) {
          int count = items.get(item);
          if (count > 1) {
              items.put(item, count - 1);
          } else {
              items.remove(item);
          }
      }
  }

  // Method to Check if the bag contains an item
  public boolean contains(T item) {
      return items.containsKey(item);
  }

  // Method to Get the count of an item
  public int count(T item) {
      return items.getOrDefault(item, 0);
  }

  // Method to Get the total size of the bag (including duplicates)
  public int size() {
      int totalSize = 0;
      for (int count : items.values()) {
          totalSize += count;
      }
      return totalSize;
  }

  // Method to Merge another bag into this bag
  public void merge(Bag<T> otherBag) {
      for (T item : otherBag.items.keySet()) {
          int otherCount = otherBag.items.get(item);
          items.put(item, items.getOrDefault(item, 0) + otherCount);
      }
  }

  // Method to Get a new bag with distinct elements
  public Bag<T> distinct() {
      // Initializing the new bag that will contain these distinct elements
      Bag<T> distinctBag = new Bag<>();
      // Populating the distinct items
      for (T item : items.keySet()) {
          distinctBag.add(item);
      }
      // Returning Created Distinct Bag
      return distinctBag;
  }

  // Override toString for easy printing
  @Override
  public String toString() {
      return items.toString();
  }

}
