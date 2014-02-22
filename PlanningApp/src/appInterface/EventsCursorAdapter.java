package appInterface;

import com.littlewhywhat.planningapp.R;

import contentItemsLib.Event;


import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Events;
import android.widget.*;
import android.view.*;


public class EventsCursorAdapter extends SimpleCursorAdapter {
	
	
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
		final Event event = new Event(appContext, cursor);
		
		LayoutInflater inflater = (LayoutInflater)appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout itemLayout =  (RelativeLayout)inflater.inflate(R.layout.event, null);	
		final TextView titleView = (TextView)itemLayout.findViewById(R.id.eventTitle);
		titleView.setText(event.getTitle());
		final TextView startDate = (TextView)itemLayout.findViewById(R.id.eventStartTime);
		startDate.setText(event.getDTSTARTinString());
		final TextView endDate = (TextView)itemLayout.findViewById(R.id.eventEndTime);
		endDate.setText(event.getDTENDinString());
		
		itemLayout.setOnLongClickListener(new View.OnLongClickListener() {			
			@Override
			public boolean onLongClick(View v) {
				
				View.DragShadowBuilder builder = new View.DragShadowBuilder(v);
				v.startDrag(event.getClipData(), builder, null, 0);
				return true;
			}
		});
		return itemLayout;
	}

}
