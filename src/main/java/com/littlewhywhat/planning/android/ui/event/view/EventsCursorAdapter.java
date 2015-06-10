package com.littlewhywhat.planning.android.ui.event.view;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Events;

import android.content.Context;
import android.widget.TextView;
import android.widget.SimpleCursorAdapter;

import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;

class EventsCursorAdapter extends SimpleCursorAdapter {
	private static final String[] FROM = new String[] { 
		Events.PROJECTION[Events.TITLE_INDEX], 
		Events.PROJECTION[Events.DTSTART_INDEX], 
		Events.PROJECTION[Events.DTEND_INDEX] 
	};
	private static final int[] TO = new int[] { R.id.eventTitle, R.id.eventStartTime, R.id.eventEndTime};
	private static final int ZERO_FLAG = 0;
	private static final DateFormat DF = DateFormat.getTimeInstance();

	private Calendar mCalendar = new GregorianCalendar();

	public EventsCursorAdapter(Context context) {
		super(context, R.layout.event, null , FROM, TO, ZERO_FLAG);
	}

	@Override
	public void setViewText(TextView v, String text) {
		final int id = v.getId();
		String finalText = text;
		switch(id) {
			case R.id.eventStartTime: case R.id.eventEndTime:
				mCalendar.setTimeInMillis(Long.parseLong(text));
				finalText = DF.format(mCalendar.getTime());
				break;
			default: break;
		}
		v.setText(finalText);
	}
}