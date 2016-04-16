package layouts;

import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public final class FormLayoutExample {
    /**
     * No instances.
     */
    private FormLayoutExample() {
        // empty
    }

    /**
     * 
     */
    private JComponent createMainPanel() {
        final CellConstraints cc = new CellConstraints();
        final FormLayout layout = new FormLayout(
                "pref, 4dlu, max(150px;pref):grow, 4dlu, min", // columns
                "pref, 2dlu, pref, 2dlu, pref"                 // rows
                );

        layout.setRowGroups(new int [][] {{1, 3, 5}});

        // Try this :)
        // final JPanel panel = new FormDebugPanel();

        final JPanel panel = new JPanel();
        panel.setLayout(layout);

        panel.add(new JLabel("Label 1"), cc.xy(1, 1));
        panel.add(new TextField(), cc.xyw(3, 1, 3));

        panel.add(new JLabel("Label 2"), cc.xy(1, 3));
        panel.add(new TextField(), cc.xy(3, 3));

        panel.add(new JLabel("Label 3"), cc.xy(1, 5));
        panel.add(new TextField(), cc.xy(3, 5));

        panel.add(new JButton("..."), cc.xy(5, 5));

        panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        return panel;
    }

    /**
     * Command line entry point.
     * 
     * @param args Command line arguments.
     */
    public static void main(String [] args) {
        final FormLayoutExample instance = new FormLayoutExample();

        final JFrame frame = new JFrame(FormLayoutExample.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
