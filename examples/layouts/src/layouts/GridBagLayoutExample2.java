package layouts;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class GridBagLayoutExample2 {
    /**
     * No instances.
     */
    private GridBagLayoutExample2() {
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
        cc.weightx = 1;
        cc.weighty = 1;
        cc.fill = GridBagConstraints.BOTH;
        gridbag.setConstraints(button1, cc);
        panel.add(button1);

        final JButton button2 = new JButton("2");
        cc.weightx = 5;
        cc.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(button2, cc);
        panel.add(button2);

        final JButton button3 = new JButton("3");
        cc.weightx = 1;
        cc.insets = new Insets(10, 5, 10, 5);
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
        final GridBagLayoutExample2 instance = new GridBagLayoutExample2();

        final JFrame frame = new JFrame(GridBagLayoutExample2.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
