package CalendarContentHelper;
import java.util.*;

import android.content.*;
import android.database.Cursor;

import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;

public class CalendarHelper 
{
	 
	String selection = "((" + CalendarContract.Events._ID + " = ?) AND (" 
	                        + CalendarContract.Events.TITLE + " = ?) AND ("
	                        + CalendarContract.Events.DTSTART + " = ?) AND ("
	                        + CalendarContract.Events.DURATION + " = ?)) ";
	public static final String[] EVENT_PROJECTION = new String[] {
		CalendarContract.Events._ID,                    // 0
		CalendarContract.Events.TITLE,                  // 1
		CalendarContract.Events.DTSTART,         		// 2
		CalendarContract.Events.DTEND                   // 3
	};
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_TITLE_INDEX = 1;
	private static final int PROJECTION_DTSTART_INDEX = 2;
	private static final int PROJECTION_DTEND_INDEX = 3;
	public static void FillIEventsDic(Context context, IEventsDictionary eventsDic, Date date)
	{   
		
		Cursor cursor = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, EVENT_PROJECTION, null, null, null);
		while (cursor.moveToNext())
		{
			IEvent event = eventsDic.GetFactory().GetNewEvent();
			event.setID(cursor.getString(PROJECTION_ID_INDEX));
			event.setTitle(cursor.getString(PROJECTION_TITLE_INDEX));
			event.setDTSTART(new Date(cursor.getLong(PROJECTION_DTSTART_INDEX)));
			event.setDTEND(new Date(cursor.getLong(PROJECTION_DTEND_INDEX)));
			eventsDic.AddIEvent(event);
		}		
	}
	
	public static void Update(Context context, IEvent event)
	{
		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		
		values.put(Events.CALENDAR_ID, 1);
		values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());
		values.put(Events.DTSTART, event.getDTSTART().getTime());
		values.put(Events.DTEND, event.getDTEND().getTime());
		values.put(Events.TITLE, event.getTitle());
		cr.update(ContentUris.withAppendedId(Events.CONTENT_URI, Long.parseLong(event.getID())), values, null, null);
	}
	
	public static int Delete(Context context, IEvent event)
	{
		ContentResolver cr = context.getContentResolver();
		 return cr.delete(ContentUris.withAppendedId(Events.CONTENT_URI, Long.parseLong(event.getID())), null, null);
		
	}
	
	public static void Insert(Context context, IEvent event)
	{
		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		
		values.put(Events.CALENDAR_ID, 1);
		values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());
		values.put(Events.DTSTART, event.getDTSTART().getTime());
		values.put(Events.DTEND, event.getDTEND().getTime());
		values.put(Events.TITLE, event.getTitle());
		event.setID(cr.insert(Events.CONTENT_URI, values).getLastPathSegment());
			
	}
}
