package classes;

/**
 * Represents a basic bank account with deposit, withdrawal, and balance tracking.
 */
public class BankAccount {
    private String firstName;
    private String lastName;
    private int accountID;
    protected double balance; // Protected so subclasses can access it

    /**
     * Constructs a new BankAccount with an initial balance of zero.
     *
     * @param firstName The account holder's first name.
     * @param lastName The account holder's last name.
     * @param accountID The unique account ID.
     * @throws IllegalArgumentException if firstName, lastName is null or empty, or accountID is negative.
     */
    public BankAccount(String firstName, String lastName, int accountID) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("First name and last name cannot be null or empty.");
        }
        if (accountID < 0) {
            throw new IllegalArgumentException("Account ID must be a positive integer.");
        }
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAccountID(accountID);
        this.balance = 0.0;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount The amount to deposit (must be positive).
     * @throws IllegalArgumentException if the amount is negative or zero.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposited $" + amount + ". New balance: $" + balance);
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount The amount to withdraw (must be positive and <= balance).
     * @throws IllegalArgumentException if amount is negative or exceeds balance.
     */
    public void withdrawal(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal.");
        }
        balance -= amount;
        System.out.println("Withdrawn $" + amount + ". New balance: $" + balance);
    }

    // Getters & Setters with validation

    /**
     * Gets the account holder's first name.
     *
     * @return The first name.
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the account holder's first name.
     *
     * @param firstName The new first name (must not be null or empty).
     * @throws IllegalArgumentException if firstName is null or empty.
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        this.firstName = firstName;
    }

    /**
     * Gets the account holder's last name.
     *
     * @return The last name.
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the account holder's last name.
     *
     * @param lastName The new last name (must not be null or empty).
     * @throws IllegalArgumentException if lastName is null or empty.
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        this.lastName = lastName;
    }

    /**
     * Gets the account ID.
     *
     * @return The account ID.
     */
    public int getAccountID() { return accountID; }

    /**
     * Sets the account ID.
     *
     * @param accountID The new account ID (must be a positive integer).
     * @throws IllegalArgumentException if accountID is negative.
     */
    public void setAccountID(int accountID) {
        if (accountID < 0) {
            throw new IllegalArgumentException("Account ID must be a positive integer.");
        }
        this.accountID = accountID;
    }

    /**
     * Gets the current account balance.
     *
     * @return The balance.
     */
    public double getBalance() { return balance; }

    /**
     * Prints the account summary, including name, account ID, and balance.
     */
    public void accountSummary() {
        System.out.println("Account Summary:");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Account ID: " + accountID);
        System.out.println("Balance: $" + balance);
    }
}
