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
		// TODO Auto-generated method stub
		return DTSTART;
	}

	@Override
	public void setDTSTART(long millis) {
		// TODO Auto-generated method stub
		DTSTART.set(millis);
	}

	@Override
	public void setDTEND(long millis) {
		// TODO Auto-generated method stub
		DTEND.set(millis);
	}

	@Override
	public Time getDTEND() {
		// TODO Auto-generated method stub
		return DTEND;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		Title = title;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return Title;
	}

	@Override
	protected ContentHelper getHelper() {
		// TODO Auto-generated method stub
		return new EventsHelper(getFactory().getContext(), ((EventsFactory)getFactory()).getCalendarId());
	}

}
