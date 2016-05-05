package tpalcmd;

import java.awt.Desktop;
import java.io.File;
import java.io.FilePermission;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

class MyTableModel extends AbstractTableModel 
{
		    	
	final int columns=5;
	int rows;
	String dirString;
	String fileString;
	String[] columnNames;
	Object data[][];
	String filePaths[];
	Locale locale;
        
    MyTableModel(Locale locale)
    {
    	columnNames = new String[5];
    	columnNames[0] = "";
    	setLanguage(locale);
    	fileSelected(new File("C:/"));
    	this.locale=locale;
    }

     
    public boolean fileSelected(File file)
    {
    	boolean needUpdate=true;
   
    	if (file.canRead())
    	{
    		
    	if(!file.isDirectory())
    		{
    		FileManager.openFile(file);
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
    			data[i][2]=dirString;
    			data[i][3]=new MySize(0);
    			}
    		else 
    			{
    			String fileName= tmp.getName();
    			if (fileName.contains("."))
    				{
        			int j = fileName.lastIndexOf(".");
        			String[] name =  {fileName.substring(0, j), fileName.substring(j+1)};
     				data[i][1]=name[0];
    				data[i][2]=name[1];
    				}
    			else 
    				{
    				data[i][1]=tmp.getName();
    				data[i][2]=fileString;
    				}
    			data[i][3]=new MySize(tmp.length());
    			}   	

    		data[i][4]= new MyDate(tmp.lastModified(),locale);
    		}    		
    	}
    	
    	if (isNotRoot)
    	{
    		Icon upIcon = FileSystemView.getFileSystemView().getSystemIcon(new File("./asdf"));
    		data[0][0]=upIcon;
    		data[0][1]="[..]";
    		data[0][2]="";
    		data[0][3]= new MySize(-1);
    		data[0][4]= new MyDate(-1,locale);
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
	public int getRowCount() 
	{
		return rows;
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		return data[row][column];
	}	
	
	
	public Class getColumnClass(int column)
    {
        return getValueAt(0, column).getClass();
    }
	

	
	public void setLanguage (Locale locale)
	{
		ResourceBundle rb = ResourceBundle.getBundle("Language", locale);
		columnNames[1]=rb.getString("column1");
		columnNames[2]=rb.getString("column2");
		columnNames[3]=rb.getString("column3");
		columnNames[4]=rb.getString("column4");
		dirString=rb.getString("dir");
		fileString=rb.getString("file");
    	fireTableDataChanged();
	}
}
