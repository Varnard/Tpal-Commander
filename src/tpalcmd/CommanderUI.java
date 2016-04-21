package tpalcmd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class CommanderUI {
	
	//TODO: skalowanie ui
	//TODO: buttony
	//TODO: obsluga plikow
	//TODO: lokalizacja
	//TODO: Sprzatnac

	    private CommanderUI() {
	    }
	    
	    private JComponent createMainPanel() {    	    	
	    				
			final FileViewPanel supPanelLeft = new FileViewPanel();		
			
			final FileViewPanel supPanelRight = new FileViewPanel();		
	        		
	        final JPanel filePanel = new JPanel();
	        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
			filePanel.add(supPanelLeft);
			filePanel.add(supPanelRight);
			filePanel.setBackground(Color.WHITE);
			
			final JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(new JButton("F3 View"));
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(new JButton("F4 Edit"));
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(new JButton("F5 Copy"));
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(new JButton("F6 Move"));
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(new JButton("F7 New Folder"));
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(new JButton("F8 Delete"));
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(new JButton("Alt + F4 Exit"));
			buttonPanel.add(Box.createHorizontalGlue());
			
			final JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(filePanel, BorderLayout.NORTH);
			mainPanel.add(buttonPanel, BorderLayout.SOUTH);
			
			return mainPanel;
		}
	    
	    private JMenuBar createMainMenuBar() {
	        final JMenu fileMenu = new JMenu("File");
	        final JMenuItem closeItem = new JMenuItem("Close");
	        closeItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
	        fileMenu.add(closeItem);
	        
	        final JMenu languageMenu = new JMenu("Language");
	        final JMenuItem english = new JMenuItem("English");
	        final JMenuItem polish = new JMenuItem("Polski");
	        languageMenu.add(polish);
	        languageMenu.add(english);
	        
	        final JMenuBar menuBar = new JMenuBar();
	    	menuBar.add(fileMenu);
	    	menuBar.add(languageMenu);
	    	menuBar.add(Box.createHorizontalGlue());
	    	menuBar.add(new JMenu("Help"));

	        return menuBar;
	    }

	    public static void main(String [] args) {
	        final CommanderUI instance = new CommanderUI();

	        final JFrame frame = new JFrame(CommanderUI.class.getName());	        
	        frame.getContentPane().add(instance.createMainPanel());
	        frame.setJMenuBar(instance.createMainMenuBar());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
	        frame.setVisible(true);
	    }
}
