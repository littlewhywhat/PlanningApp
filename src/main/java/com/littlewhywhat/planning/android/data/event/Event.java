package com.littlewhywhat.planning.android.data.event;

import com.littlewhywhat.planning.android.util.DateInterval;

import java.util.TimeZone;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.database.Cursor;
import android.text.format.Time;

public class Event {
	private static final String TIME_FORMAT = "%Y.%m.%d %H:%M:%S";
	private static final long MILLISINMINUTE = 60000;
	private Context appContext;
	private String ID;
	protected Context getContext() {
		return appContext;
	}
	public Event(Context context) {
		appContext = context;
		
	}
	public Event(Context context, Cursor cursor) {
		this(context);
		setID(cursor.getString(EventsHelper.PROJECTION_ID_INDEX));
		setDTSTART(cursor.getLong(EventsHelper.PROJECTION_DTSTART_INDEX));
		setDTEND(cursor.getLong(EventsHelper.PROJECTION_DTEND_INDEX));
		setTitle(cursor.getString(EventsHelper.PROJECTION_TITLE_INDEX));
		setCalendarId(cursor.getString(EventsHelper.PROJECTION_CALENDARID_INDEX));
	}
	
	public Event(Context context, ClipData data)
	{
		this(context);
		setID((String) data.getItemAt(EventsHelper.PROJECTION_ID_INDEX).getText());
		setTitle((String)data.getItemAt(EventsHelper.PROJECTION_TITLE_INDEX).getText());
		setCalendarId((String)data.getItemAt(EventsHelper.PROJECTION_CALENDARID_INDEX).getText());
		DTSTART.parse((String)data.getItemAt(EventsHelper.PROJECTION_DTSTART_INDEX).getText());
		DTEND.parse((String)data.getItemAt(EventsHelper.PROJECTION_DTEND_INDEX).getText());
	}
	
	public Event(Context context, String calendarId, Time time) {
		this(context);
		setCalendarId(calendarId);
		setDTSTART(DateInterval.TrimHMS(time).toMillis(true));
		setDTEND(getDTSTARTinMillis() + 5 * MILLISINMINUTE);
		setTitle("New Event");
	}

	private Time DTSTART = new Time();
	private Time DTEND = new Time();
	private String Title;
	private String mCalendarId;
	
	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public int getDTSTARTinMinutes() {
		
		return (int) ((getDTSTARTinMillis() - DateInterval.TrimHMS(getDTSTART()).toMillis(true)) / MILLISINMINUTE);
	}
	
	public int getDTENDinMinutes() {
		return (int) ((getDTENDinMillis() - DateInterval.TrimHMS(getDTEND()).toMillis(true)) / MILLISINMINUTE);
	}
	
	public String getDTSTARTinString() {
		return DTSTART.format(TIME_FORMAT);
	}
	public String getDTENDinString() {
		return DTEND.format(TIME_FORMAT);
	}
	public ClipData getClipData() {
		ClipData.Item idItem = new ClipData.Item(getID());
		ClipData data = new ClipData(Event.class.getName(), 
				new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN},
				idItem);
		ClipData.Item titleItem = new ClipData.Item(getTitle());
		ClipData.Item dtStartItem = new ClipData.Item(getDTSTART().format2445());
		ClipData.Item dtEndItem = new ClipData.Item(getDTEND().format2445());
		ClipData.Item calendarIdItem = new ClipData.Item(getCalendarId());
		data.addItem(titleItem);
		data.addItem(dtStartItem);
		data.addItem(dtEndItem);
		data.addItem(calendarIdItem);
		return data;
	}
	
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

   	public String getTitle() {
   		return Title;
   	}
   	
	public String getCalendarId() {
		return mCalendarId;
	}

	public String getTimeZone() {
		return TimeZone.getDefault().getDisplayName();
	}

	public Long getDTENDinMillis() {
		return DTEND.toMillis(true);
	}

	public Long getDTSTARTinMillis() {
		return DTSTART.toMillis(true);
	}

	protected EventsHelper getHelper() {
		return new EventsHelper(getContext()) ;
	}
	public void setDTENDinMinutes(int progress) {
		setDTEND(DateInterval.TrimHMS(getDTEND()).toMillis(true) + (long)(progress * MILLISINMINUTE));		
	}
	
	public void setDTSTARTinMinutes(int progress) {
		setDTSTART(DateInterval.TrimHMS(getDTSTART()).toMillis(true) + (long)(progress * MILLISINMINUTE));		
	}

	public void Update() {
		getHelper().Update(this);
	}
	
	public void Delete() {
		getHelper().Delete(this);
	}

	public void Insert() {
		getHelper().Insert(this);
	}
}
