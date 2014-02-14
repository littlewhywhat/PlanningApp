package contentItemsLib;

import java.util.Date;

import CalendarContentHelper.ContentHelper;
import CalendarContentHelper.EventsHelper;
import CalendarContentHelper.IContentItem.IEvent;

public class Event extends ContentItem implements IEvent {
	public Event(ContentItemsFactory factory) {
		super(factory);
	}

	private Date DTSTART = null;
	private Date DTEND = null;
	private String Title = null;

	@Override
	public Date getDTSTART() {
		// TODO Auto-generated method stub
		return DTSTART;
	}

	@Override
	public void setDTSTART(Date date) {
		// TODO Auto-generated method stub
		DTSTART = date;
	}

	@Override
	public void setDTEND(Date date) {
		// TODO Auto-generated method stub
		DTEND = date;
	}

	@Override
	public Date getDTEND() {
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
