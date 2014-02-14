package appInterface;

import com.littlewhywhat.planningapp.R;

import contentItemsLib.Calendar;
import contentItemsLib.ContentItemsDictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class CalendarsAdapter extends ContentArrayAdapter {

	public CalendarsAdapter(ContentItemsDictionary dic) {
		super(dic);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

				final Calendar calendar = (Calendar)getItem(position);
				LayoutInflater inflater = (LayoutInflater)appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				RelativeLayout itemLayout =  (RelativeLayout)inflater.inflate(R.layout.calendar, null);

				final TextView titleView = (TextView)itemLayout.findViewById(R.id.titleView);
				titleView.setText(calendar.getName());
	
				return itemLayout;
	}

}
