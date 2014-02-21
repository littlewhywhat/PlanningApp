package contentItemsLib;



import java.util.TimeZone;

import CalendarContentHelper.ContentHelper;
import CalendarContentHelper.EventsHelper;
import CalendarContentHelper.IContentItem.IEvent;
import android.content.Context;
import android.text.format.Time;


public class Event extends ContentItem implements IEvent {
	public Event(Context context) {
		super(context);
		
	}

	private Time DTSTART = new Time();
	private Time DTEND = new Time();
	private String Title;
	private String mCalendarId;
	
	
	public Time getDTSTART() {
		return DTSTART;
	}
	public Time getDTEND() {
		return DTEND;
	}
	public void setDTSTART(long millis) {
		DTSTART.set(millis);
	}

	public void setDTEND(long millis) {
		DTEND.set(millis);
	}


	public void setTitle(String title) {
		Title = title;
	}
	
	public void setCalendarId(String calendarId) {
		mCalendarId = calendarId;
	}

   	@Override
   	public String getTitle() {
   		return Title;
   	}
   	
	@Override
	public String getCalendarId() {
		// TODO Auto-generated method stub
		return mCalendarId;
	}

	@Override
	public String getTimeZone() {
		return TimeZone.getDefault().getDisplayName();
	}

	@Override
	public Long getDTENDinMillis() {
		return DTEND.toMillis(true);
	}

	@Override
	public Long getDTSTARTinMillis() {
		return DTSTART.toMillis(true);
	}

	@Override
	protected ContentHelper getHelper() {
		return new EventsHelper(getContext()) ;
	}

}
