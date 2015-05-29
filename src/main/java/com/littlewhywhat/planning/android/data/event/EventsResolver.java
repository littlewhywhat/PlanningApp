package com.littlewhywhat.planning.android.data.event;

import android.content.ContentResolver;

public class EventsResolver {
	private ContentResolver mContentResolver;

	public EventsResolver(ContentResolver contentResolver) {
		mContentResolver = contentResolver;
	}

	public void Update(Event event) {
		mContentResolver.update(Events.getUriWithId(event.getId()), Events.getContentValuesOf(event), null, null);
	}

	public void Delete(Event event) {
		mContentResolver.delete(Events.getUriWithId(event.getId()), null, null);		
	}

	public void Insert(Event event) {
		mContentResolver.insert(Events.CONTENT_URI, Events.getContentValuesOf(event)).getLastPathSegment();			
	}
}
