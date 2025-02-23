// package wk6.cta;
// package cta

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

public class CustomLinkedListTest {

    @Test
    public void testInsertionAndIteration() {
        CustomLinkedList list = new CustomLinkedList();
        list.insert(10);
        list.insert(20);
        list.insert(30);

        int[] expected = {10, 20, 30};
        int index = 0;

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            assertEquals(expected[index++], (int) iterator.next());
        }
    }

    @Test
    public void testDeletion() {
        CustomLinkedList list = new CustomLinkedList();
        list.insert(5);
        list.insert(10);
        list.insert(15);

        list.delete(10);

        int[] expected = {5, 15};
        int index = 0;

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            assertEquals(expected[index++], (int) iterator.next());
        }
    }

    @Test
    public void testDeletingNonExistentElement() {
        CustomLinkedList list = new CustomLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.delete(99); 

        int[] expected = {1, 2, 3};
        int index = 0;

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            assertEquals(expected[index++], (int) iterator.next());
        }
    }

    @Test
    public void testEmptyListDeletion() {
        CustomLinkedList list = new CustomLinkedList();
        list.delete(10);
        assertFalse(list.iterator().hasNext());
    }

    @Test
    public void testIterationOnEmptyList() {
        CustomLinkedList list = new CustomLinkedList();
        assertFalse(list.iterator().hasNext());
    }

    @Test
    public void testFileInputHandler() {
        String testFilename = "test_inputs/test_numbers.txt";

        List<Integer> expected = Arrays.asList(3, 8, 15, 20);
        List<Integer> result = FileInputHandler.readIntegersFromFile(testFilename);

        assertEquals(expected, result);
    }

    @Test
    public void testFileInputHandlerToLinkedList() {
        String testFilename = "test_inputs/test_numbers.txt";
        List<Integer> numbers = FileInputHandler.readIntegersFromFile(testFilename);
        CustomLinkedList list = new CustomLinkedList();

        for (int num : numbers) {
            list.insert(num);
        }

        int index = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            assertEquals(numbers.get(index++), iterator.next());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInsertion() {
        CustomLinkedList list = new CustomLinkedList();
        list.insert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFileInput() {
        FileInputHandler.readIntegersFromFile(null);
    }

    @Test
    public void testEmptyFileInput() {
        String emptyFilename = "test_inputs/empty.txt";
        List<Integer> result = FileInputHandler.readIntegersFromFile(emptyFilename);
        assertTrue(result.isEmpty());
    }
}
