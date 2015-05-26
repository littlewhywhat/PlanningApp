package com.littlewhywhat.planning.android.data.calendar;

import android.content.Context;
import android.content.CursorLoader;
import android.provider.CalendarContract.Calendars;

public class CalendarsLoader extends CursorLoader {

	public CalendarsLoader(Context context) {
		super(context, Calendars.CONTENT_URI, CalendarsHelper.CALENDAR_PROJECTION, CalendarsHelper.selection, CalendarsHelper.SelectionArgs, null);
	}

}
