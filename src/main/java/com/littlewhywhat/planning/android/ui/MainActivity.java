package com.littlewhywhat.planning.android.ui;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.EventsResolver;

import com.littlewhywhat.planning.android.ui.event.view.EventsFragment;
import com.littlewhywhat.planning.android.ui.util.DatePickerFragment;
import com.littlewhywhat.planning.android.ui.util.DatePickerFragment.OnDatePickedListener;
import com.littlewhywhat.planning.android.ui.calendar.view.CalendarIdPickerFragment.OnCalendarIdPickedListener;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;

import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class MainActivity extends Activity implements OnCalendarIdPickedListener, OnDatePickedListener {
	private static final String DATEPICKER_FRAGMENT_TAG = "DatePickerFragment";
	private static final String DEFAULT_EVENT_TITLE = "New Event";
	private static final DateFormat DF = DateFormat.getDateInstance();
	private Calendar mDatePicked;
	private String mCalendarIdPicked;
	private EventsResolver mEventsResolver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		mEventsResolver = new EventsResolver(getContentResolver());
		setDefaultDatePicked();
		setChooseDateButton();
		setInsertEventButton();
	}

	private void setDefaultDatePicked() {
	    mDatePicked = new GregorianCalendar();
	    mDatePicked.clear(Calendar.HOUR_OF_DAY);
	    mDatePicked.clear(Calendar.HOUR);
	    mDatePicked.clear(Calendar.MINUTE);
	    mDatePicked.clear(Calendar.SECOND);
	    mDatePicked.clear(Calendar.MILLISECOND);
	}

	@Override
	public void onDatePicked(int year, int monthOfYear, int dayOfMonth) {
		mDatePicked.set(Calendar.YEAR, year);
		mDatePicked.set(Calendar.MONTH, monthOfYear);
		mDatePicked.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		refresh();
	}
	
	@Override
	public void onCalendarIdPicked(String calendarId) {		
		mCalendarIdPicked = calendarId;
		refresh();
	}

	private void setInsertEventButton() {
		((Button)findViewById(R.id.insertDateButton)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				final Event event = Event.newInstance();
				event.setCalendarId(mCalendarIdPicked);
				event.getDtStart().setTimeInMillis(mDatePicked.getTimeInMillis());
				event.getDtEnd().setTimeInMillis(mDatePicked.getTimeInMillis());
				event.setTitle(DEFAULT_EVENT_TITLE);
				mEventsResolver.Insert(event);
			}			
		});
	}

	private void setChooseDateButton() {
		((Button)findViewById(R.id.chooseDateButton)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				final int year = mDatePicked.get(Calendar.YEAR);
				final int month = mDatePicked.get(Calendar.MONTH);
				final int dayOfMonth = mDatePicked.get(Calendar.DAY_OF_MONTH);
				final DialogFragment fragment = DatePickerFragment.newInstance(year, month, dayOfMonth);
				fragment.show(getFragmentManager(), DATEPICKER_FRAGMENT_TAG);
			};		
		});
	}
	
	private void refresh() {
		refreshTimeView();
		refreshEvents();
	}

	private void refreshTimeView() {
		((TextView)findViewById(R.id.timeView)).setText(DF.format(mDatePicked.getTime()));
	}

	private void refreshEvents() {
		final long dtStart = mDatePicked.getTimeInMillis();
		mDatePicked.roll(Calendar.DAY_OF_MONTH, true);
		final long dtEnd = mDatePicked.getTimeInMillis();
		mDatePicked.roll(Calendar.DAY_OF_MONTH, false);
		final EventsFragment fragment = (EventsFragment)getFragmentManager().findFragmentById(R.id.eventsFragment);
		fragment.restartLoader(dtStart, dtEnd, mCalendarIdPicked);
	}
}
