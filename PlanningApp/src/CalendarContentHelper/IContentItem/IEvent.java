package CalendarContentHelper.IContentItem;

import java.util.Date;


public interface IEvent extends IContentItem
{
	public Date getDTSTART();
	public void setDTSTART(Date date);
	public void setDTEND(Date date);
	public Date getDTEND();
	public void setTitle(String title);
	public String getTitle();
}
