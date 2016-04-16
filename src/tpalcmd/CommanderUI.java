package tpalcmd;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	final Path C = Paths.get("C:/");
	final Path D = Paths.get("D:/");
	
	    @SuppressWarnings("serial")
		class MyTableModel extends AbstractTableModel 
		{
	    		    	
	    	SimpleDateFormat sdf;
	    	final int columns=5;
	    	int rows;
	    	
	    	final String[] columnNames = {"","File Name", "File Type","Size", "Last modified"}; 
	    	Object data[][];
		    
	    	
		        
		    MyTableModel()
		    {
		    	sdf = new SimpleDateFormat("dd-MM-yyyy");
		    	update(new File("C:/"));
		    }
		    
		    public void update(File file)
		    {
		    	File[] fileList=file.listFiles();
		    	rows=fileList.length;
		    	data= new Object[rows][columns];
		    	
		    	for (int i=0; i<rows;i++)
		    	{
		    		File tmp = fileList[i];		    		
		    		
		    		String[] fileName= tmp.getName().split("\\.");
		    		if (fileName!=null)
		    		{
		    		data[i][0]="";
		    		data[i][1]=fileName[0];
		    		if (tmp.isDirectory())data[i][2]="<DIR>";
		    		else data[i][2]=fileName[1];
		    		data[i][3]=tmp.length();
		    		if (tmp.lastModified()==0)data[i][4]="";
		    		else data[i][4]=sdf.format(tmp.lastModified());
		    		}
		    		
		    	}
		    	
		    	
		    	
		    }
		    
			@Override
			public int getColumnCount() {
				return columns;
			}
			
			
			@Override
			public String getColumnName(int column)
			{
				return columnNames[column];
			}

			@Override
			public int getRowCount() {
				return rows;
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
			tableLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableLeft.setShowGrid(false);
			tableLeft.setBackground(Color.WHITE);
			
			JTable tableRight = new JTable(new MyTableModel());
			tableRight.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableRight.setShowGrid(false);
			
			JComboBox comboBoxLeft = new JComboBox();
			comboBoxLeft.setEditable(true);
			comboBoxLeft.addItem(C);
			comboBoxLeft.addItem(D);
			
			JComboBox comboBoxRight = new JComboBox();
			comboBoxRight.setEditable(true);
			comboBoxRight.addItem(C);
			comboBoxRight.addItem(D);
			
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
