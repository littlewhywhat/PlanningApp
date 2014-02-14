package contentItemsLib;


import android.content.Context;


import CalendarContentHelper.DateInterval;
import CalendarContentHelper.IContentItemsFactory;

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
