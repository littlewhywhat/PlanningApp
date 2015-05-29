package com.littlewhywhat.planning.android.data.event;

import android.content.CursorLoader;
import android.content.Context;

public class EventLoader extends CursorLoader {
	public EventLoader(Context context, String eventId) {
		super(context, Events.CONTENT_URI , Events.EVENT_PROJECTION, Events.selectionById, 
				new String[] { eventId }, null);
	}
}