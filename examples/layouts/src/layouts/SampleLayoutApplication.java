package layouts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class SampleLayoutApplication {
    /**
     * No instances.
     */
    private SampleLayoutApplication() {
        // empty
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
     * 
     */
    private JComponent createNorthPanel() {
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.red);
        
        panel.add(createButton("a", 40, 40));
        panel.add(createButton("b", 40, 40));
        panel.add(createButton("c", 40, 40));
        panel.add(createButton("d", 40, 40));
        panel.add(createButton("e", 40, 40));
        panel.add(createButton("f", 40, 40));
        panel.add(createButton("g", 40, 40));
        panel.add(createButton("h", 40, 40));

        return panel;
    }

    /**
     * 
     */
    private JComponent createCenterPanel() {
        final JLabel label = new JLabel();
        label.setBackground(Color.white);
        
        return label;
    }

    /**
     * 
     */
    private JComponent createSouthPanel() {
        final JButton button = new JButton("<html><u>Close</u><br><b>window</html>");
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        
        return button;
    }

    /**
     * 
     */
    private JComponent createMainPanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(createNorthPanel(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createSouthPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    /**
     * 
     */
	private static void createAndShowGUI() {
        final SampleLayoutApplication instance = new SampleLayoutApplication();

        final JFrame frame = new JFrame(SampleLayoutApplication.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
	}
	
    /**
     * Command line entry point.
     * 
     * @param args Command line arguments.
     */
    public static void main(String [] args) {
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (Exception e) {
		    throw new RuntimeException(e); 
		}

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
