package tpalcmd;

import java.awt.Desktop;
import java.io.File;
import java.io.FilePermission;
import java.text.SimpleDateFormat;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel 
{
		    	
	SimpleDateFormat sdf;
	final int columns=5;
	int rows;
	
	final String[] columnNames = {"","File Name", "File Type","Size", "Last modified"}; 
	Object data[][];
	String filePaths[];	
        
    MyTableModel()
    {
    	sdf = new SimpleDateFormat("dd-MM-yyyy");
    	fileSelected(new File("C:/"));
    }
    
    public boolean fileSelected(File file)
    {
    	boolean needUpdate=true;
   
    	if (file.canRead())
    	{
    		
    	if(!file.isDirectory())
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
            needUpdate=false;
    		}
    	

    	else
    	{
    	boolean isNotRoot=(file.getParentFile()!=null);
    	
    	File[] fileList=file.listFiles();

    	fileList=removeHidden(fileList);
    	
    	if (isNotRoot)
    	{        	
    		File[] newFileList= new File[fileList.length+1];
    		newFileList[0]=file.getParentFile();
        	for (int j=0;j<fileList.length;j++)
        	{
        		newFileList[j+1]=fileList[j];        		
        	}
        	fileList=newFileList;
    	}
    	
		rows=fileList.length;
		filePaths = new String[rows]; 
		data= new Object[rows][columns];
    	
    	
    	
    	for (int i=0; i<rows ;i++)
    	{
    		File tmp = fileList[i];
    		filePaths[i] = tmp.getAbsolutePath();    		
    		
    		
    		if (tmp.getName()!=null)
    		{
    		Icon icon = FileSystemView.getFileSystemView().getSystemIcon(tmp);
    		data[i][0]=icon;    		
    		if (tmp.isDirectory())
    			{
    			data[i][1]=tmp.getName();
    			data[i][2]="<DIR>";
    			data[i][3]="";
    			}
    		else 
    			{
    			String[] fileName= tmp.getName().split("\\.");
    			if (fileName.length>1 && !(fileName[0].equals("")))
    				{
    				data[i][1]=fileName[0];
    				data[i][2]=fileName[1];
    				}
    			else 
    				{
    				data[i][1]=tmp.getName();
    				data[i][2]="FILE";
    				}
    			data[i][3]=convertSize(tmp.length());
    			}
    		
    		if (tmp.lastModified()==0)data[i][4]="";
    		else data[i][4]=sdf.format(tmp.lastModified());
    		}    		
    	}
    	
    	if (isNotRoot)
    	{
    		Icon upIcon = FileSystemView.getFileSystemView().getSystemIcon(new File("./asdf"));
    		data[0][0]=upIcon;
    		data[0][1]="[...]";
    		data[0][2]="";
    		data[0][3]=0;
    		data[0][4]=0;
    	}
    	}
    	}
    	else needUpdate=false;
    	
    return needUpdate;
    }
    
    private File[] removeHidden(File[] fileList)
    {
    	File[] withoutHidden;
    	int i=fileList.length;
    	for (File test : fileList)
    	{    		
    		if (test.isHidden())i--;  
    	}
    	
    	if (i==fileList.length) return fileList;
    	else
    	{
    		File[] newFileList = new File[i];
    		i=0;
    		for (int j=0;j<fileList.length;j++)
    		{
    			if (!fileList[j].isHidden())
    			{
    				newFileList[i]=fileList[j];
    				i++;
    			}
    		}
    	return newFileList;
    	}
    }
    
    public String getFilePath (int row)
    {
    	return filePaths[row];
    }
    
	@Override
	public int getColumnCount() 
	{
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
	
	public Class getColumnClass(int column)
    {
        return getValueAt(0, column).getClass();
    }
	
	private String convertSize(Long size)
	{
		String result="";
		if(size/1024>0)
		{
			Long x=size/1024;
			
			if(x/1024>0)
			{
				x=x/1024;
				
				if(x/1024>0)
				{
					x=x/1024;
					
				}
				else result=x.toString()+"MB";
				
			}			
			else result = x.toString()+"kB";
		}
		else result=size.toString() + "B";
			
		return result;
	}
	
	
}
