import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class HueApp {
    private static JTextPane textPane;
    private static Color randomGreen;

    // Exit confirmation dialog
    private static void showExitConfirmationDialog(JFrame frame) {
        int choice = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to exit?\n",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }

        // Append centered text to JTextPane
    private static void appendCenteredText(String text) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center, 20);
        StyleConstants.setItalic(center, true);
        StyleConstants.setForeground(center, textPane.getForeground());

        try {
            int start = doc.getLength();
            doc.insertString(start, text, center);
            doc.setParagraphAttributes(start, text.length(), center, false);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    // Generate a green hue
    private static Color generateRandomGreenHue() {
        Random rand = new Random();
        int r = rand.nextInt(50);
        int g = 150 + rand.nextInt(106);
        int b = rand.nextInt(100);
        return new Color(r, g, b);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hue App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        textPane = new JTextPane();
        textPane.setEditable(false);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Ensure it's not too small (when it's too small scrolling is needed to see all buttons)

        centerPanel.add(scrollPane);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Navigation bar
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JButton showDateTimeBtn = new JButton("1. Show Date & Time");
        JButton saveToFileBtn = new JButton("2. Save to log.txt");
        JButton changeColorBtn = new JButton("3. Green Background");
        JButton exitBtn = new JButton("4. Exit");

        navBar.add(showDateTimeBtn);
        navBar.add(saveToFileBtn);
        navBar.add(changeColorBtn);
        navBar.add(exitBtn);
        frame.add(navBar, BorderLayout.NORTH);

        // Option 1: Show Date & Time (append, centered)
        showDateTimeBtn.addActionListener(e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String now = "Current Date & Time: " + LocalDateTime.now().format(formatter);
            appendCenteredText(now + "\n");
        });

        // Option 2: Save to log.txt
        saveToFileBtn.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("log.txt")) {
                writer.write(textPane.getText());
                JOptionPane.showMessageDialog(frame, "Text saved to log.txt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error writing to file: " + ex.getMessage());
            }
        });

        // Option 3: Random green background
        changeColorBtn.addActionListener(e -> {
            randomGreen = generateRandomGreenHue();
            centerPanel.setBackground(randomGreen);
            scrollPane.getViewport().setBackground(randomGreen);
            textPane.setBackground(randomGreen);
        });

        // Option 4: Exit confirmation
        exitBtn.addActionListener(e -> showExitConfirmationDialog(frame));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
