package layouts;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class GridBagLayoutExample1 {
    /**
     * No instances.
     */
    private GridBagLayoutExample1() {
        // empty
    }

    /**
     * 
     */
    private JComponent createMainPanel() {
        final GridBagLayout gridbag = new GridBagLayout();
        final GridBagConstraints cc = new GridBagConstraints();

        final JPanel panel = new JPanel();
        panel.setLayout(gridbag);

        final JButton button1 = new JButton("1");
        cc.weightx = 1.0;
        cc.weighty = 1.0;
        cc.fill = GridBagConstraints.BOTH;
        gridbag.setConstraints(button1, cc);
        panel.add(button1);

        final JButton button2 = new JButton("2");
        cc.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(button2, cc);
        panel.add(button2);
        
        final JButton button3 = new JButton("3");
        gridbag.setConstraints(button3, cc);
        panel.add(button3);

        return panel;
    }

    /**
     * Command line entry point.
     * 
     * @param args Command line arguments.
     */
    public static void main(String [] args) {
        final GridBagLayoutExample1 instance = new GridBagLayoutExample1();

        final JFrame frame = new JFrame(GridBagLayoutExample1.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
