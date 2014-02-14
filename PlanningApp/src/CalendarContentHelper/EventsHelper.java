package CalendarContentHelper;
import java.util.*;

import CalendarContentHelper.IContentItem.IContentItem;
import CalendarContentHelper.IContentItem.IEvent;
import android.content.*;
import android.database.Cursor;

import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.util.Log;

public class EventsHelper extends ContentHelper
{
	private final String TAG = "EventsHelper";
	private String CalendarId;
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
	private final String selection = "((" + CalendarContract.Events.DTSTART + " >= ?) AND (" 
            + CalendarContract.Events.DTEND + " <= ?)) ";
	public EventsHelper(Context context, String calendarId)
	{
		super(context);
		CalendarId = calendarId;
		Log.i(TAG, "Created");
	}
	@Override
	protected void fillIContentItemOtherFields(Cursor cursor, IContentItem contentItem) 
	{
		IEvent event = (IEvent)contentItem;
		event.setTitle(cursor.getString(PROJECTION_TITLE_INDEX));
		event.setDTSTART(new Date(cursor.getLong(PROJECTION_DTSTART_INDEX)));
		event.setDTEND(new Date(cursor.getLong(PROJECTION_DTEND_INDEX)));	
	}
	@Override
	protected int getProjectionIdIndex() 
	{
		return PROJECTION_ID_INDEX;
	}
	@Override
	protected Uri getContentUri() 
	{
		return Events.CONTENT_URI;
	}
	@Override
	protected String[] getProjection() 
	{
		return EVENT_PROJECTION;
	}
	@Override
	protected String getSelection() {
		return selection;
	}

	public void FillIEventsDic(IContentItemsDictionary eventsDic, DateInterval interval)
	{
		Log.i(TAG, "FillingDic");
		this.FillIContentItemDic(eventsDic, interval.ConvertToStringArgs());
	}
	
	private ContentResolver getResolver()
	{
		return getAppContext().getContentResolver();
	}
	private ContentValues getContentValuesForEvent(IEvent event)
	{
		ContentValues values = new ContentValues();		
		values.put(Events.CALENDAR_ID, CalendarId);
		values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());
		values.put(Events.DTSTART, event.getDTSTART().getTime());
		values.put(Events.DTEND, event.getDTEND().getTime());
		values.put(Events.TITLE, event.getTitle());
		return values;
	}
	@Override	
	public void Update(IContentItem item)
	{
		IEvent event = (IEvent)item;
		getResolver().update(ContentUris.withAppendedId(getContentUri(), 
				Long.parseLong(event.getID())), getContentValuesForEvent(event), null, null);
	}
	@Override
	public void Delete(IContentItem item)
	{
		IEvent event = (IEvent)item;
		if (0 != getResolver().delete(ContentUris.withAppendedId(getContentUri(), Long.parseLong(event.getID())), null, null))
			event.setID(null);		
	}
	@Override
	public void Insert(IContentItem item)
	{
		IEvent event = (IEvent)item;
		event.setID(getResolver().insert(getContentUri(), getContentValuesForEvent(event)).getLastPathSegment());			
	}
	
}
