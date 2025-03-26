import javax.swing.*;

public class FrameSample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame Only Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a button directly to the frame without panel
        JButton button = new JButton("Click Me (Frame)");
        button.addActionListener((e) -> frame.dispose());

        frame.add(button);

        frame.setLocationRelativeTo(null); // To center on screen

        frame.setVisible(true);
    }
}
