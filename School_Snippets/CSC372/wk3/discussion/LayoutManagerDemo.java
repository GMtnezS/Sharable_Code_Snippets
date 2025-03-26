import javax.swing.*;
import java.awt.*;

public class LayoutManagerDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Layout Manager Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Sample BoxLayout to stack panels VERTICALLY - also via .setLayout()
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // 1. FlowLayout via constructor
        JPanel flowPanel = new JPanel(new FlowLayout());
        flowPanel.setBorder(BorderFactory.createTitledBorder("FlowLayout (via constructor)"));
        flowPanel.add(new JButton("One"));
        flowPanel.add(new JButton("Two"));
        flowPanel.add(new JButton("Three"));

        // 2. BorderLayout via .setLayout()
        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout());
        borderPanel.setBorder(BorderFactory.createTitledBorder("BorderLayout (via setLayout)"));
        borderPanel.add(new JButton("North"), BorderLayout.NORTH);
        borderPanel.add(new JButton("Center"), BorderLayout.CENTER);
        borderPanel.add(new JButton("South"), BorderLayout.SOUTH);

        // 3. GridLayout via subclassing JPanel
        JPanel gridPanel = new GridPanel();
        gridPanel.setBorder(BorderFactory.createTitledBorder("GridLayout (via subclass)"));

        // Adding all panels to the container panel
        container.add(flowPanel);
        container.add(borderPanel);
        container.add(gridPanel);

        frame.add(container);
        frame.setVisible(true);
    }
}

// Custom panel with GridLayout that extends JPanel for via 3
class GridPanel extends JPanel {
    public GridPanel() {
        setLayout(new GridLayout(2, 2));
        add(new JButton("1"));
        add(new JButton("2"));
        add(new JButton("3"));
        add(new JButton("4"));
    }
}
