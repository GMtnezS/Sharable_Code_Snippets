package classes;

/**
 * Represents a student with a roll number, name, and address.
 */

public class Student {
    private String name;
    private String address;
    private double gpa;

    public Student(String name, String address, double gpa) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        if (address == null || address.isEmpty()) throw new IllegalArgumentException("Address cannot be empty.");
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");

        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getGpa() {
        return gpa;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) throw new IllegalArgumentException("Address cannot be empty.");
        this.address = address;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + " | " + address + " | GPA: " + gpa;
    }
}