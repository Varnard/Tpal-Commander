package layouts;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * An example of a {@link FlowLayout}.
 * 
 * @author Dawid Weiss
 */
public final class FlowLayoutExample {
    /**
     * No instances.
     */
    private FlowLayoutExample() {
        // empty
    }

    /**
     * 
     */
    private JComponent createMainPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        panel.add(createButton("a", 50, 50));
        panel.add(createButton("b", 100, 50));
        panel.add(createButton("c", 50, 100));
        panel.add(createButton("d", 125, 75));
        
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
        final FlowLayoutExample instance = new FlowLayoutExample();

        final JFrame frame = new JFrame(FlowLayoutExample.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
