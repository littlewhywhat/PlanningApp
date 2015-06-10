package com.littlewhywhat.planning.android.data.calendar;

import android.provider.CalendarContract;
import android.net.Uri;

public class Calendars {
	public static final String SELECTION = "((" + CalendarContract.Calendars.VISIBLE + " = ?) AND (" 
            + CalendarContract.Calendars.SYNC_EVENTS + " = ?)) ";
	public static final String[] SELECTION_ARGS = new String[] { "1" , "1" };
	public static final String[] PROJECTION = new String[] {
		CalendarContract.Calendars._ID,                      // 0
		CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,    // 1
	};
	public static final Uri CONTENT_URI = CalendarContract.Calendars.CONTENT_URI;
	public static final int ID_INDEX = 0;
	public static final int NAME_INDEX = 1;	
}