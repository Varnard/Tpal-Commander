package tpalcmd;

import java.io.File;
import java.nio.file.Path;

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
			rightPanel.update();
			leftPanel.update();
		}
	}

}
