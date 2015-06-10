package com.littlewhywhat.planning.android.ui.calendar.view;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.calendar.Calendars;

import android.content.Context;
import android.widget.SimpleCursorAdapter;

class CalendarsCursorAdapter extends SimpleCursorAdapter {
	private final static String[] FROM = new String[] { Calendars.PROJECTION[Calendars.NAME_INDEX] };
	private final static int[] TO = new int[] { R.id.calendarTitle };

	public CalendarsCursorAdapter(Context context) {
		super(context, R.layout.calendar, null, FROM, TO, 0);		
	}
}