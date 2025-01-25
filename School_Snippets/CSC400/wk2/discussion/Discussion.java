package wk2.discussion;

import java.util.ArrayList;

public class Discussion {
    public static void main(String[] args) {
      // Array sample
      int length = 5;
      int[] numbers = new int[length]; 
      numbers[0] = 10;
      numbers[1] = 20;

      for (int i = 0; i < length; i++) {
        System.out.println(numbers[i]);
      }

      // ArrayList sample
      ArrayList<Integer> nums = new ArrayList<>();
      nums.add(10);
      nums.add(20);
     
      for (int i = 0; i < nums.size(); i++) {
        System.out.println(nums.get(i));
      }

    }

}