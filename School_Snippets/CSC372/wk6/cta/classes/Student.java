package classes;

/**
 * Represents a student with a roll number, name, and address.
 */
public class Student {
    private int rollno;
    private String name;
    private String address;

    /**
     * Constructs a Student object with the specified roll number, name, and address.
     *
     * @param rollno  the roll number of the student
     * @param name    the name of the student
     * @param address the address of the student
     */
    public Student(int rollno, String name, String address) {
        if (rollno <= 0) {
            throw new IllegalArgumentException("Roll number must be positive.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.rollno = rollno;
        this.name = name;
        this.address = address;
    }

    public int getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return rollno + " | " + name + " | " + address;
    }
}