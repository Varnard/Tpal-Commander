package layouts;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class BorderLayoutExample {
    /**
     * No instances.
     */
    private BorderLayoutExample() {
        // empty
    }

    /**
     * 
     */
    private JComponent createMainPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(createButton("W", 50, 50), BorderLayout.WEST);
        panel.add(createButton("E", 50, 50), BorderLayout.EAST);
        panel.add(createButton("S", 50, 50), BorderLayout.SOUTH);
        panel.add(createButton("N", 50, 50), BorderLayout.NORTH);
        panel.add(createButton("C", 50, 50), BorderLayout.CENTER);

        return panel;
    }

    /**
     * 
     */
    private JButton createButton(String text, int width, int height) {
        final JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    /**
     * Command line entry point.
     * 
     * @param args Command line arguments.
     */
    public static void main(String [] args) {
        final BorderLayoutExample instance = new BorderLayoutExample();

        final JFrame frame = new JFrame(BorderLayoutExample.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
