package tpalcmd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

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
	//TODO: obsluga plikow
	//TODO: lokalizacja
	//TODO: Sprzatnac

	private static FileViewPanel supPanelLeft;		
	private static FileViewPanel supPanelRight;
	ButtonPanel buttonPanel;
	
	    private CommanderUI() {
	    }
	    
	    private JComponent createMainPanel() {    	    	
	    				 
	    	Locale locale = new Locale("EN");
	    	supPanelLeft = new FileViewPanel(locale, "left");
	    	supPanelRight= new FileViewPanel(locale, "right");	
	        		
	        final JPanel filePanel = new JPanel();
	        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
			filePanel.add(supPanelLeft);
			filePanel.add(supPanelRight);
			
			buttonPanel = new ButtonPanel(locale, supPanelLeft, supPanelRight);
			
			final JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(filePanel, BorderLayout.CENTER);
			mainPanel.add(buttonPanel, BorderLayout.SOUTH);
			
			return mainPanel;
		}
	    
	    private JMenuBar createMainMenuBar() {
	        final JMenu fileMenu;
	        final JMenuItem closeItem;
	        fileMenu = new JMenu("File");	     
	        closeItem = new JMenuItem("Close");
	        closeItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
	        fileMenu.add(closeItem);
	        final JMenu help = new JMenu("Help");
	        final JMenu languageMenu;
	        languageMenu = new JMenu("Language");
	        final JMenuItem english = new JMenuItem("English");
	        english.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					updateLanguage(new Locale("EN"));
					fileMenu.setText("File");
					closeItem.setText("Close");
					languageMenu.setText("Language");
					help.setText("Help");
				}
			});
	        final JMenuItem polish = new JMenuItem("Polski");
	        polish.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					updateLanguage(new Locale("PL"));
					fileMenu.setText("Plik");
					closeItem.setText("Zamknij");
					languageMenu.setText("Jêzyk");
					help.setText("Help");
				}
			});
	        languageMenu.add(polish);
	        languageMenu.add(english);
	        
	        final JMenuBar menuBar = new JMenuBar();
	    	menuBar.add(fileMenu);
	    	menuBar.add(languageMenu);
	    	menuBar.add(Box.createHorizontalGlue());
	    	menuBar.add(help);

	        return menuBar;
	    }

	    private void updateLanguage(Locale locale)
	    {
	    	supPanelLeft.updateLanguage(locale);
			supPanelRight.updateLanguage(locale);
			buttonPanel.updateLanguage(locale);	
	    }
	    
	    
	    public static void main(String [] args) {
	        final CommanderUI instance = new CommanderUI();
	        
	        final JFrame frame = new JFrame(CommanderUI.class.getName());	        
	        frame.getContentPane().add(instance.createMainPanel());
	        frame.setJMenuBar(instance.createMainMenuBar());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
	        frame.setVisible(true);
	        
	        FileManager.setLeftPanel(supPanelLeft);
	        FileManager.setRightPanel(supPanelRight);
	    }
}
