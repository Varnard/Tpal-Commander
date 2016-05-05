package tpalcmd;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MyDate{	
	
	private long time;
	private SimpleDateFormat sdf;
	private String text;
	
	public MyDate (long time, Locale locale)
	{
		if (locale.equals(new Locale("EN")))sdf = new SimpleDateFormat("dd/MM/yyyy");
		else sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.time=time;
		
		if (time==-1)text="";
		else if (time==0)text="Never";
		else
		{
			text=sdf.format(time);
		}
		
	}
	
	@Override
	public String toString()
	{
		return text;
	}
	
	public long getTime()
	{
		return time;
	}

}
