package com.littlewhywhat.planning.android.data.event;

import android.net.Uri;
import android.content.ContentUris;
import android.content.ContentValues;
import android.provider.CalendarContract;

class Events {
	public static final String[] EVENT_PROJECTION = new String[] {
		CalendarContract.Events._ID,                    // 0
		CalendarContract.Events.TITLE,                  // 1
		CalendarContract.Events.DTSTART,         	   // 2
		CalendarContract.Events.DTEND,                  // 3
		CalendarContract.Events.CALENDAR_ID,		       // 4
	};
	public static final int PROJECTION_ID_INDEX = 0;
	public static final int PROJECTION_TITLE_INDEX = 1;
	public static final int PROJECTION_DTSTART_INDEX = 2;
	public static final int PROJECTION_DTEND_INDEX = 3;
	public static final int PROJECTION_CALENDARID_INDEX = 4;
	public static final String selection = "((" + CalendarContract.Events.DTSTART + " >= ?) AND (" 
            + CalendarContract.Events.DTEND + " <= ?) AND (" 
            + CalendarContract.Events.DELETED + " != '1') AND (" 
            + CalendarContract.Events.CALENDAR_ID + " <= ?)) ";
	public static final String selectionById = "((" + CalendarContract.Events._ID + " = ?))";
	public static final Uri CONTENT_URI = CalendarContract.Events.CONTENT_URI;

	public static Uri getUriWithId(String id) {
		return ContentUris.withAppendedId(CONTENT_URI, Long.parseLong(id));
	}

	public static ContentValues getContentValuesOf(Event event) {
		final ContentValues values = new ContentValues();		
		values.put(CalendarContract.Events.CALENDAR_ID, event.getCalendarId());
		values.put(CalendarContract.Events.EVENT_TIMEZONE, event.getTimeZone());
		values.put(CalendarContract.Events.DTSTART, event.getDtStartinMillis());
		values.put(CalendarContract.Events.DTEND, event.getDtEndinMillis());
		values.put(CalendarContract.Events.TITLE, event.getTitle());
		return values;
	}
}