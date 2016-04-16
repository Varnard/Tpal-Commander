package tpalcmd;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

public class CommanderUI {

	String C ="C:/";
	String D = "D:/";
	
   
	    private CommanderUI() {
	    }
	    

	    private JComponent createMainPanel() {        

	    	final MyTableModel leftTableModel = new MyTableModel();
			final JTable tableLeft = new JTable(leftTableModel);
			tableLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableLeft.setShowGrid(false);
			tableLeft.getColumnModel().getColumn(0).setPreferredWidth(0);
			tableLeft.getColumnModel().getColumn(1).setPreferredWidth(150);
			tableLeft.getColumnModel().getColumn(2).setPreferredWidth(50);
			tableLeft.getColumnModel().getColumn(3).setPreferredWidth(70);
			tableLeft.getColumnModel().getColumn(4).setPreferredWidth(80);
			
			final MyTableModel rightTableModel = new MyTableModel();
			final JTable tableRight = new JTable(rightTableModel);
			tableRight.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableRight.setShowGrid(false);
			tableRight.getColumnModel().getColumn(0).setPreferredWidth(0);
			tableRight.getColumnModel().getColumn(1).setPreferredWidth(150);
			tableRight.getColumnModel().getColumn(2).setPreferredWidth(50);
			tableRight.getColumnModel().getColumn(3).setPreferredWidth(70);
			tableRight.getColumnModel().getColumn(4).setPreferredWidth(80);
			
			final JComboBox comboBoxLeft = new JComboBox();
			comboBoxLeft.setEditable(true);
			comboBoxLeft.addItem(C);
			comboBoxLeft.addItem(D);
			comboBoxLeft.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					File newFile = new File((String)comboBoxLeft.getSelectedItem());
					if (newFile.isDirectory())
					{
					leftTableModel.update(newFile);
					tableLeft.updateUI();
					}
				}
			});
			
			
			final JComboBox comboBoxRight = new JComboBox();
			comboBoxRight.setEditable(true);
			comboBoxRight.addItem(C);
			comboBoxRight.addItem(D);
			comboBoxRight.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							String pathName = (String)comboBoxRight.getSelectedItem();
							rightTableModel.update(new File(pathName));
							tableRight.updateUI();
						}
					});
			
			final JPanel supPanelLeft = new JPanel();
			supPanelLeft.setLayout(new BorderLayout());
			supPanelLeft.add(new JScrollPane(tableLeft), BorderLayout.CENTER);
			supPanelLeft.add(comboBoxLeft, BorderLayout.NORTH);
			
		
			
			final JPanel supPanelRight = new JPanel();
			supPanelRight.setLayout(new BorderLayout());
			supPanelRight.add(new JScrollPane(tableRight), BorderLayout.CENTER);
			supPanelRight.add(comboBoxRight, BorderLayout.NORTH);
			
	        
	        final JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.add(supPanelLeft);
			panel.add(supPanelRight);
			panel.setBackground(Color.WHITE);
			return panel;
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
