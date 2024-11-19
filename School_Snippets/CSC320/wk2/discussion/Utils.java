package wk2.discussion;

public class Utils {

    public static String concatName(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First name and last name cannot be null");
        }
        return firstName.trim() + " " + lastName.trim();
    }
}
