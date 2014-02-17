package appInterface;

import com.littlewhywhat.planningapp.R;

import contentItemsLib.Calendar;
import contentItemsLib.ContentItemsDictionary;
import contentItemsLib.Event;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventsAdapter extends ContentArrayAdapter {

	private static final String TIME_FORMAT = "%Y.%m.%d %H:%M:%S";

	public EventsAdapter(ContentItemsDictionary dic) {
		super(dic);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Event event = (Event)getItem(position);
		LayoutInflater inflater = (LayoutInflater)appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout itemLayout =  (RelativeLayout)inflater.inflate(R.layout.event, null);

		final TextView titleView = (TextView)itemLayout.findViewById(R.id.eventTitle);
		titleView.setText(event.getTitle());
		if (event.getDTSTART() != null)
		{
			final TextView startDate = (TextView)itemLayout.findViewById(R.id.eventStartTime);
			startDate.setText(event.getDTSTART().format(TIME_FORMAT));
		}
		if (event.getDTEND() != null)
		{
			final TextView endDate = (TextView)itemLayout.findViewById(R.id.eventEndTime);
			endDate.setText(event.getDTEND().format(TIME_FORMAT));
		}
		return itemLayout;
	}

}
