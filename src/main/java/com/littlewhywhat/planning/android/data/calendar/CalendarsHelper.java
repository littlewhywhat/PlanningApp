package com.littlewhywhat.planning.android.data.calendar;

import android.provider.CalendarContract;

class CalendarsHelper {
	public static final String selection = "((" + CalendarContract.Calendars.VISIBLE + " = ?) AND (" 
            + CalendarContract.Calendars.SYNC_EVENTS + " = ?)) ";
	public static final String[] SelectionArgs = new String[] { "1" , "1" };
	public static final String[] CALENDAR_PROJECTION = new String[] {
		CalendarContract.Calendars._ID,                      // 0
		CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,    // 1
	};
	public static final int PROJECTION_ID_INDEX = 0;
	public static final int PROJECTION_NAME_INDEX = 1;	
}
