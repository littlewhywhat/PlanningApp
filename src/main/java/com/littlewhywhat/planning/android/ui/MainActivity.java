package com.littlewhywhat.planning.android.ui;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.EventsResolver;

import com.littlewhywhat.planning.android.ui.event.OnEventDragListener;
import com.littlewhywhat.planning.android.ui.event.view.EventsFragment;
import com.littlewhywhat.planning.android.ui.util.DatePickerFragment;
import com.littlewhywhat.planning.android.ui.util.DatePickerFragment.DatePickerListener;
import com.littlewhywhat.planning.android.ui.calendar.view.CalendarsFragment.CalendarChooseListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class MainActivity extends Activity implements CalendarChooseListener, DatePickerListener {
	private static final String DATEPICKER_FRAGMENT_TAG = "DatePickerFragment";
	private Calendar mCalendar;
	private String mCalendarId;
	private EventsResolver mEventsResolver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		mEventsResolver = new EventsResolver(getContentResolver());
		setDefaultDate();
		setTimeViewText();
		setChooseDateButton();
		setInsertEventButton();
	}

	@Override 
	public void refresh() {
		setTimeViewText();
		refreshEvents();
	}

	@Override
	public Calendar getCalendar() {
		return mCalendar;
	}
	
	@Override
	public void onCalendarChoose(String calendarId) {		
		mCalendarId = calendarId;
		refreshEvents();
	}

	private void setDefaultDate() {
	    mCalendar = new GregorianCalendar();
	    mCalendar.clear(Calendar.HOUR_OF_DAY);
	    mCalendar.clear(Calendar.HOUR);
	    mCalendar.clear(Calendar.MINUTE);
	    mCalendar.clear(Calendar.SECOND);
	    mCalendar.clear(Calendar.MILLISECOND);
	}

	private void setTimeViewText() {
		((TextView)findViewById(R.id.timeView)).setText(mCalendar.getTime().toString());
	}

	private void setInsertEventButton() {
		((Button)findViewById(R.id.insertDateButton)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Event event = new Event(mCalendarId, mCalendar.getTimeInMillis());
				mEventsResolver.Insert(event);
			}			
		});
	}

	private void setChooseDateButton() {
		((Button)findViewById(R.id.chooseDateButton)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				showDatePickerDialog();		
			};		
		});
	}

	private void showDatePickerDialog() {
		new DatePickerFragment().show(getFragmentManager(), DATEPICKER_FRAGMENT_TAG);
	}
	
	private void refreshEvents() {
		final long dtStart = mCalendar.getTimeInMillis();
		mCalendar.roll(Calendar.DAY_OF_MONTH, true);
		final long dtEnd = mCalendar.getTimeInMillis();
		mCalendar.roll(Calendar.DAY_OF_MONTH, false);
		final EventsFragment fragment = (EventsFragment)getFragmentManager().findFragmentById(R.id.eventsFragment);
		fragment.restartLoader(dtStart, dtEnd, mCalendarId);
	}
}
