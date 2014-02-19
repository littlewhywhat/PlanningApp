package appInterface;

import CalendarContentHelper.DateInterval;
import android.content.*;
import android.provider.CalendarContract.Events;

public class EventsLoader extends CursorLoader {
	private static final String[] EVENT_PROJECTION = new String[] {
		Events._ID,                    // 0
		Events.TITLE,                  // 1
		Events.DTSTART,         		// 2
		Events.DTEND,                  // 3
		Events.CALENDAR_ID
	};
	private final static String selection = "((" + Events.DTSTART + " >= ?) AND (" 
            + Events.DTEND + " <= ?)AND (" 
            + Events.CALENDAR_ID + " <= ?)) ";
	private static String[] getCustomSelectionArgs(DateInterval interval, String calendarId) {
		return new String[] {
				String.valueOf(interval.getDTSTART().toMillis(true)),
				String.valueOf(interval.getDTEND().toMillis(true)),
				calendarId
		};
	}
	public EventsLoader(Context context, DateInterval interval, String calendarId) {
		super(context, Events.CONTENT_URI , EVENT_PROJECTION, selection, 
				getCustomSelectionArgs(interval, calendarId), null);
	}

}
