package classes; 

public class CheckingAccount extends BankAccount {
    private double interestRate;
    private static final double OVERDRAFT_FEE = 30.0;

    // Constructor with interest rate
    public CheckingAccount(String firstName, String lastName, int accountID, double interestRate) {
        super(firstName, lastName, accountID);
        this.interestRate = interestRate;
    }

    // Overriding withdrawal to allow overdrafts with a fee
    public void processWithdrawal(double amount) {
        if (amount <= balance) {
            withdrawal(amount);
        } else {
            balance -= (amount + OVERDRAFT_FEE);
            System.out.println("Overdraft! Withdrawn $" + amount + " with a $" + OVERDRAFT_FEE + " fee. New balance: $" + balance);
        }
    }

    public double getInterestRate() {
        return interestRate;
    }

    // Display all superclass attributes + interest rate
    public void displayAccount() {
        accountSummary();
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}
