package com.littlewhywhat.planning.android.ui.calendar.view;

import com.littlewhywhat.planning.android.R;

import android.content.Context;
import android.provider.CalendarContract.Calendars;
import android.widget.SimpleCursorAdapter;

class CalendarsCursorAdapter extends SimpleCursorAdapter {
	private final static String[] from = new String[] { Calendars.CALENDAR_DISPLAY_NAME };
	private final static int[] to = new int[] { R.id.calendarTitle };
	public CalendarsCursorAdapter(Context context) {
		super(context, R.layout.calendar, null, from, to, 0);		
	}

}
