package CalendarContentHelper.IContentItem;


import android.text.format.Time;


public interface IEvent extends IContentItem
{
	public Time getDTSTART();
	public void setDTSTART(long millis);
	public void setDTEND(long millis);
	public Time getDTEND();
	public void setTitle(String title);
	public String getTitle();
}
