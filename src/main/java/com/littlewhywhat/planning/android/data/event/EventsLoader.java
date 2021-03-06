package com.littlewhywhat.planning.android.data.event;

import android.content.CursorLoader;
import android.content.Context;

public class EventsLoader extends CursorLoader {
	public EventsLoader(Context context, String dtStart, String dtEnd, String calendarId) {
		super(context, Events.CONTENT_URI , Events.PROJECTION, Events.SELECTION, 
				new String[] { dtStart, dtEnd, calendarId}, null);
	}
}