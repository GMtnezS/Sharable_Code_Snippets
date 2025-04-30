package classes;

import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to demonstrate the functionality of the Student and sorting classes.
 */
public class Classroom {
    private static final LinkedList<Student> students = new LinkedList<>();

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            run(new Scanner(args[1]));
        } else {
            Scanner scanner = new Scanner(System.in);
            run(scanner);
            scanner.close();
        }
    }

    public static void run(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Student Inventory Menu ===");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Update student GPA or address");
            System.out.println("4. Remove student");
            System.out.println("5. Export sorted list to file");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addStudent(scanner);
                case "2" -> viewStudents();
                case "3" -> updateStudent(scanner);
                case "4" -> removeStudent(scanner);
                case "5" -> exportStudents();
                case "6" -> exit = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        double gpa = promptGpa(scanner);

        students.add(new Student(name, address, gpa));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the inventory.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter name of student to update: ");
        String name = scanner.nextLine();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.print("New address (leave blank to skip): ");
                String address = scanner.nextLine();
                if (!address.isBlank()) student.setAddress(address);

                System.out.print("New GPA (leave blank to skip): ");
                String gpaInput = scanner.nextLine();
                if (!gpaInput.isBlank()) {
                    try {
                        double gpa = Double.parseDouble(gpaInput);
                        if (gpa >= 0.0 && gpa <= 4.0) {
                            student.setGpa(gpa);
                        } else {
                            System.out.println("Invalid GPA. Must be between 0.0 and 4.0. GPA not updated.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. GPA not updated.");
                    }
                }
            }
        }

        System.out.println("Student updated.");
        return;
}


    private static void removeStudent(Scanner scanner) {
        System.out.print("Enter name of student to remove: ");
        String name = scanner.nextLine();
        students.removeIf(s -> s.getName().equalsIgnoreCase(name));
        System.out.println("If present, student has been removed.");
    }

    private static void exportStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to export.");
            return;
        }
        SelectionSorter.selectionSort(students, new GenericComparator("name"));
        try (FileWriter writer = new FileWriter("students_sorted_by_name.txt")) {
            for (Student student : students) {
                writer.write(student.toString() + "\n");
            }
            System.out.println("Student list written to students_sorted_by_name.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static double promptGpa(Scanner scanner) {
        while (true) {
            System.out.print("GPA: ");
            try {
                double gpa = Double.parseDouble(scanner.nextLine());
                if (gpa >= 0.0 && gpa <= 4.0) return gpa;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid GPA. Please enter a number between 0.0 and 4.0.");
        }
    }

    public static void clearStudents() {
        students.clear();
    }

}