package com.littlewhywhat.planning.android.data.event;

import java.util.GregorianCalendar;
import java.util.Calendar;

import android.content.ClipData;
import android.content.ClipDescription;
import android.database.Cursor;

public class Event {
	private static final String DEFAULT_TITLE = "New Event";
	private static final int MINUTES_IN_HOUR = 60;
	private static final int DEFAULT_INTERVAL = 5;
	private String mId;
	private String mTitle;
	private String mCalendarId;
	private Calendar mDtStart;
	private Calendar mDtEnd;
	
	private Event() {
		mDtStart = new GregorianCalendar();
		mDtEnd = (Calendar)mDtStart.clone();
		setDtEndDefault();
	}
	
	public Event(Cursor cursor) {
		this();
		setId(cursor.getString(Events.PROJECTION_ID_INDEX));
		setDtStart(cursor.getLong(Events.PROJECTION_DTSTART_INDEX));
		setDtEnd(cursor.getLong(Events.PROJECTION_DTEND_INDEX));
		setTitle(cursor.getString(Events.PROJECTION_TITLE_INDEX));
		setCalendarId(cursor.getString(Events.PROJECTION_CALENDARID_INDEX));
	}
	
	public Event(ClipData data) {
		this();
		setId((String)data.getItemAt(Events.PROJECTION_ID_INDEX).getText());
		setTitle((String)data.getItemAt(Events.PROJECTION_TITLE_INDEX).getText());
		setCalendarId((String)data.getItemAt(Events.PROJECTION_CALENDARID_INDEX).getText());
		setDtStart((String)data.getItemAt(Events.PROJECTION_DTSTART_INDEX).getText());
		setDtEnd((String)data.getItemAt(Events.PROJECTION_DTEND_INDEX).getText());
	}
	
	public Event(String calendarId, long time) {
		this();
		setCalendarId(calendarId);
		setDtStart(time);
		setDtEndDefault();
		setTitle(DEFAULT_TITLE);
	}

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		mId = id;
	}
	
	public String getDtStartInString() {
		return mDtStart.getTime().toString();
	}

	public String getDtEndInString() {
		return mDtEnd.getTime().toString();
	}

	public Long getDtEndinMillis() {
		return mDtEnd.getTimeInMillis();
	}

	public Long getDtStartinMillis() {
		return mDtStart.getTimeInMillis();
	}

	public void setDtStart(long millis) {
		mDtStart.setTimeInMillis(millis);
	}

	public void setDtStart(String millis) {
		mDtStart.setTimeInMillis(Long.parseLong(millis));
	}

	public void setDtEnd(long millis) {
		mDtEnd.setTimeInMillis(millis);
	}

	public void setDtEnd(String millis) {
		mDtEnd.setTimeInMillis(Long.parseLong(millis));
	}

	public void setDtEndDefault() {
		setDtEnd(getDtStartinMillis());
		mDtEnd.roll(Calendar.MINUTE, DEFAULT_INTERVAL);
	}

	public void setDtEndinMinutes(int progressInMin) {
		mDtEnd.set(Calendar.HOUR_OF_DAY, progressInMin / MINUTES_IN_HOUR);
		mDtEnd.set(Calendar.MINUTE, progressInMin % MINUTES_IN_HOUR);
	}
	
	public void setDtStartinMinutes(int progressInMin) {
		mDtStart.set(Calendar.HOUR_OF_DAY, progressInMin / MINUTES_IN_HOUR);
		mDtStart.set(Calendar.MINUTE, progressInMin % MINUTES_IN_HOUR);
	}

	public int getDtStartinMinutes() {
		final int hours = mDtStart.get(Calendar.HOUR_OF_DAY);
		final int minutes = mDtStart.get(Calendar.MINUTE);
		return hours * MINUTES_IN_HOUR + minutes;
	}
	
	public int getDtEndinMinutes() {
		final int hours = mDtEnd.get(Calendar.HOUR_OF_DAY);
		final int minutes = mDtEnd.get(Calendar.MINUTE);
		return hours * MINUTES_IN_HOUR + minutes;
	}

	public void setTitle(String title) {
		mTitle = title;
	}
   	
   	public String getTitle() {
   		return mTitle;
   	}

	public void setCalendarId(String calendarId) {
		mCalendarId = calendarId;
	}
   	
	public String getCalendarId() {
		return mCalendarId;
	}

	public String getTimeZone() {
		return mDtStart.getTimeZone().getDisplayName();
	}

	public ClipData getClipData() {
		final ClipData.Item idItem = new ClipData.Item(getId());
		final ClipData data = new ClipData(Event.class.getName(), 
										   new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
										   idItem);
		final ClipData.Item titleItem = new ClipData.Item(getTitle());
		final ClipData.Item dtStartItem = new ClipData.Item(String.valueOf(getDtStartinMillis()));
		final ClipData.Item dtEndItem = new ClipData.Item(String.valueOf(getDtEndinMillis()));
		final ClipData.Item calendarIdItem = new ClipData.Item(getCalendarId());
		data.addItem(titleItem);
		data.addItem(dtStartItem);
		data.addItem(dtEndItem);
		data.addItem(calendarIdItem);
		return data;
	}
}
