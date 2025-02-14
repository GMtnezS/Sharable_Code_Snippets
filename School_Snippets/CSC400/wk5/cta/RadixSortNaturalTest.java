// package wk5.cta;

import org.junit.Test;
import static org.junit.Assert.*;

public class RadixSortNaturalTest {

    @Test
    public void testSortingStrings() {
        // Assignment Test Case: joke book back dig desk word fish ward dish wit deed fast dog bend
        String[] input = {"joke", "book", "back", "dig", "desk", "word", "fish", "ward", "dish", "wit", "deed", "fast", "dog", "bend"};
        String[] expected = {"back", "bend", "book", "deed", "desk", "dig", "dish", "dog", "fast", "fish", "joke", "ward", "wit", "word"};

        RadixSortNatural.radixSort(input);
        assertArrayEquals(expected, input);
    }
   
    @Test
    public void testSortingAlphanumericStrings() {
        String[] input = {"fish10", "fish2", "book1", "book10", "book2"};
        String[] expected = {"book1", "book2", "book10", "fish2", "fish10"};

        RadixSortNatural.radixSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortingNumbersWithinStrings() {
        String[] input = {"item100", "item2", "item10"};
        String[] expected = {"item2", "item10", "item100"};

        RadixSortNatural.radixSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortingWithEmptyStrings() {
        String[] input = {"fish3", "", "fish2", "fish1", ""};
        String[] expected = {"", "", "fish1", "fish2", "fish3"};

        RadixSortNatural.radixSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testAlreadySortedInput() {
        String[] input = {"book1", "book2", "book10"};
        String[] expected = {"book1", "book2", "book10"};

        RadixSortNatural.radixSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testOnlyEmptyStrings() {
        String[] input = {"", "", ""};
        String[] expected = {"", "", ""};

        RadixSortNatural.radixSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testEmptyArray() {
        String[] input = {};
        String[] expected = {};

        RadixSortNatural.radixSort(input);
        assertArrayEquals(expected, input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() {
        RadixSortNatural.radixSort(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullValuesInArray() {
        String[] input = {"book1", null, "book10"};
        RadixSortNatural.radixSort(input);
    }
}
