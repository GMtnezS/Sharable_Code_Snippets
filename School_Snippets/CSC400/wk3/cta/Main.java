package wk3.cta;

import java.util.HashMap;

public class Main {
    public static void fourth(int[] array) {
      HashMap<Integer, Boolean> map = new HashMap<>();
      HashMap<Integer, Boolean> missing = new HashMap<>(); // Track potential missing numbers
      int n = array.length;

      for (int num : array) {
          // Remove the current number from 'missing' if it exists there
          if (missing.containsKey(num)) {
              missing.remove(num);
          }

          // If the number is greater than array.length + 1, skip adding num + 1 since it would be out of bounds
          if (num == n + 1) {
              map.put(num, true);
          } else {
              // Add current number and num + 1 to map; num as found true and num + 1 as found false (if not already in map)
              map.put(num, true);

              if (!map.containsKey(num + 1)) {
                  map.put(num + 1, false); // Save num + 1 as not found in map
                  missing.put(num + 1, true); // Add num + 1 to potential missing
              } 

              if (num > 1) { // Ensure num - 1 is within range
                if (!map.containsKey(num - 1)) {
                    map.put(num - 1, false); // Save num - 1 as not found in map
                    missing.put(num - 1, true); // Add num - 1 to potential missing
                }
            }
          }

      }

      System.out.println(missing.keySet().iterator().next());
    
    }

    public static void main(String[] args) {
      // Exercise 1)
      // What is the Big-Oh of the following computation?

      // int sum = 0;
      // for (int counter = n; counter > 0; counter = counter - 2)
      //     sum = sum + counter;

      // The for loop starts with counter = n and decrements by 2 in each iteration. This means the number of iterations is approximately n/2, which represents half of n.
      // The constant factor 1/2 is irrelevant in Big-O analysis because Big-O focuses on the growth rate as n increases.
      // Therefore, n/2 simplifies to O(n). 

      // ________________________________________

      // Exercise 2)
      // Suppose your implementation of a particular algorithm appears in Java as follows:

      // for (int pass = 1; pass <= n; pass++)
      // {
      //      for(int index  = 0; index < n; index++)
      //      {
      //          for(int count = 1; count < 10; count++)
      //          {
      //            . . . 
      //
      //          } //end for
      //      } // end for
      // } //end for
      // The algorithm involves an array of "n" items. The previous code shows only the repetition in the algorithm, but it does not show the computations that occur within the loops.Those computations, however, are independent of "n." What is the order of the algorithm?    

      // The outer for loop runs n times. 
      // For each iteration of the outer loop, the middle loop also runs n times. 
      // Within the middle loop, the inner loop always runs a constant number of times (9).
      // This results in a total of n * n * 9 = 9n^2 iterations.
      
      // In Big-O analysis, we ignore constant factors like 9, 
      // so the complexity simplifies to O(n²).

      // Exercise 3)
      // Consider two programs, A and B. Program A requires 1000 x n^2 operations and Program B requires 2^n operaitons. For which values of n will Program A execute faster than Program B?

      // Based on the explanation, Program A = 1000n^2 and Program B = 2^n. 
      // To determine when Program A is faster than Program B, we need to compare their formulas at different values of n. 

      // n      | 1000 * n^2 (Program A) | 2^n (Program B)      | Which is Better
      // -------|-------------------------|----------------------|----------------
      // 1      | 1000                   | 2                    | Program B
      // 2      | 4000                   | 4                    | Program B
      // 3      | 9000                   | 8                    | Program B
      // 4      | 16000                  | 16                   | Program B
      // 5      | 25000                  | 32                   | Program B
      // 10     | 100000                 | 1024                 | Program B
      // 15     | 225000                 | 32768                | Program B
      // 18     | 324000                 | 262144               | Program B
      // 19     | 361000                 | 524288               | Program A
      // 20     | 400000                 | 1048576              | Program A
      // 25     | 625000                 | 33554432             | Program A
      // 50     | 2500000                | 1125899906842624     | Program A
      // 100    | 10000000               | 1.27E+30             | Program A
      // 1000   | 1000000000             | 1.07E+301            | Program A

      // As noted in this table, at first Program B performs better than Program A, up until a point in between n = 15 and n = 20
      // From this point forward, Program B continues to exponentially grow so quickly that it more than duplicated itself before even reaching 100 iterations. 
      // As shown by this graph, Program A is faster than Program B when n > 18 (non-inclusive of n = 18). 

      // Exercise 4)
      // Consider an array of length "n" containing unique integers in random order and in the range 1 to n + 1. For example an array of length 5 would contain 5 unique integers selected randomly from the integers 1 through 6. Thus the array might contain 3 6 5 1 4. Of the integers 1 through 6, notice that 2 was not selected and is not in the array. Write Java code that finds the integer that does not appear in such an array. Explain the Big-Oh in your code.

      // We are tasked with finding the missing integer from an array of size n containing unique integers in the range 1 to n+1
      // For example, if the array is [3, 6, 5, 1, 4], the missing number is 2.
      int[] array1 = {3, 6, 5, 1, 4};
      int[] array2 = {10, 7, 4, 1, 8, 5, 2, 3, 9};
      int[] array3 = {10, 7, 4, 1, 6, 5, 2, 3, 9};
      int[] array4 = {2, 3, 4, 5, 6, 7, 8, 9, 10};
      int[] array5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};

      fourth(array1);
      fourth(array2);
      fourth(array3);
      fourth(array4);
      fourth(array5);

      // This one was tricky, after some googling I found a clean solution in which we determine the sum of all present numbers and substract that from the expected total sum to find the missing value. 
      // Which makes perfect sense and will probably be how I would solve this type of problem in the future, 
      // but it really felt like something I wouldn't be able to come up on my own so I gave it another try with a different approach. 

      // Since it is guaranteed that only 1 number is missing, then that means all other numbers are present and these will be either preceeding or proceeding the missing number. 
      // Here the most important is that i'm using two maps/caches to keep track of found numbers and missing numbers throughout a single iteration.

      // As we iterate we use the current number to "discover" the other numbers around it that are expected to the present. 
      // These are num + 1 and num - 1. This also required binding into scope (1 - n + 1 means that 0 can not be included, and n + 1 + 1 should ALSO not be included)
      // If num + 1 and num - 1 have not been found, they can be considered possible missing respectively. 
      // As we check each num, we remove "possible" missing numbers and mark them as found. 

      // At the end, only the last missing number (the one that is possibly missing and was not found) will be left. 

      // this is a single loop, with no nested looping operations at each iteration, so its time complexity is O(n). 

      }
}
