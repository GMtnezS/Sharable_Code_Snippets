import classes.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ClassroomTest {

    @Test
    public void testStudentCreation() {
        Student student = new Student(1, "Alice", "New York");
        assertEquals(1, student.getRollno());
        assertEquals("Alice", student.getName());
        assertEquals("New York", student.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStudentCreationWithNegativeRollno() {
        new Student(-1, "Alice", "New York");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStudentCreationWithEmptyName() {
        new Student(1, "", "New York");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStudentCreationWithNullAddress() {
        new Student(1, "Alice", null);
    }

    @Test
    public void testGenericComparatorByName() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(2, "Bob", "Los Angeles"));
        students.add(new Student(1, "Alice", "New York"));

        students.sort(new GenericComparator("name"));
        assertEquals("Alice", students.get(0).getName());
    }

    @Test
    public void testGenericComparatorByRollno() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(2, "Bob", "Los Angeles"));
        students.add(new Student(1, "Alice", "New York"));

        students.sort(new GenericComparator("rollno"));
        assertEquals(1, students.get(0).getRollno());
    }

    @Test
    public void testGenericComparatorByAddress() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(2, "Bob", "Los Angeles"));
        students.add(new Student(1, "Alice", "New York"));

        students.sort(new GenericComparator("address"));
        assertEquals("Bob", students.get(0).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenericComparatorWithInvalidField() {
        new GenericComparator("invalidField");
    }

    @Test
    public void testSelectionSorter() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(2, "Bob", "Los Angeles"));
        students.add(new Student(1, "Alice", "New York"));
        students.add(new Student(3, "Charlie", "Chicago"));

        SelectionSorter.selectionSort(students, new GenericComparator("rollno"));
        assertEquals(1, students.get(0).getRollno());
        assertEquals(2, students.get(1).getRollno());
        assertEquals(3, students.get(2).getRollno());
    }

    @Test
    public void testSelectionSorterWithEmptyList() {
        ArrayList<Student> students = new ArrayList<>();
        SelectionSorter.selectionSort(students, new GenericComparator("name"));
        assertTrue(students.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectionSorterWithNullList() {
        SelectionSorter.selectionSort(null, new GenericComparator("name"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectionSorterWithNullComparator() {
        ArrayList<Student> students = new ArrayList<>();
        SelectionSorter.selectionSort(students, null);
    }
}