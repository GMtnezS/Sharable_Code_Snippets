import javax.swing.*;
import java.awt.*;

public class BankBalanceApp {
    private static double balance = 0.0;
    private static JLabel balanceLabel;

    // Reusable InputDialogue, for InitalBalance Deposit and Withdrawal.
    private static void showInputDialogWithAction(String title, String message, java.util.function.Consumer<Double> onValidInput) {
        while (true) {
            JTextField amountInput = new JTextField(10);
            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(new JLabel(message));
            panel.add(amountInput);

            int result = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double amount = Double.parseDouble(amountInput.getText());
                    if (amount <= 0) throw new NumberFormatException();

                    // Call the custom action with the valid input
                    onValidInput.accept(amount);

                    // Only update the label if it has been initialized
                    if (balanceLabel != null) {
                        updateBalanceLabel();
                    }

                    break;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid positive number.");
                }
            } else {
                break;
            }
        }
    }

    // Exit confirmation dialog
    private static void showExitConfirmationDialog(JFrame frame) {
        int choice = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to exit?\nFinal Balance: $" + String.format("%.2f", balance),
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        // Step 1: Get Initial Balance
        // Initial balance input (required before app starts)
        showInputDialogWithAction(
            "Initial Balance",
            "Enter initial bank account balance:",
            amount -> balance = amount
        );

        // Step 2: Main Frame Setup
        JFrame frame = new JFrame("Bank Balance Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Step 3: Balance Display
        balanceLabel = new JLabel("Current Balance: $" + String.format("%.2f", balance), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        frame.add(balanceLabel, BorderLayout.NORTH);

        // Step 4: Buttons Panel (horizontal layout)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(exitBtn);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Step 5: Button Actions
        depositBtn.addActionListener(e ->
            showInputDialogWithAction(
                "Deposit", 
                "Enter amount to deposit:", 
                amount -> balance += amount
            )
        );
        withdrawBtn.addActionListener(e ->
            showInputDialogWithAction(
                "Withdraw", 
                "Enter amount to withdraw:", 
                amount -> {
                    if (amount > balance) {
                        JOptionPane.showMessageDialog(null, "Insufficient funds.");
                    } else {
                        balance -= amount;
                    }
                }
            )
        );
        exitBtn.addActionListener(e -> showExitConfirmationDialog(frame));

        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }


    // Update balance label text
    private static void updateBalanceLabel() {
        balanceLabel.setText("Current Balance: $" + String.format("%.2f", balance));
    }
}
