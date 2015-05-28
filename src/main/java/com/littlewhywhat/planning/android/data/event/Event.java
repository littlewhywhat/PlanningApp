package com.littlewhywhat.planning.android.data.event;

import java.util.GregorianCalendar;
import java.util.Calendar;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.database.Cursor;

public class Event {
	private static final long MILLISINMINUTE = 60000;
	private Context appContext;
	private String ID;
	protected Context getContext() {
		return appContext;
	}
	private Event(Context context) {
		appContext = context;
		calendarStart = new GregorianCalendar();
		calendarEnd = (GregorianCalendar)calendarStart.clone();
		calendarEnd.roll(Calendar.MINUTE, 5);
	}
	public Event(Context context, Cursor cursor) {
		this(context);
		setID(cursor.getString(Events.PROJECTION_ID_INDEX));
		setDTSTART(cursor.getLong(Events.PROJECTION_DTSTART_INDEX));
		setDTEND(cursor.getLong(Events.PROJECTION_DTEND_INDEX));
		setTitle(cursor.getString(Events.PROJECTION_TITLE_INDEX));
		setCalendarId(cursor.getString(Events.PROJECTION_CALENDARID_INDEX));
	}
	
	public Event(Context context, ClipData data) {
		this(context);
		setID((String) data.getItemAt(Events.PROJECTION_ID_INDEX).getText());
		setTitle((String)data.getItemAt(Events.PROJECTION_TITLE_INDEX).getText());
		setCalendarId((String)data.getItemAt(Events.PROJECTION_CALENDARID_INDEX).getText());
		setDTSTART((String)data.getItemAt(Events.PROJECTION_DTSTART_INDEX).getText());
		setDTEND((String)data.getItemAt(Events.PROJECTION_DTEND_INDEX).getText());
	}
	
	public Event(Context context, String calendarId, long time) {
		this(context);
		setCalendarId(calendarId);
		setDTSTART(time);
		setDTEND(getDTSTARTinMillis() + 5 * MILLISINMINUTE);
		setTitle("New Event");
	}

	private String Title;
	private String mCalendarId;
	private GregorianCalendar calendarStart;
	private GregorianCalendar calendarEnd;

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public int getDTSTARTinMinutes() {
		int hours = calendarStart.get(Calendar.HOUR_OF_DAY);
		int minutes = calendarStart.get(Calendar.MINUTE);
		return hours * 60 + minutes;
	}
	
	public int getDTENDinMinutes() {
		int hours = calendarEnd.get(Calendar.HOUR_OF_DAY);
		int minutes = calendarEnd.get(Calendar.MINUTE);
		return hours * 60 + minutes;
	}
	
	public String getDTSTARTinString() {
		return calendarStart.getTime().toString();
	}
	public String getDTENDinString() {
		return calendarEnd.getTime().toString();
	}
	public ClipData getClipData() {
		ClipData.Item idItem = new ClipData.Item(getID());
		ClipData data = new ClipData(Event.class.getName(), 
				new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN},
				idItem);
		ClipData.Item titleItem = new ClipData.Item(getTitle());
		ClipData.Item dtStartItem = new ClipData.Item(String.valueOf(getDTSTARTinMillis()));
		ClipData.Item dtEndItem = new ClipData.Item(String.valueOf(getDTENDinMillis()));
		ClipData.Item calendarIdItem = new ClipData.Item(getCalendarId());
		data.addItem(titleItem);
		data.addItem(dtStartItem);
		data.addItem(dtEndItem);
		data.addItem(calendarIdItem);
		return data;
	}

	public void setDTSTART(long millis) {
		calendarStart.setTimeInMillis(millis);
	}

	public void setDTEND(long millis) {
		calendarEnd.setTimeInMillis(millis);
	}
	public void setDTSTART(String millis) {
		calendarStart.setTimeInMillis(Long.parseLong(millis, 10));
	}

	public void setDTEND(String millis) {
		calendarEnd.setTimeInMillis(Long.parseLong(millis, 10));
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
		return calendarStart.getTimeZone().getDisplayName();
	}

	public Long getDTENDinMillis() {
		return calendarEnd.getTimeInMillis();
	}

	public Long getDTSTARTinMillis() {
		return calendarStart.getTimeInMillis();
	}

	protected EventsHelper getHelper() {
		return new EventsHelper(getContext()) ;
	}
	public void setDTENDinMinutes(int progressInMin) {
		calendarEnd.set(Calendar.HOUR_OF_DAY, progressInMin / 60);
		calendarEnd.set(Calendar.MINUTE, progressInMin % 60);
	}
	
	public void setDTSTARTinMinutes(int progressInMin) {
		calendarStart.set(Calendar.HOUR_OF_DAY, progressInMin / 60);
		calendarStart.set(Calendar.MINUTE, progressInMin % 60);
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
