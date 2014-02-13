package CalendarContentHelper;

import java.util.Date;

public interface IEvent 
{
	public String getID();
	public void setID(String id);
	public Date getDTSTART();
	public void setDTSTART(Date date);
	public void setDTEND(Date date);
	public Date getDTEND();
	public void setTitle(String title);
	public String getTitle();
}
