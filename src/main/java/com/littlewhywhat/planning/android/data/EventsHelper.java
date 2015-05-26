package com.littlewhywhat.planning.android.data;

import android.content.ContentUris;
import android.content.Context;
import android.content.ContentValues;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.util.Log;

public class EventsHelper {
	private static final String TAG = "EventsHelper";

	public static final String[] EVENT_PROJECTION = new String[] {
		Events._ID,                    // 0
		Events.TITLE,                  // 1
		Events.DTSTART,         		// 2
		Events.DTEND,                  // 3
		Events.CALENDAR_ID,		   // 4
	};
	public static final int PROJECTION_ID_INDEX = 0;
	public static final int PROJECTION_TITLE_INDEX = 1;
	public static final int PROJECTION_DTSTART_INDEX = 2;
	public static final int PROJECTION_DTEND_INDEX = 3;
	public static final int PROJECTION_CALENDARID_INDEX = 4;
	public static final String selection = "((" + Events.DTSTART + " >= ?) AND (" 
            + Events.DTEND + " <= ?) AND (" 
            + Events.DELETED + " != '1') AND (" 
            + Events.CALENDAR_ID + " <= ?)) ";
	
	private Context appContext;

	public EventsHelper(Context context) {
		appContext = context;
		Log.i(TAG, "Created");
	}	

	protected Uri getContentUri() {
		return Events.CONTENT_URI;
	}

	protected String[] getProjection() {
		return EVENT_PROJECTION;
	}

	protected String getSelection() {
		return selection;
	}

	protected ContentResolver getResolver() {
		return appContext.getContentResolver();
	}

	private Cursor getCursorByArgs(String[] selectionArgs) {
		return getResolver().query(getContentUri(), getProjection(), getSelection(), selectionArgs , null); 
	}

	public Cursor getCursor(String DTSTART, String DTEND, String calendarId) {	
		return getCursorByArgs(new String[] { DTSTART, DTEND, calendarId });
	}
	
	private ContentValues getContentValuesForEvent(IEvent event) {
		ContentValues values = new ContentValues();		
		values.put(Events.CALENDAR_ID, event.getCalendarId());
		values.put(Events.EVENT_TIMEZONE, event.getTimeZone());
		values.put(Events.DTSTART, event.getDTSTARTinMillis());
		values.put(Events.DTEND, event.getDTENDinMillis());
		values.put(Events.TITLE, event.getTitle());
		return values;
	}

	public void Update(IContentItem item) {
		IEvent event = (IEvent)item;
		getResolver().update(ContentUris.withAppendedId(getContentUri(), 
				Long.parseLong(event.getID())), getContentValuesForEvent(event), null, null);
	}

	public void Delete(IContentItem item) {
		IEvent event = (IEvent)item;
		if (0 != getResolver().delete(ContentUris.withAppendedId(getContentUri(), Long.parseLong(event.getID())), null, null))
			event.setID(null);		
	}

	public void Insert(IContentItem item) {
		IEvent event = (IEvent)item;
		event.setID(getResolver().insert(getContentUri(), getContentValuesForEvent(event)).getLastPathSegment());			
	}
}
