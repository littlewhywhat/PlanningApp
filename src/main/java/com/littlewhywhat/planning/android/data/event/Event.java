package com.littlewhywhat.planning.android.data.event;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class Event {
	private String mId;
	private String mTitle;
	private String mCalendarId;
	private Calendar mDtStart;
	private Calendar mDtEnd;

	public static Event newInstance() {
		final Event event = new Event();
		return event;
	}

	private Event() {
		mDtStart = new GregorianCalendar();
		mDtEnd = (Calendar)mDtStart.clone();
	}

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		mId = id;
	}

	public Calendar getDtStart() {
		return mDtStart;
	}

	public Calendar getDtEnd() {
		return mDtEnd;
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
}