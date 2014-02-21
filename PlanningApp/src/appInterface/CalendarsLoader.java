package appInterface;

import CalendarContentHelper.CalendarsHelper;
import android.content.*;
import android.provider.CalendarContract.Calendars;

public class CalendarsLoader extends CursorLoader {

	public CalendarsLoader(Context context) {
		super(context, Calendars.CONTENT_URI, CalendarsHelper.CALENDAR_PROJECTION, CalendarsHelper.selection, CalendarsHelper.SelectionArgs, null);
	}

}
