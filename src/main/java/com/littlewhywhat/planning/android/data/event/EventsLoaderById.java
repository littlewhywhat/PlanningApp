package com.littlewhywhat.planning.android.data.event;

import android.content.CursorLoader;
import android.content.Context;

public class EventsLoaderById extends CursorLoader {
	public EventsLoaderById(Context context, String eventId) {
		super(context, Events.CONTENT_URI , Events.PROJECTION, Events.SELECTION_BY_ID, 
				new String[] { eventId }, null);
	}
}