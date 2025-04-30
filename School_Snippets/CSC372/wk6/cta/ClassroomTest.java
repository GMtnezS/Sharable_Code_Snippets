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
} 
