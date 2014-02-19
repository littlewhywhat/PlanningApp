package contentItemsLib;

import CalendarContentHelper.EventsHelper;
import android.content.Context;

public class EventsFactory extends ContentItemsFactory {
	
	String CalendarId;
	public EventsFactory(Context context, String calendarId)
	{
		super(context);
		helper = new EventsHelper(context, calendarId);
		CalendarId = calendarId;
	}
	public String getCalendarId() { return CalendarId; }
	@Override
	public ContentItem getNewContentItem() {
		return new Event(this);
	}

}
