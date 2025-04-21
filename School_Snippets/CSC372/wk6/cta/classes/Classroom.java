package classes;

import java.util.ArrayList;

/**
 * A class to demonstrate the functionality of the Student and sorting classes.
 */
public class Classroom {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(10, "Alice", "New York"));
        students.add(new Student(4, "Bob", "Los Angeles"));
        students.add(new Student(8, "Charlie", "Chicago"));
        students.add(new Student(1, "Diana", "Houston"));
        students.add(new Student(5, "Ethan", "Phoenix"));
        students.add(new Student(3, "Fiona", "Philadelphia"));
        students.add(new Student(9, "George", "San Antonio"));
        students.add(new Student(2, "Hannah", "San Diego"));
        students.add(new Student(7, "Ian", "Dallas"));
        students.add(new Student(6, "Jasmine", "San Jose"));

        System.out.println("\nOriginal list:");
        for (Student s : students) System.out.println(s);

        SelectionSorter.selectionSort(students, new GenericComparator("name"));
        System.out.println("\nSorted by name:");
        for (Student s : students) System.out.println(s);

        SelectionSorter.selectionSort(students, new GenericComparator("rollno"));
        System.out.println("\nSorted by roll number:");
        for (Student s : students) System.out.println(s);

        SelectionSorter.selectionSort(students, new GenericComparator("address"));
        System.out.println("\nSorted by address:");
        for (Student s : students) System.out.println(s);
    }
}