package contentItemsLib;

import java.util.Hashtable;

import android.content.Context;


import CalendarContentHelper.DateInterval;
import CalendarContentHelper.IContentItemsFactory;
import CalendarContentHelper.IContentItem.IContentItem;

public class EventsDictionary extends ContentItemsDictionary {
	public EventsDictionary(Context context, String calendarId) {
		Factory = new EventsFactory(context, calendarId);
	}
	public void Fill(DateInterval interval)
	{
		Fill(interval.ConvertToStringArgs());
	}
	private Hashtable<String,Event> EventsDic = new Hashtable<String,Event>();
	@Override
	public void AddIContentItem(IContentItem contentItem) {
		EventsDic.put(contentItem.getID(), (Event)contentItem);
	}

	@Override
	public IContentItemsFactory GetFactory() {
		return Factory;
	}
	
	public int Size() { return EventsDic.size(); }
	public void Clear() { EventsDic.clear(); }
	public Event GetFirst() { return EventsDic.values().iterator().next(); }
}
