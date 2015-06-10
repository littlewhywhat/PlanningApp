package com.littlewhywhat.planning.android.data.event;

import android.net.Uri;
import android.content.ContentUris;
import android.content.ContentValues;
import android.provider.CalendarContract;

public class Events {
	public static final String[] PROJECTION = new String[] {
		CalendarContract.Events._ID,                    // 0
		CalendarContract.Events.TITLE,                  // 1
		CalendarContract.Events.DTSTART,         	    // 2
		CalendarContract.Events.DTEND,                  // 3
		CalendarContract.Events.CALENDAR_ID,		    // 4
		CalendarContract.Events.EVENT_TIMEZONE          // 5
	};
	public static final int ID_INDEX = 0;
	public static final int TITLE_INDEX = 1;
	public static final int DTSTART_INDEX = 2;
	public static final int DTEND_INDEX = 3;
	public static final int CALENDARID_INDEX = 4;
	public static final int TIMEZONE_INDEX = 5;
	public static final String SELECTION = "((" + CalendarContract.Events.DTSTART + " >= ?) AND (" 
            + CalendarContract.Events.DTEND + " <= ?) AND (" 
            + CalendarContract.Events.DELETED + " != '1') AND (" 
            + CalendarContract.Events.CALENDAR_ID + " <= ?)) ";
	public static final String SELECTION_BY_ID = "((" + CalendarContract.Events._ID + " = ?))";
	public static final Uri CONTENT_URI = CalendarContract.Events.CONTENT_URI;

	public static Uri getUriWithId(String id) {
		return ContentUris.withAppendedId(CONTENT_URI, Long.parseLong(id));
	}

	public static ContentValues getContentValuesOf(Event event) {
		final ContentValues values = new ContentValues();		
		values.put(CalendarContract.Events.CALENDAR_ID, event.getCalendarId());
		values.put(CalendarContract.Events.EVENT_TIMEZONE, event.getTimeZone());
		values.put(CalendarContract.Events.DTSTART, event.getDtStart());
		values.put(CalendarContract.Events.DTEND, event.getDtEnd());
		values.put(CalendarContract.Events.TITLE, event.getTitle());
		return values;
	}
}