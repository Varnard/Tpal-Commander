package tpalcmd;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class FileViewPanel extends JPanel{
	
	private JTable table;	
	private ComboBoxEditor cBoxEditor;
	
	public FileViewPanel(Locale locale){		
	
	final MyTableModel tableModel = new MyTableModel(locale);
	table = new JTable(tableModel);
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table.setShowGrid(false);
	table.getColumnModel().getColumn(0).setPreferredWidth(0);
	table.getColumnModel().getColumn(1).setPreferredWidth(150);
	table.getColumnModel().getColumn(2).setPreferredWidth(50);
	table.getColumnModel().getColumn(3).setPreferredWidth(70);
	table.getColumnModel().getColumn(4).setPreferredWidth(80);
	table.setAutoCreateRowSorter(true);
	
	cBoxEditor= new BasicComboBoxEditor();			
	final JComboBox comboBox = new JComboBox();
	comboBox.setEditable(true);
	comboBox.setEditor(cBoxEditor);
	comboBox.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			File newFile = new File((String)cBoxEditor.getItem());
			if (tableModel.fileSelected(newFile))
			{
			tableModel.fireTableStructureChanged();
			table.updateUI();
			}
		}
	});
	
	File[] rootList = File.listRoots();
	for (File file : rootList)
	{
		comboBox.addItem(file.getPath());
	}
	
	setLayout(new BorderLayout());
	add(new JScrollPane(table), BorderLayout.CENTER);
	add(comboBox, BorderLayout.NORTH);
	
	table.addMouseListener(new MouseListener(){		

		@Override
		public void mouseClicked(MouseEvent e) {
		
			if (e.getClickCount()==2)
			{
				File selectedFile = new File(tableModel.getFilePath(table.getSelectedRow()));
				boolean needUpdate = tableModel.fileSelected(selectedFile);						
				if (needUpdate)
				{
					tableModel.fireTableStructureChanged();
					table.updateUI();
					cBoxEditor.setItem(selectedFile.getAbsolutePath());
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	});
	

}	
	
	public void updateLanguage(Locale locale)
	{
		MyTableModel mtm = (MyTableModel)table.getModel();
		mtm.setLanguage(locale);
		mtm.fileSelected(new File((String)cBoxEditor.getItem()));
		
		table.updateUI();
	}

}
