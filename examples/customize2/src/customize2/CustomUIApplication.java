package customize2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class CustomUIApplication {
	
    private JMenuBar createMainMenuBar() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem closeItem = new JMenuItem("Close");
        closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
        fileMenu.add(closeItem);
    	
        final JMenuBar menuBar = new JMenuBar();
    	menuBar.add(fileMenu);
    	menuBar.add(new JMenu("Edit"));
    	menuBar.add(Box.createHorizontalGlue());
    	menuBar.add(new JMenu("Help"));

        return menuBar;
    }

    private JButton createToolButton(String text) {
        final JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(42, 42));
        
        return button;
    }
    
    private JComponent createCenterPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(" Edit Window "));
        
        JTextArea textArea = new JTextArea("Sample text...");
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        return panel;
    }

    private JComponent createToolbarPanel() {
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(createToolButton("a"));
        panel.add(createToolButton("b"));
        panel.add(createToolButton("c"));
        panel.add(createToolButton("d"));
        panel.add(createToolButton("e"));

        return panel;
    }

    private JComponent createMainPanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(createToolbarPanel(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);

        return mainPanel;
    }
	
	private static void createAndShowGUI() {
	    final CustomUIApplication instance = new CustomUIApplication();

        final JFrame frame = new JFrame(instance.getClass().getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setJMenuBar(instance.createMainMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
	}
	
    public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
