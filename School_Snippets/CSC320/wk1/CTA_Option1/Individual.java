package CTA_Option1;

public class Individual {
    // Attributes
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    // Constructor
    public Individual(String firstName, String lastName, String streetAddress, String city, String state, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    // Method to print information
    public void printInfo() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Street Address: " + streetAddress);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
        System.out.println("Zip Code: " + zipCode);
    }
}
