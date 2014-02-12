package CalendarContentHelper;

import java.util.Date;

public interface IEvent 
{
	public int getID();
	public void setID(int id);
	public Date getDTSTART();
	public void setDTSTART(Date date);
	public void setDURATION(String duration);
	public String getDURATION();
	public void setTitle(String title);
	public String getTitle();
}
