import javax.swing.*;
import java.awt.*;

public class WindowSample {
    public static void main(String[] args) {
        JWindow window = new JWindow();

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Welcome to JWindow!");

        panel.add(label);
        window.add(panel);

        JButton button = new JButton("OK");
        panel.add(button);

        button.addActionListener(e -> window.dispose());

        // Set size and center on screen
        window.setSize(250, 120);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Automatically close after 10 seconds if button has not been pressed (wouldn't want it to be left hanging)
        Timer timer = new Timer(10000, e -> window.dispose());
        timer.setRepeats(false);
        timer.start();
    }
}
