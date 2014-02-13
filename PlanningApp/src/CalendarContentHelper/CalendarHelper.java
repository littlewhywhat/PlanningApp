package CalendarContentHelper;
import java.util.*;

import android.content.*;
import android.database.Cursor;

import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.util.Log;

public class CalendarHelper 
{
	private final String TAG = "CalendarHelper";
	private Context appContext;	
	public CalendarHelper(Context context)
	{
		appContext = context;
		Log.i(TAG, "Created");
	}
	public void FillIEventsDic(IEventsDictionary eventsDic, DateInterval interval)
	{
		Log.i(TAG, "FillingDic");
		Cursor cursor = getCursorByDateInterval(getSelectionArgs(interval));
		while (cursor.moveToNext())
			eventsDic.AddIEvent(fillEventByCursor(cursor, eventsDic.GetFactory().GetNewEvent()));
	}
	
	private IEvent fillEventByCursor(Cursor cursor, IEvent event) 
	{	
		event.setID(cursor.getString(PROJECTION_ID_INDEX));
		event.setTitle(cursor.getString(PROJECTION_TITLE_INDEX));
		event.setDTSTART(new Date(cursor.getLong(PROJECTION_DTSTART_INDEX)));
		event.setDTEND(new Date(cursor.getLong(PROJECTION_DTEND_INDEX)));
		return event;
	}
	private Cursor getCursorByDateInterval(String[] selectionArgs) 
	{	
		return appContext.getContentResolver()
				.query(Events.CONTENT_URI, EVENT_PROJECTION, selection, selectionArgs , null);
	}
	private String[] getSelectionArgs(DateInterval interval) {
		return new String[] { String.valueOf(interval.getDTSTART().getTime()), 
				  		      String.valueOf(interval.getDTEND().getTime()) };		
	}
	private final String selection = "((" + CalendarContract.Events.DTSTART + " >= ?) AND (" 
	                        + CalendarContract.Events.DTEND + " <= ?)) ";
	private static final String[] EVENT_PROJECTION = new String[] {
		CalendarContract.Events._ID,                    // 0
		CalendarContract.Events.TITLE,                  // 1
		CalendarContract.Events.DTSTART,         		// 2
		CalendarContract.Events.DTEND                   // 3
	};
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_TITLE_INDEX = 1;
	private static final int PROJECTION_DTSTART_INDEX = 2;
	private static final int PROJECTION_DTEND_INDEX = 3;
	
	private ContentResolver getResolver()
	{
		return appContext.getContentResolver();
	}
	private ContentValues getContentValuesForEvent(IEvent event)
	{
		ContentValues values = new ContentValues();		
		values.put(Events.CALENDAR_ID, 1);
		values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());
		values.put(Events.DTSTART, event.getDTSTART().getTime());
		values.put(Events.DTEND, event.getDTEND().getTime());
		values.put(Events.TITLE, event.getTitle());
		return values;
	}
	
	public void FillIEventsDic(IEventsDictionary eventsDic)
	{   
		Cursor cursor = getCursorByDateInterval(null);
		while (cursor.moveToNext())
			eventsDic.AddIEvent(fillEventByCursor(cursor, eventsDic.GetFactory().GetNewEvent()));
	}
	
	public void Update(IEvent event)
	{
		getResolver().update(ContentUris.withAppendedId(Events.CONTENT_URI, 
				Long.parseLong(event.getID())), getContentValuesForEvent(event), null, null);
	}
	
	public void Delete(IEvent event)
	{
		if (0 != getResolver().delete(ContentUris.withAppendedId(Events.CONTENT_URI, Long.parseLong(event.getID())), null, null))
			event.setID(null);		
	}
	
	public void Insert(IEvent event)
	{
		event.setID(getResolver().insert(Events.CONTENT_URI, getContentValuesForEvent(event)).getLastPathSegment());			
	}
}
