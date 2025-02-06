package wk4.discussion;

import java.util.EmptyStackException;
import java.util.Scanner;

public class BrowserHistory {
    private final Stack<String> historyStack;

    public BrowserHistory() {
        historyStack = new Stack<>();
    }

    public void visitPage(String url) {
        historyStack.push(url);
        System.out.println("Visited: " + url);
    }

    public void goBack() {
        try {
            historyStack.pop();
            System.out.println(historyStack.isEmpty() ? 
                "No previous pages in history." : "Back to: " + historyStack.peek());
        } catch (EmptyStackException e) {
            System.out.println("No history to go back to.");
        }
    }

    public void currentUrl() {
        try {
            System.out.println("Current page: " + historyStack.peek());
        } catch (EmptyStackException e) {
            System.out.println("No pages visited yet.");
        }
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("--- Browser History Menu ---");
            System.out.println("1. Visit a new page");
            System.out.println("2. Go back");
            System.out.println("3. Show current page");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter URL: ");
                    String url = scanner.nextLine();
                    browser.visitPage(url);
                    break;
                case 2:
                    browser.goBack();
                    break;
                case 3:
                    browser.currentUrl();
                    break;
                case 4:
                    System.out.println("Exiting browser history simulation.");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
            System.out.println("\n    --- ---    \n");
            
        } while (choice != 4);

        scanner.close();
    }
}
