package com.littlewhywhat.planning.android.ui.event.view;

import com.littlewhywhat.planning.android.R;

import android.content.Context;
import android.provider.CalendarContract;
import android.widget.TextView;
import android.widget.SimpleCursorAdapter;

import java.util.GregorianCalendar;
import java.util.Calendar;

class EventsCursorAdapter extends SimpleCursorAdapter {
	private static final String[] from = new String[] { CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND };
	private static final int[] to = new int[] { R.id.eventTitle, R.id.eventStartTime, R.id.eventEndTime};
	private static final int ZERO_FLAG = 0;

	private Calendar mCalendar = new GregorianCalendar();

	public EventsCursorAdapter(Context context) {
		super(context, R.layout.event, null , from, to, ZERO_FLAG);
	}

	@Override
	public void setViewText(TextView v, String text) {
		final int id = v.getId();
		String finalText = text;
		switch(id) {
			case R.id.eventStartTime: case R.id.eventEndTime:
				mCalendar.setTimeInMillis(Long.parseLong(text));
				finalText = mCalendar.getTime().toString();
				break;
			default: break;
		}
		v.setText(finalText);
	}
}
