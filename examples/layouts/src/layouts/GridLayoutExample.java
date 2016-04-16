package layouts;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class GridLayoutExample {
    /**
     * No instances.
     */
    private GridLayoutExample() {
        // empty
    }

    /**
     * 
     */
    private JComponent createMainPanel() {
        final GridLayout layout = new GridLayout(2, 3);
        layout.setHgap(5);
        layout.setVgap(5);
    	
    	final JPanel panel = new JPanel();
        panel.setLayout(layout);

        panel.add(new JButton("Button 1"));
        panel.add(new JButton("Button 2"));
        panel.add(new JButton("Button 3"));
        panel.add(new JButton("Long-Named Button 4"));
        panel.add(new JButton("5"));

        return panel;
    }

    /**
     * Command line entry point.
     * 
     * @param args Command line arguments.
     */
    public static void main(String [] args) {
        final GridLayoutExample instance = new GridLayoutExample();

        final JFrame frame = new JFrame(GridLayoutExample.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
