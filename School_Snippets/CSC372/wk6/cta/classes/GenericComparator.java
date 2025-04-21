package classes;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * A generic comparator using reflection to compare Student Class fields dynamically.
 * This comparator accepts a field name (e.g., "name", "rollno", "address") and
 * compares two Student Class instances based on the values of that field.
 * The field must exist and be Comparable, or an exception will be thrown.
 */
public class GenericComparator implements Comparator<Student> {

    /**
     * The name of the field to compare.
     */
    private final String fieldName;

    /**
     * The reflected field from the Student class used for comparison.
     */
    private final Field field;

    /**
     * Constructs a GenericComparator for the specified field name.
     *
     * @param fieldName the name of the field to compare (must be declared in Student Class)
     * @throws IllegalArgumentException if the field name is null, empty, or not a valid field in Student
     */
    public GenericComparator(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field cannot be null or empty.");
        }

        try {
            this.field = Student.class.getDeclaredField(fieldName);
            this.field.setAccessible(true);
            this.fieldName = fieldName;
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Invalid field: " + fieldName);
        }
    }

    /**
     * Compares two Student Class objects based on the configured field.
     *
     * @param a the first student to compare
     * @param b the second student to compare
     * @return a negative integer, zero, or a positive integer as the first argument is less than,
     * equal to, or greater than the second
     * @throws IllegalArgumentException if either student is null, or if the field is not comparable
     * @throws RuntimeException         if the field cannot be accessed
     */
    @Override
    public int compare(Student a, Student b) {
        try {
            Object valA = field.get(a);
            Object valB = field.get(b);

            if (valA instanceof Comparable<?> && valB instanceof Comparable<?>) {
                @SuppressWarnings("unchecked")
                Comparable<Object> compA = (Comparable<Object>) valA;
                return compA.compareTo(valB);
            } else {
                throw new IllegalArgumentException("Field '" + fieldName + "' is not comparable.");
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to access field: " + fieldName, e);
        }
    }
}
