import classes.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class RecursiveProductTest {

    @Test
    public void testComputeProduct() {
        long[] numbers = {1, 2, 3, 4, 5};
        long result = RecursiveProduct.computeProduct(numbers, 0);
        assertEquals(120, result);
    }

    @Test
    public void testComputeProductWithSingleElement() {
        long[] numbers = {7};
        long result = RecursiveProduct.computeProduct(numbers, 0);
        assertEquals(7, result);
    }

    @Test
    public void testGetInput() {
        String simulatedInput = "2\n3\n4\n5\n6\n";
        Scanner scanner = new Scanner(simulatedInput);

        long[] result = RecursiveProduct.getInput(scanner);
        assertArrayEquals(new long[]{2, 3, 4, 5, 6}, result);
    }

    @Test
    public void testDisplayResult() {
        String result = RecursiveProduct.displayResult(30);
        assertEquals("The product of the numbers is: 30", result);
    }

    // had some fun with inputs validation ;D    
    @Test
    public void testValidProductWithZero() {
        long[] numbers = {1, 2, 3, 0, 5};
        long result = RecursiveProduct.computeProduct(numbers, 0);
        assertEquals(0, result);
    }

    @Test
    public void testValidProductWithNegativeNumber() {
        long[] numbers = {-1, 2, 3, 4, 5};
        long result = RecursiveProduct.computeProduct(numbers, 0);
        assertEquals(-120, result);
    }

    @Test
    public void testNonIntegerInputRecovery() {
        String input = "abc\n1\n2\n3\n4\n5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        long[] numbers = RecursiveProduct.getInput(scanner);
        assertArrayEquals(new long[]{1, 2, 3, 4, 5}, numbers);
    }

    @Test
    public void testDecimalInputRecovery() {
        String input = "3.14\n1\n2\n3\n4\n5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        long[] numbers = RecursiveProduct.getInput(scanner);
        assertArrayEquals(new long[]{1, 2, 3, 4, 5}, numbers);
    }

    @Test
    public void testTooFewInputs() {
        String input = "1\n2\n3\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        try {
            RecursiveProduct.getInput(scanner);
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void testExtraInputsIgnored() {
        String input = "1\n2\n3\n4\n5\n6\n7\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        long[] numbers = RecursiveProduct.getInput(scanner);
        assertArrayEquals(new long[]{1, 2, 3, 4, 5}, numbers);
    }

    @Test
    public void testNullInputStream() {
        try {
            Scanner scanner = new Scanner((InputStream) null);
            RecursiveProduct.getInput(scanner);
            fail("Expected NullPointerException or IllegalStateException");
        } catch (NullPointerException | IllegalStateException e) {
        }
    }
}