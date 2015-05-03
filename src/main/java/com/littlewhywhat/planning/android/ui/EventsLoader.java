package com.littlewhywhat.planning.android.ui;

import com.littlewhywhat.planning.android.data.EventsHelper;
import android.content.*;
import android.provider.CalendarContract.Events;

public class EventsLoader extends CursorLoader {


	public EventsLoader(Context context, String DTSTART, String DTEND, String calendarId) {
		super(context, Events.CONTENT_URI , EventsHelper.EVENT_PROJECTION, EventsHelper.selection, 
				new String[] { DTSTART, DTEND, calendarId}, null);
	}

}
