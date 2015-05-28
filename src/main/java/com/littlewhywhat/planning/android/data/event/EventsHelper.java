package com.littlewhywhat.planning.android.data.event;

import android.content.Context;
import android.content.ContentResolver;
import android.util.Log;

public class EventsHelper {
	private static final String TAG = "EventsHelper";

	private Context mContext;

	public EventsHelper(Context context) {
		mContext = context;
		Log.i(TAG, "Created");
	}

	private ContentResolver getResolver() {
		return mContext.getContentResolver();
	}

	public void Update(Event event) {
		getResolver().update(Events.getUriWithId(event.getId()), Events.getContentValuesOf(event), null, null);
	}

	public void Delete(Event event) {
		if (0 != getResolver().delete(Events.getUriWithId(event.getId()), null, null))
			event.setId(null);		
	}

	public void Insert(Event event) {
		event.setId(getResolver().insert(Events.CONTENT_URI, Events.getContentValuesOf(event)).getLastPathSegment());			
	}
}
