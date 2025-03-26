import javax.swing.*;

public class DialogueSample {
    public static void main(String[] args) {
        JDialog dialog = new JDialog((JFrame) null, "Dialog Without Frame", true);
        dialog.setSize(250, 120);

        JPanel panel = new JPanel();
        panel.add(new JLabel("This dialog has no frame!"));

        JButton button = new JButton("OK");
        panel.add(button);

        button.addActionListener(e -> dialog.dispose());

        dialog.add(panel);

        dialog.setLocationRelativeTo(null); // To center on screen
        dialog.setVisible(true);

        dialog.add(new JLabel("Notice label without panel is not visible"));
        dialog.add(panel);
    }
}
