package tpalcmd;

import java.io.File;
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
    		Icon icon = FileSystemView.getFileSystemView().getSystemIcon(tmp);
    		data[i][0]=icon;
    		data[i][1]=fileName[0];
    		if (tmp.isDirectory())
    			{
    			data[i][2]="<DIR>";
    			data[i][3]="";
    			}
    		else 
    			{
    			data[i][2]=fileName[1];
    			data[i][3]=convertSize(tmp.length());
    			}
    		
    		if (tmp.lastModified()==0)data[i][4]="";
    		else data[i][4]=sdf.format(tmp.lastModified());
    		}
    		
    	}
        	
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
