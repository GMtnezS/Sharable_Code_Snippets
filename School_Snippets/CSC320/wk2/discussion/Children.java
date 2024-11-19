package wk2.discussion;

import java.util.ArrayList;

public class Children {
    private ArrayList<Child> children;

    public Children() {
        this.children = new ArrayList<Child>();
    }

    // Add a child to the list
    public void addChild(Child child) {
        children.add(child);
    }

    // Get a child by index
    public Child getChild(int index) {
        if (index < 0 || index >= children.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return children.get(index);
    }

    // Print all children's info
    public void printInfo() {
        for (Child child : children) {
            child.printSummary();
        }
    }

    // Remove a child

    // Remove a child by index
    public void removeChild(int index) {
        if (index < 0 || index >= children.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        children.remove(index);
    }

    // Remove a child by FullName
    public boolean removeChildByName(String firstName, String lastName) {
        return children.removeIf(child -> 
            child.getFullName().equals(Utils.concatName(firstName, lastName))
        );
    }

}
