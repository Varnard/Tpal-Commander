package tpalcmd;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class CommanderUI {

	    @SuppressWarnings("serial")
		static class MyTableModel extends AbstractTableModel 
		{
	    	String[] columnNames = {"","File Name", "File Type",
		            "Size", "Last modified"};
		        Object[][] data = {
		        	{ "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false) },
		        	{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
		        	{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
		        	{ "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) },
		        	{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) }};
	    	
			@Override
			public int getColumnCount() {
				return 5;
			}
			
			
			@Override
			public String getColumnName(int column)
			{
				return columnNames[column];
			}

			@Override
			public int getRowCount() {
				return 5;
			}

			@Override
			public Object getValueAt(int row, int column) {
				return data[row][column];
			}
	    }
	    
	    private CommanderUI() {
	    }
	    

	    private JComponent createMainPanel() {        

			JTable tableLeft = new JTable(new MyTableModel());
			JTable tableRight = new JTable(new MyTableModel());
			tableLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableLeft.setShowGrid(false);
			tableRight.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableRight.setShowGrid(false);
			
			JComboBox comboBoxLeft = new JComboBox();
			comboBoxLeft.setEditable(true);
			JComboBox comboBoxRight = new JComboBox();
			comboBoxRight.setEditable(true);
			
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
