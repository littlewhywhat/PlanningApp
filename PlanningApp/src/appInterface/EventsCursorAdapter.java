package appInterface;

import com.littlewhywhat.planningapp.R;

import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Events;
import android.widget.*;
import android.text.format.Time;
import android.view.*;


public class EventsCursorAdapter extends SimpleCursorAdapter {
	private static final String TIME_FORMAT = "%Y.%m.%d %H:%M:%S";
	private static final int TITLE_INDEX = 1;
	private static final int DTSTART_INDEX = 2;
	private static final int DTEND_INDEX = 3;	
	private static final String[] from = new String[] { Events.TITLE, Events.DTSTART, Events.DTEND };
	private Context appContext;
	public EventsCursorAdapter(Context context) {
		super(context, R.layout.event, null , from, 
				new int[] { R.id.eventTitle, R.id.eventStartTime, R.id.eventEndTime}, 0);
		appContext = context;		
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Cursor cursor = (Cursor)getItem(position);
		String title = cursor.getString(TITLE_INDEX);
		Time DTSTART = new Time();
		DTSTART.set(cursor.getLong(DTSTART_INDEX));
		Time DTEND = new Time();
		DTEND.set(cursor.getLong(DTEND_INDEX));	
		LayoutInflater inflater = (LayoutInflater)appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout itemLayout =  (RelativeLayout)inflater.inflate(R.layout.event, null);	
		final TextView titleView = (TextView)itemLayout.findViewById(R.id.eventTitle);
		titleView.setText(title);
		final TextView startDate = (TextView)itemLayout.findViewById(R.id.eventStartTime);
		startDate.setText(DTSTART.format(TIME_FORMAT));
		final TextView endDate = (TextView)itemLayout.findViewById(R.id.eventEndTime);
		endDate.setText(DTEND.format(TIME_FORMAT));		
		return itemLayout;
	}

}
