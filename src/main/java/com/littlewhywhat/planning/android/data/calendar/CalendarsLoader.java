package com.littlewhywhat.planning.android.data.calendar;

import android.content.Context;
import android.content.CursorLoader;

public class CalendarsLoader extends CursorLoader {
	public CalendarsLoader(Context context) {
		super(context, Calendars.CONTENT_URI, Calendars.PROJECTION, Calendars.SELECTION, Calendars.SELECTION_ARGS, null);
	}
}