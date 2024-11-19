package wk2.discussion;

public class Child {
    private String firstName;
    private String lastName;
    private int age;

    public Child(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void printInfo() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
    }
    public void printSummary() {
        System.out.println("Name: " + Utils.concatName(firstName, lastName) + ", Age: " + age);
    }
    public String getFullName() {
        return Utils.concatName(firstName, lastName);
    }
}
