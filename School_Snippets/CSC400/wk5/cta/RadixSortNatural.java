// package wk5.cta;

import java.util.*;

public class RadixSortNatural {

    // Perform radix sort on strings containing both letters and numbers
    @SuppressWarnings("unchecked")
    public static void radixSort(String[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        for (String str : array) {
            if (str == null) {
                throw new IllegalArgumentException("Array contains null values.");
            }
        }

        if (array.length == 0) return;

        boolean ifNumbers = false;

        int maxLength = Arrays.stream(array).mapToInt(String::length).max().orElse(0);
        final int ASCII_RANGE = 256;  // Covers all ASCII characters

        List<String>[] buckets = new ArrayList[ASCII_RANGE];
        for (int i = 0; i < ASCII_RANGE; i++) buckets[i] = new ArrayList<>();

        // Process characters from rightmost to leftmost (LSD Radix Sort)
        for (int charIndex = maxLength - 1; charIndex >= 0; charIndex--) {
            for (String str : array) {
                // Check if it has number, and if so, update ifNumbers to True
                if (!ifNumbers && str.matches(".*\\d.*")) {
                    ifNumbers = true;
                }

                char ch = charIndex < str.length() ? str.charAt(charIndex) : 0;
                buckets[ch].add(str);
            }

            // Collect sorted values back into array
            int index = 0;
            for (List<String> bucket : buckets) {
                for (String str : bucket) {
                    array[index++] = str;
                }
                bucket.clear();
            }
        }
        if (ifNumbers){
            // Apply Natural Order Comparator to fix numeric order
            Arrays.sort(array, new NaturalOrderComparator());
        }
    }

    // Custom comparator for natural sorting of alphanumeric strings
    public static class NaturalOrderComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return compareNatural(s1, s2);
        }

        private int compareNatural(String s1, String s2) {
            int i = 0, j = 0;
            while (i < s1.length() && j < s2.length()) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);

                // If both are digits, compare full numbers
                if (Character.isDigit(c1) && Character.isDigit(c2)) {
                    int num1 = extractNumber(s1, i);
                    int num2 = extractNumber(s2, j);

                    if (num1 != num2) {
                        return Integer.compare(num1, num2);
                    }

                    // Move past the entire number
                    i += Integer.toString(num1).length();
                    j += Integer.toString(num2).length();
                } 
                // Compare non-digit characters lexicographically
                else {
                    if (c1 != c2) {
                        return Character.compare(c1, c2);
                    }
                    i++;
                    j++;
                }
            }

            // If one string is longer, it should come later
            return Integer.compare(s1.length(), s2.length());
        }

        // Extracts a full number from a string starting at a given index
        private int extractNumber(String s, int start) {
            StringBuilder num = new StringBuilder();
            while (start < s.length() && Character.isDigit(s.charAt(start))) {
                num.append(s.charAt(start));
                start++;
            }
            return Integer.parseInt(num.toString());
        }
    }

}
