package tpalcmd;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Path;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class FileManager {
	private final static SecurityManager sm = System.getSecurityManager();
	private static FileViewPanel leftPanel;
	private static FileViewPanel rightPanel;
	private static Locale locale;
	
	private FileManager(){};
	
	public static void setRightPanel(FileViewPanel right)
	{
		rightPanel=right;
	}
	
	public static void setLeftPanel(FileViewPanel left)
	{
		leftPanel=left;
	}	
	
	public static void setLocale(Locale newLocale)
	{
		locale=newLocale;
	}
	
	
	public static SecurityManager getSecurityManager() 
	{
		return sm;
	}

	public static void setActivePanel(String which)
	{
		if (which.equals("left"))
		{
			leftPanel.setActive();
			rightPanel.setInactive();
		}
		
		if (which.equals("right"))
		{
			leftPanel.setInactive();
			rightPanel.setActive();
		}
	}
	
	public static void refresh()
	{
		leftPanel.update();
		rightPanel.update();
	}
	
    public static void openFile(File file)
    {
		try
		{
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");          
        }
         
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);      

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
    
    public static void openFiles()
    {
    	File[] files;
		if (rightPanel.isActive())
		{
			files = rightPanel.getSelectedFiles();
		}
		else
		{
			files = leftPanel.getSelectedFiles();
		}
		
		for (File file : files)
		{
			openFile(file);
		}
    }
    
    public static void editFiles()
    {
    	File[] files;
		if (rightPanel.isActive())
		{
			files = rightPanel.getSelectedFiles();
		}
		else
		{
			files = leftPanel.getSelectedFiles();
		}
		
		for (File file : files)
		{
			openFile(file);
		}
    }
	
	public static void makeFolder(String folderName)
	{		
		String baseName;
		
		if (rightPanel.isActive())
		{
			baseName = rightPanel.getFolder();
		}
		else
		{
			baseName = leftPanel.getFolder();
		}
		
		if (new File(baseName).getParentFile()!=null) baseName+="\\";
		
		File newFolder = new File(baseName+folderName);
		if (!newFolder.exists())
		{
			try
			{
			newFolder.mkdir();
			refresh();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteFiles(File[] files)
	{
	
		for (File file : files)
		{
			delete(file);
		}
		refresh();
	}
	
	public static void deleteSelectedFiles()
	{
		File[] files;
		if (rightPanel.isActive())
		{
			files = rightPanel.getSelectedFiles();
		}
		else
		{
			files = leftPanel.getSelectedFiles();
		}
		
		for (File file : files)
		{
			delete(file);
		}
		refresh();
	}
	
	
	
	public static void moveFiles()
	{
		File[] files;
		if (rightPanel.isActive())
		{
			files = rightPanel.getSelectedFiles();
		}
		else
		{
			files = leftPanel.getSelectedFiles();
		}
		if (files.length>0)
		{
		final File[] filesToMove=files;
        SwingUtilities.invokeLater(new Runnable()
        {   
            @Override
            public void run()
            {
                new FileMover(filesToMove, locale).setVisible(true);        		
            }
        });
		}
		
	}
	
	public static void copyFiles()
	{
		File[] files;
		if (rightPanel.isActive())
		{
			files = rightPanel.getSelectedFiles();
		}
		else
		{
			files = leftPanel.getSelectedFiles();
		}
		if (files.length>0)
		{
		final File[] filesToCopy=files;
        SwingUtilities.invokeLater(new Runnable()
        {   
            @Override
            public void run()
            {
                new FileCopier(filesToCopy, locale).setVisible(true);
        		
            }
        });
		}
		
	}
	
	private static void delete(final File file)
	{               
		new SwingWorker<Object, Void>() {
        public Object doInBackground()
        {
        	if (file.isDirectory())
        	{
        		File[] subFiles = file.listFiles();
         		for(File subFile : subFiles)
         		{
         		delete(subFile);
         		}
         		try
         		{
        			if (file.delete());
        			else
        			{
        				JFrame frame = new JFrame();
        				JOptionPane.showMessageDialog(frame, "Cannot delete the file");
        			}
        		}
        			catch (Exception e)
         		{
        			e.printStackTrace();        			
        		}
        	}
        	else
        	{
        		try 
        		{
        			if (file.delete());
        			else
        			{
        				JFrame frame = new JFrame();
        				JOptionPane.showMessageDialog(frame, "Cannot delete the file");
        			}
        			
            	} 
            		catch (Exception e)
        		{
            		e.printStackTrace();               	
        		}
        	}
            
            return null;
        }

        // this method is called on the main event dispatching thread
        public void done() {
        	
    		refresh();
 
        }
    }.execute();
				

	}
}
