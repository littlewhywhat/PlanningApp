package appInterface;

import com.littlewhywhat.planningapp.R;

import android.content.Context;
import android.provider.CalendarContract.Events;
import android.widget.SimpleCursorAdapter;


public class EventsCursorAdapter extends SimpleCursorAdapter {
	private static final String[] from = new String[] { Events.TITLE, Events.DTSTART, Events.DTEND };
	public EventsCursorAdapter(Context context) {
		super(context, R.layout.event, null , from, 
				new int[] { R.id.eventTitle, R.id.eventStartTime, R.id.eventEndTime}, 0);
	}
	
}
