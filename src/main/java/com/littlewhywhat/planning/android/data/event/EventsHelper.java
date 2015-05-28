package com.littlewhywhat.planning.android.data.event;

import android.content.Context;
import android.content.ContentResolver;
import android.util.Log;

class EventsHelper {
	private static final String TAG = "EventsHelper";

	private Context appContext;

	public EventsHelper(Context context) {
		appContext = context;
		Log.i(TAG, "Created");
	}

	private ContentResolver getResolver() {
		return appContext.getContentResolver();
	}

	public void Update(Event event) {
		getResolver().update(Events.getUriWithId(event.getID()), Events.getContentValuesOf(event), null, null);
	}

	public void Delete(Event event) {
		if (0 != getResolver().delete(Events.getUriWithId(event.getID()), null, null))
			event.setID(null);		
	}

	public void Insert(Event event) {
		event.setID(getResolver().insert(Events.CONTENT_URI, Events.getContentValuesOf(event)).getLastPathSegment());			
	}
}
