package tpalcmd;

public class MySize {
	
	long size;
	String text;
	
	public MySize(long size)
	{
		this.size=size;
		if (size==-1)text="";
		else if (size==0)text="";
		else
		{
		text=convertSize(size);
		}
	}

	
	@Override
	public String toString()
	{
		return text;
	}
	
	public long getSize()
	{
		return size;
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
					
					if(x/1024>0)
					{
						x=x/1024;
						
					}
					else result=x.toString()+"GB";
					
				}
				else result=x.toString()+"MB";
				
			}			
			else result = x.toString()+"kB";
		}
		else result=size.toString() + "B";
			
		return result;
	}
}
