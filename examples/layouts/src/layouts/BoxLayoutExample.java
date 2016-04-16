package layouts;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class BoxLayoutExample {
    /**
     * No instances.
     */
    private BoxLayoutExample() {
        // empty
    }

    /**
     * 
     */
    private JComponent createMainPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(createCenterButton("Button 1"));
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(createCenterButton("Button 2"));
        panel.add(createCenterButton("Button 3"));
        panel.add(Box.createVerticalGlue());
        panel.add(createCenterButton("Long-Named Button 4"));
        panel.add(Box.createVerticalGlue());
        panel.add(createCenterButton("5"));

        return panel;
    }

    /**
     * 
     */
    private static JButton createCenterButton(String text) {
        final JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        return button;
    }

    /**
     * Command line entry point.
     * 
     * @param args Command line arguments.
     */
    public static void main(String [] args) {
        final BoxLayoutExample instance = new BoxLayoutExample();

        final JFrame frame = new JFrame(BoxLayoutExample.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
