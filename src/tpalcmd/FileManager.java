package tpalcmd;

import java.io.File;
import java.nio.file.Path;

import javax.swing.SwingUtilities;

public class FileManager {
	
	private static FileViewPanel leftPanel;
	private static FileViewPanel rightPanel;
	
	private FileManager(){};
	
	public static void setRightPanel(FileViewPanel right)
	{
		rightPanel=right;
	}
	
	public static void setLeftPanel(FileViewPanel left)
	{
		leftPanel=left;
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
			newFolder.mkdir();
			refresh();
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
                new FileMover(filesToMove).setVisible(true);        		
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
                new FileCopier(filesToCopy).setVisible(true);
        		
            }
        });
		}
		
	}
	
	private static void delete(File file)
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
				file.delete();
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
				file.delete();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}		
		refresh();
	}

}
