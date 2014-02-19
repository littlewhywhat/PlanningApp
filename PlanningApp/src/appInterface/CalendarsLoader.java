package appInterface;

import android.content.*;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;

public class CalendarsLoader extends CursorLoader {
	private static final String selection = "((" + Calendars.VISIBLE + " = ?) AND (" 
            + Calendars.SYNC_EVENTS + " = ?)) ";
	private static final String[] selectionArgs = new String[] { "1" , "1" };
	private static final String[] CALENDAR_PROJECTION = new String[] {
		CalendarContract.Calendars._ID,                      // 0
		CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,    // 1
	};
	public static final int PROJECTION_ID_INDEX = 0;
	public static final int PROJECTION_NAME_INDEX = 1;
	
	public CalendarsLoader(Context context) {
		super(context, Calendars.CONTENT_URI, CALENDAR_PROJECTION, selection, selectionArgs, null);
	}

}
