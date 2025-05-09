import classes.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;

public class ClassroomTest {


    @Before
    public void resetBeforeEachTest() {
        Classroom.clearStudents();
    }

    @Test
    public void testStudentCreation() {
        Student student = new Student("Alice", "New York", 3.5);
        assertEquals("Alice", student.getName());
        assertEquals("New York", student.getAddress());
        assertEquals(3.5, student.getGpa(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStudentCreationWithEmptyName() {
        new Student("", "New York", 3.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStudentCreationWithNullAddress() {
        new Student("Alice", null, 3.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStudentCreationWithInvalidGpaNegative() {
        new Student("Alice", "New York", -1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStudentCreationWithInvalidGpaTooHigh() {
        new Student("Alice", "New York", 4.5);
    }

    @Test
    public void testGenericComparatorByName() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Bob", "Los Angeles", 3.2));
        students.add(new Student("Alice", "New York", 3.5));

        students.sort(new GenericComparator("name"));
        assertEquals("Alice", students.get(0).getName());
    }

    @Test
    public void testGenericComparatorByAddress() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Bob", "Los Angeles", 3.2));
        students.add(new Student("Alice", "New York", 3.5));

        students.sort(new GenericComparator("address"));
        assertEquals("Bob", students.get(0).getName());
    }

    @Test
    public void testGenericComparatorByGpa() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Bob", "Los Angeles", 3.2));
        students.add(new Student("Alice", "New York", 3.5));

        students.sort(new GenericComparator("gpa"));
        assertEquals("Bob", students.get(0).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenericComparatorWithInvalidField() {
        new GenericComparator("invalidField");
    }

    @Test
    public void testSelectionSorter() {
        LinkedList<Student> students = new LinkedList<>();
        students.add(new Student("Bob", "Los Angeles", 3.2));
        students.add(new Student("Alice", "New York", 3.5));
        students.add(new Student("Charlie", "Chicago", 3.1));

        SelectionSorter.selectionSort(students, new GenericComparator("name"));
        assertEquals("Alice", students.get(0).getName());
        assertEquals("Bob", students.get(1).getName());
        assertEquals("Charlie", students.get(2).getName());
    }

    @Test
    public void testSelectionSorterWithEmptyList() {
        LinkedList<Student> students = new LinkedList<>();
        SelectionSorter.selectionSort(students, new GenericComparator("name"));
        assertTrue(students.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectionSorterWithNullList() {
        SelectionSorter.selectionSort(null, new GenericComparator("name"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectionSorterWithNullComparator() {
        LinkedList<Student> students = new LinkedList<>();
        SelectionSorter.selectionSort(students, null);
    }

    @Test
    public void testAddStudentFlow() {
        String input = "1\nJohn Doe\n123 Main St\n3.5\n6\n"; // Add -> Exit
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Classroom.run(scanner);

        String result = output.toString();
        assertTrue(result.contains("Student added successfully."));
    }

    @Test
    public void testInvalidGpaInputRecovery() {
        String input = "1\nJane Doe\n456 Oak Ave\nabc\n4.5\n3.8\n6\n"; // Add -> Tries invalid GPAs, then valid GPAs -> Exit
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Classroom.run(scanner);

        String result = output.toString();
        assertTrue(result.contains("Invalid GPA. Please enter a number between 0.0 and 4.0."));
        assertTrue(result.contains("Student added successfully."));
    }

    @Test
    public void testRemoveStudentFlow() {
        String input = "1\nJohn Smith\n999 Elm Rd\n3.2\n4\nJohn Smith\n6\n"; // Add -> Remove -> Exit
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Classroom.run(scanner);

        String result = output.toString();
        assertTrue(result.contains("If present, student has been removed."));
    }

    @Test
    public void testUpdateStudentFlow() {
        String input = "1\nAna Bell\n345 River St\n3.1\n3\nAna Bell\n456 Stream Blvd\ny\n3.6\n6\n"; // Add -> Update -> Exit
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Classroom.run(scanner);

        String result = output.toString();
        assertTrue(result.contains("Student updated."));
    }

    @Test
    public void testExportEmptyStudentList() {
        Classroom.clearStudents();

        String input = "5\n6\n"; // Export -> Exit
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Classroom.run(scanner);

        String result = output.toString();
        assertTrue(result.contains("No students to export."));
    }

    @Test
    public void testInvalidMenuOption() {
        String input = "99\n6\n"; // Invalid -> Exit
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Classroom.run(scanner);

        String result = output.toString();
        assertTrue(result.contains("Invalid option. Try again."));
    }

    @Test
    public void testExportSortedListContent() {
        // Adding some students
        String input = "1\nCharlie\nChicago\n3.1\n1\nAlice\nNew York\n3.5\n1\nBob\nLos Angeles\n3.2\n5\n6\n";
        Scanner scanner = new Scanner(input);
        Classroom.run(scanner);

        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("students_sorted_by_name.txt"));
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();
            String thirdLine = reader.readLine();
            reader.close();

            assertTrue("First line should contain Alice", firstLine.contains("Alice"));
            assertTrue("Second line should contain Bob", secondLine.contains("Bob"));
            assertTrue("Third line should contain Charlie", thirdLine.contains("Charlie"));
        } catch (java.io.IOException e) {
            fail("Failed to read output file: " + e.getMessage());
        }
    }

    @Test
    public void testCompleteDataEntryFlow() {
        String input = "1\nJohn Doe\n123 Main St\n3.5\n1\nJane Smith\n456 Oak Ave\n3.8\n5\n6\n";
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Classroom.run(scanner);

        String result = output.toString();
        assertTrue("Should add first student", result.contains("Student added successfully."));
        assertTrue("Should add second student", result.contains("Student added successfully."));
        assertTrue("Should export to file", result.contains("Student list written to students_sorted_by_name.txt"));
    }

    @Test
    public void testOutputFileIsTextFile() {
        String input = "1\nTest Student\nTest Address\n3.5\n5\n6\n";  // Add -> Export -> Exit
        Scanner scanner = new Scanner(input);
        Classroom.run(scanner);

        java.io.File file = new java.io.File("students_sorted_by_name.txt");
        assertTrue("Output file should exist", file.exists());
        assertTrue("Output file should be readable", file.canRead());
        
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            String content = reader.readLine();
            reader.close();
            assertNotNull("Should be able to read file content", content);
            assertTrue("Content should be text", content.matches(".*[a-zA-Z].*"));
        } catch (java.io.IOException e) {
            fail("Failed to read output file as text: " + e.getMessage());
        }
    }

    @Test
    public void testEndToEndCapstoneRequirements() {
        String input = "1\n" +  // Add first student
                      "John Smith\n" +
                      "123 Main St\n" +
                      "3.8\n" +
                      "1\n" +  // Add second student
                      "Alice Johnson\n" +
                      "456 Oak Ave\n" +
                      "3.9\n" +
                      "1\n" +  // Add third student with invalid GPA 
                      "Bob Wilson\n" +
                      "789 Pine Rd\n" +
                      "4.5\n" +  // Invalid GPA (should be rejected)
                      "3.2\n" +  // Valid GPA
                      "5\n" +  // Export
                      "6\n";   // Exit

        Scanner scanner = new Scanner(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Classroom.run(scanner);

        // Verifying the output file exists and contains sorted data
        java.io.File file = new java.io.File("students_sorted_by_name.txt");
        assertTrue("Output file should exist", file.exists());

        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            String fileContent = content.toString();

            // Verifying all students are in the file
            assertTrue("File should contain Alice Johnson", fileContent.contains("Alice Johnson"));
            assertTrue("File should contain Bob Wilson", fileContent.contains("Bob Wilson"));
            assertTrue("File should contain John Smith", fileContent.contains("John Smith"));

            // Verifying the order sorted by name
            int aliceIndex = fileContent.indexOf("Alice Johnson");
            int bobIndex = fileContent.indexOf("Bob Wilson");
            int johnIndex = fileContent.indexOf("John Smith");

            assertTrue("Alice should come before Bob", aliceIndex < bobIndex);
            assertTrue("Bob should come before John", bobIndex < johnIndex);

            // Verifying GPA validation worked
            assertTrue("Should contain valid GPA 3.8", fileContent.contains("3.8"));
            assertTrue("Should contain valid GPA 3.9", fileContent.contains("3.9"));
            assertTrue("Should contain valid GPA 3.2", fileContent.contains("3.2"));
            assertFalse("Should not contain invalid GPA 4.5", fileContent.contains("4.5"));

        } catch (java.io.IOException e) {
            fail("Failed to read output file: " + e.getMessage());
        }

        // Verify program output messages
        String result = output.toString();
        assertTrue("Should show invalid GPA message", result.contains("Invalid GPA"));
        assertTrue("Should show successful student additions", result.contains("Student added successfully"));
        assertTrue("Should show file export message", result.contains("Student list written to students_sorted_by_name.txt"));
    }
} 
