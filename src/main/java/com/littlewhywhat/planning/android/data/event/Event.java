package com.littlewhywhat.planning.android.data.event;

public class Event {
	private String mId;
	private String mTitle;
	private String mCalendarId;
	private String mTimeZone;
	private long mDtStart;
	private long mDtEnd;

	public static Event newInstance() {
		return new Event();
	}

	private Event() { }

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		mId = id;
	}

	public long getDtStart() {
		return mDtStart;
	}

	public void setDtStart(long millis) {
		mDtStart = millis;
	}

	public long getDtEnd() {
		return mDtEnd;
	}

	public void setDtEnd(long millis) {
		mDtEnd = millis;
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
		return mTimeZone;
	}

	public void setTimeZone(String timeZone) {
		mTimeZone = timeZone;
	}
}