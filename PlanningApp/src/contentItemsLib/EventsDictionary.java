package contentItemsLib;


import CalendarContentHelper.DateInterval;
import CalendarContentHelper.IContentItemsFactory;
import android.content.Context;



public class EventsDictionary extends ContentItemsDictionary {
	public EventsDictionary(Context context, String calendarId) {
		Factory = new EventsFactory(context, calendarId);
	}
	public void Fill(DateInterval interval)
	{
		Fill(interval.ConvertToStringArgs());
	}

	@Override
	public IContentItemsFactory GetFactory() {
		return Factory;
	}

}
