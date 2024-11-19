package wk2.discussion;

public class Main {
    public static void main(String[] args) {
        // Create a Children list
        Children childrenList = new Children();

        // Adding a bunch of children to the list
        childrenList.addChild(new Child("John", "Doe", 10));
        childrenList.addChild(new Child("Jane", "Smith", 8));
        childrenList.addChild(new Child("Emily", "Davis", 12));
        childrenList.addChild(new Child("Michael", "Brown", 9));
        childrenList.addChild(new Child("Sarah", "Johnson", 11));
        childrenList.addChild(new Child("David", "Wilson", 7));
        childrenList.addChild(new Child("Anna", "Moore", 6));
        childrenList.addChild(new Child("Chris", "Taylor", 13));
        childrenList.addChild(new Child("Laura", "Anderson", 10));
        childrenList.addChild(new Child("Sophia", "Lee", 8));

        // Print the entire list of children
        System.out.println("List of Children:");
        childrenList.printInfo();

        // Get the info of the 5th child (index 4)
        System.out.println("\nDetails of the 5th child:\n");
        Child fifthChild = childrenList.getChild(4);

        // Print the info of the 5th child (index 4)
        fifthChild.printInfo();

        // Remove the 5th child (index 4) by index
        System.out.println("\nRemoving the 5th child...");
        childrenList.removeChild(4);

        System.out.println("\nThe 5th child was removed.");

        // Print the list after removals
        System.out.println("\nList of Children after removal of 5th child:");
        childrenList.printInfo();
    }
}
