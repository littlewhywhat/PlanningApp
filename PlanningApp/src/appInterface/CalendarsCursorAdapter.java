package appInterface;

import com.littlewhywhat.planningapp.R;

import android.content.Context;
import android.provider.CalendarContract.Calendars;
import android.widget.SimpleCursorAdapter;


public class CalendarsCursorAdapter extends SimpleCursorAdapter {
	private final static String[] from = new String[] { Calendars.CALENDAR_DISPLAY_NAME };
	public CalendarsCursorAdapter(Context context) {
		super(context, R.layout.calendar, null, from, new int[] { R.id.calendarTitle }, 0);		
	}

}
