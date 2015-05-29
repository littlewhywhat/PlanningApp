package com.littlewhywhat.planning.android.ui;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.EventsResolver;

import com.littlewhywhat.planning.android.ui.event.OnEventDragListener;
import com.littlewhywhat.planning.android.ui.event.view.EventsFragment;

import com.littlewhywhat.planning.android.ui.util.DatePickerFragment;
import com.littlewhywhat.planning.android.ui.util.DatePickerFragment.DatePickerListener;
import com.littlewhywhat.planning.android.ui.calendar.view.CalendarsFragment.CalendarChooseListener;
import android.app.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class MainActivity extends Activity implements CalendarChooseListener, DatePickerListener {
	private final String TAG = "Main";
	private final String DATE_KEY = "Date";
	private GregorianCalendar calendar;
	private String calendarId;
	private OnEventDragListener dragListener = new OnEventDragListener();
	private EventsResolver mEventsResolver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Created");
		this.setContentView(R.layout.main);
		mEventsResolver = new EventsResolver(getContentResolver());
		setDefaultDate();
		setChooseDateButton();
		setDragListener();
		getInsertButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Event event = new Event(calendarId, calendar.getTimeInMillis());
				mEventsResolver.Insert(event);
			}			
		});
	}

	private Button getInsertButton() {
		return (Button) findViewById(R.id.insertDateButton);
	}

	private void setDragListener() {
		getDragView().setOnDragListener(dragListener);
		
	}
	private View getDragView()	{
		return findViewById(R.id.editEventFragment);
	}
	
	@Override
	public void onCalendarChoose(String CalendarId) {		
		calendarId = CalendarId;
		refreshEvents();
	}

	@Override
	public void onDateChanged(long millis) {
		calendar.setTimeInMillis(millis);
		setTimeViewText();
		refreshEvents();		
	}

	@Override
	public String getDateKey() {
		return DATE_KEY;
	}
	private void setDefaultDate() {
	    calendar = new GregorianCalendar();
	    calendar.clear(Calendar.HOUR_OF_DAY);
	    calendar.clear(Calendar.HOUR);
	    calendar.clear(Calendar.MINUTE);
	    calendar.clear(Calendar.SECOND);
	    calendar.clear(Calendar.MILLISECOND);
	    setTimeViewText();
	}
	
	private TextView getTimeView() {
		return (TextView)findViewById(R.id.timeView);
	}
	
	private void setTimeViewText() {
		getTimeView().setText(calendar.getTime().toString());
	}
	private void showDatePickerDialog() {
		DialogFragment newFragment = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putLong(DATE_KEY, calendar.getTimeInMillis());
		newFragment.setArguments(args);
		newFragment.show(getFragmentManager(), "datePicker");
	}

	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			Log.i(TAG, "Button clicked");
			showDatePickerDialog();		
		}
	};

	private void setChooseDateButton() {
		getChooseDateButton().setOnClickListener(listener);		
	}
	
	private Button getChooseDateButton() {
		return (Button)findViewById(R.id.chooseDateButton);
	}
	
	private void refreshEvents() {
		EventsFragment fragment = (EventsFragment)getFragmentManager().findFragmentById(R.id.eventsFragment);
		long start = calendar.getTimeInMillis();
		calendar.roll(Calendar.DAY_OF_MONTH, true);
		long end = calendar.getTimeInMillis();
		calendar.roll(Calendar.DAY_OF_MONTH, false);
		fragment.restartLoader(start, end, calendarId);
	}
}
