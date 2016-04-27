package tpalcmd;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	FileViewPanel leftPanel;
	FileViewPanel rightPanel;
	
	String[] optionsText;
	String exitText;
	String exitTitle;
	String deleteText;
	String deleteTitle;
	String newFolderText;
	String newFolderTitle;
	JButton f3;
	JButton f4;
	JButton f5;
	JButton f6;
	JButton f7;
	JButton f8;
	JButton altf4;
	
	public ButtonPanel(Locale locale, FileViewPanel rightPanel, FileViewPanel leftPanel)
	{
		this.rightPanel=rightPanel;
		this.leftPanel=leftPanel;
		
		optionsText=new String[2];
		setLayout(new GridLayout(1,7));
		
		f3 = new JButton("F3 View");
		add(f3);	
		
		f4 = new JButton("F4 Edit");
		add(f4);
		
		f5 = new JButton("F5 Copy");
		add(f5);
		
		f6 = new JButton("F6 Move");
		add(f6);
		
		f7 = new JButton("F7 New Folder");
		add(f7);
		
		f8 = new JButton("F8 Delete");
		add(f8);
		
		altf4 = new JButton("Alt + F4 Exit");		
		add(altf4);
		
		updateLanguage(locale);

		addListeners();
		
		
	}
	public void updateLanguage(Locale locale)
	{
		ResourceBundle rb = ResourceBundle.getBundle("Language",locale);
		optionsText[0]=rb.getString("option0");
		optionsText[1]=rb.getString("option1");
		exitTitle=rb.getString("exitTitle");
		exitText=rb.getString("exitText");
		deleteTitle=rb.getString("deleteTitle");
		deleteText=rb.getString("deleteText");
		newFolderText=rb.getString("newFolderText");
		newFolderTitle=rb.getString("newFolderTitle");
		f3.setText(rb.getString("F3"));
		f4.setText(rb.getString("F4"));
		f5.setText(rb.getString("F5"));
		f6.setText(rb.getString("F6"));
		f7.setText(rb.getString("F7"));
		f8.setText(rb.getString("F8"));
		altf4.setText(rb.getString("altF4"));
		this.updateUI();
	}
	
	private void addListeners()
	{
		f3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("f3 pressed");				
			}
			
		});		
		
		f4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("f4 pressed");				
			}
			
		});	
		
		f5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("f5 pressed");				
			}
			
		});	
		
		f6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("f6 pressed");				
			}
			
		});	
		
		f7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();
				Object[] options = optionsText;					
				
				String name = (String)JOptionPane.showInputDialog(
	                    frame, newFolderText, newFolderTitle,
	                    JOptionPane.PLAIN_MESSAGE);
				
				FileManager.makeFolder(name);
			
			}
			
		});	
		
		f8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("f8 pressed");
				JFrame frame = new JFrame();
				Object[] options = optionsText;
				int n = JOptionPane.showOptionDialog(frame, deleteText, deleteTitle,
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
				if (n==0)
				{
					
				}
			}
			
		});	
		
		altf4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();
				Object[] options = optionsText;
				int n = JOptionPane.showOptionDialog(frame,	exitText, exitTitle,
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
				if (n==0)
				{
				System.exit(0);	
				}
			}
			
		});	
	}
	
}
