package contentItemsLib;



import android.text.format.Time;

import CalendarContentHelper.ContentHelper;
import CalendarContentHelper.EventsHelper;
import CalendarContentHelper.IContentItem.IEvent;

public class Event extends ContentItem implements IEvent {
	public Event(ContentItemsFactory factory) {
		super(factory);
	}

	private Time DTSTART = new Time();
	private Time DTEND = new Time();
	private String Title = null;

	@Override
	public Time getDTSTART() {
		return DTSTART;
	}

	@Override
	public void setDTSTART(long millis) {
		DTSTART.set(millis);
	}

	@Override
	public void setDTEND(long millis) {
		DTEND.set(millis);
	}

	@Override
	public Time getDTEND() {
		return DTEND;
	}

	@Override
	public void setTitle(String title) {
		Title = title;
	}

	@Override
	public String getTitle() {
		return Title;
	}

	@Override
	protected ContentHelper getHelper() {
		return new EventsHelper(getFactory().getContext(), ((EventsFactory)getFactory()).getCalendarId());
	}

}
