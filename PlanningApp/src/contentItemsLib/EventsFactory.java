package contentItemsLib;

import CalendarContentHelper.ContentHelper;
import CalendarContentHelper.EventsHelper;
import android.content.Context;

public class EventsFactory extends ContentItemsFactory {
	
	String CalendarId;
	public EventsFactory(Context context, String calendarId)
	{
		super(context);
		CalendarId = calendarId;
	}
	public String getCalendarId() { return CalendarId; }
	@Override
	public ContentItem getNewContentItem() {
		return new Event(this);
	}
	@Override
	protected ContentHelper getHelper(Context context) {
		return new EventsHelper(context, CalendarId);
	}

}
