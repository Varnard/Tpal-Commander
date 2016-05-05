package tpalcmd;

import java.util.Comparator;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

public class FileTable extends JTable{
	
	public FileTable(Locale locale){
		MyTableModel tableModel = new MyTableModel(locale);
		this.setModel(tableModel);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.setShowGrid(false);
		this.getColumnModel().getColumn(0).setPreferredWidth(0);
		this.getColumnModel().getColumn(1).setPreferredWidth(150);
		this.getColumnModel().getColumn(2).setPreferredWidth(50);
		this.getColumnModel().getColumn(3).setPreferredWidth(70);
		this.getColumnModel().getColumn(4).setPreferredWidth(80);	
		
    	TableRowSorter<MyTableModel >sorter = new TableRowSorter<MyTableModel>();
    	sorter.setModel(tableModel);
    	
    	this.setRowSorter(sorter);
    	
    	sorter.setSortable(0, false);
    	sorter.setComparator(1, new Comparator<String>() {
        	
    		@Override
    		public int compare(String s1, String s2) {
    			
    			int result;
    			
    			if (s1.equals("[..]")||s2.equals("[..]"))
    			{
    				result=0;
    				return result;
    			}
    			else
    			{
    				result = s1.compareTo(s2);
    			}		
    			
    			
    			return result;
    		}  
    		
    		
    	});	
    	
    	sorter.setComparator(2, new Comparator<String>() {
        	
    		@Override
    		public int compare(String s1, String s2) {
    			
    			int result;
    			
    			if (s1.equals("")||s2.equals(""))
    			{
    				result=0;
    				return result;
    			}
    			else
    			{
    				result = s1.compareTo(s2);
    			}		
    			
    			
    			return result;
    		}  
    		
    		
    	});	
    	
    	sorter.setComparator(3, new Comparator<MySize>() {
        	
    		@Override
    		public int compare(MySize s1, MySize s2) {
    			
    			int result=0;
    			
    			if (s1.getSize()==-1||s2.getSize()==-1)
    			{
    				result=0;
    				return result;
    			}
    			else
    			{
    				if (s1.getSize()-s2.getSize()>0) result=1;
    				else if (s1.getSize()-s2.getSize()<0)result = -1;
    				else if (s1.getSize()-s2.getSize()==0)result=0;
    				return result;
    			}
  	  			

    		} 
    		
    		
    	});	
    	
    	sorter.setComparator(4, new Comparator<MyDate>() {
        	
    		@Override
    		public int compare(MyDate d1, MyDate d2) {
    			
    			int result=0;
    			
    			if (d1.getTime()==-1||d2.getTime()==-1)
    			{
    				result=0;
    				return result;
    			}
    			else
    			{
    				if (d1.getTime()-d2.getTime()>0) result=1;
    				else if (d1.getTime()-d2.getTime()<0)result = -1;
    				else if (d1.getTime()-d2.getTime()==0)result=0;
    				return result;
    			}
  	  			

    		}  
    		
    		
    	});	
	}

}
