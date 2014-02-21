package CalendarContentHelper.IContentItem;

public interface IEvent extends IContentItem
{
	public String getTitle();
	public String getCalendarId();
	public String getTimeZone();
	public Long getDTENDinMillis();
	public Long getDTSTARTinMillis();
}
