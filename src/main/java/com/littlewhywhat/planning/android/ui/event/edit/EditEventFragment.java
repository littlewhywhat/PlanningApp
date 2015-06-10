package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.Events;
import com.littlewhywhat.planning.android.data.event.EventsLoaderById;
import com.littlewhywhat.planning.android.data.event.EventsResolver;
import com.littlewhywhat.planning.android.ui.event.Drag;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;

public class EditEventFragment extends Fragment 
		implements LoaderManager.LoaderCallbacks<Cursor>, SeekBar.OnSeekBarChangeListener {
	private static final int MINUTES_IN_HOUR = 60;
	private static final String EVENT_ID_KEY = "EVENT_ID";
	private static final String DEFAULT_TITLE_TEXT = "Drag event here!";
	private static final int LOADER_ID = 0;
	private static final DateFormat DF = DateFormat.getTimeInstance();

	private Event mEvent;
	private Calendar mCalendar = new GregorianCalendar();
	private EventsResolver mEventsResolver;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.edit_event_layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)	{
		super.onActivityCreated(savedInstanceState);	
		mEventsResolver = new EventsResolver(getActivity().getContentResolver());
		setPermanentListeners();
		refresh();
	}

	private void setPermanentListeners() {
		getEditEventLayout().setOnDragListener(new View.OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
				final int action = event.getAction();
				switch(action) {
					case DragEvent.ACTION_DRAG_STARTED:
						if (Drag.checkEvent(event)) {
							dragExited(v);
							return true;
						}
						return false;
					case DragEvent.ACTION_DRAG_EXITED:
						dragExited(v);
						return true;
					case DragEvent.ACTION_DRAG_ENTERED:
						dragEntered(v);
						return true;
					case DragEvent.ACTION_DRAG_LOCATION:
						return true;
					case DragEvent.ACTION_DROP:                	
						restartLoader(Drag.getEventId(event.getClipData()));
						recover(v);
						return true;      
					case DragEvent.ACTION_DRAG_ENDED:
						recover(v);
						return true;
					default:
						return false;
				}
			}
		});
		getUpdateButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mEventsResolver.Update(mEvent);				
			}			
		});
		getDeleteButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mEventsResolver.Delete(mEvent);
			}			
		});
	}

	private Button getUpdateButton() {
		return (Button) getActivity().findViewById(R.id.updateButton);
	}

	private Button getDeleteButton() {
		return (Button) getActivity().findViewById(R.id.deleteButton);
	}

	private SeekBar getEditDtStartSeekBar() { 
		return (SeekBar) getActivity().findViewById(R.id.editDtStartSeekBar);
	}

	private SeekBar getEditDtEndSeekBar() {
		return (SeekBar) getActivity().findViewById(R.id.editDtEndSeekBar);
	}

	private TextView getDtEndView() {
		return (TextView) getActivity().findViewById(R.id.editDtEndView);
	}

	private TextView getDtStartView() {
		return (TextView) getActivity().findViewById(R.id.editDtStartView);
	}

	private TextView getTitleView() {
		return (TextView) getActivity().findViewById(R.id.editTitleView);		
	}

	private RelativeLayout getEditEventLayout() {
		return (RelativeLayout) getActivity().findViewById(R.id.editEventFragment);
	}

	private void refresh() {
		if (mEvent != null) {
			final String dtStart = formatMillis(mEvent.getDtStart());
			final String dtEnd = formatMillis(mEvent.getDtEnd());
			setTextViews(mEvent.getTitle(), dtStart, dtEnd);
			setSeekBarsListener(this);
			final int dtStartMinutes = millisToMinutesOfDay(mEvent.getDtStart());
			final int dtEndMinutes = millisToMinutesOfDay(mEvent.getDtEnd());
			setSeekBars(dtStartMinutes, dtEndMinutes, true);
			getUpdateButton().setEnabled(true);
			getDeleteButton().setEnabled(true);
		} else {
			setTextViews(DEFAULT_TITLE_TEXT, null, null);
			setSeekBarsListener(null);
			setSeekBars(0, 0, false);
			getUpdateButton().setEnabled(false);
			getDeleteButton().setEnabled(false);
		}
	}

	private String formatMillis(long millis) {
		mCalendar.setTimeInMillis(millis);
		return DF.format(mCalendar.getTime());
	}

	private void setSeekBarsListener(SeekBar.OnSeekBarChangeListener listener) {
		getEditDtStartSeekBar().setOnSeekBarChangeListener(listener);
		getEditDtEndSeekBar().setOnSeekBarChangeListener(listener);
	}

	private void setTextViews(String title, String dtStart, String dtEnd) {
		getTitleView().setText(title);
		getDtStartView().setText(dtStart);
		getDtEndView().setText(dtEnd);	
	}

	private void setSeekBars(int startProgress, int endProgress, boolean isEnabled) {
		getEditDtStartSeekBar().setEnabled(isEnabled);
		getEditDtEndSeekBar().setEnabled(isEnabled);
		getEditDtStartSeekBar().setProgress(startProgress);
		getEditDtEndSeekBar().setProgress(endProgress);
	}

	private int millisToMinutesOfDay(long millis) {
		mCalendar.setTimeInMillis(millis);
		final int hours = mCalendar.get(Calendar.HOUR_OF_DAY);
		final int minutes = mCalendar.get(Calendar.MINUTE);
		return hours * MINUTES_IN_HOUR + minutes;
	}

	private long minutesOfDayToMillis(int minutesOfDay) {
		mCalendar.set(Calendar.HOUR_OF_DAY, minutesOfDay / MINUTES_IN_HOUR);
		mCalendar.set(Calendar.MINUTE, minutesOfDay % MINUTES_IN_HOUR);
		return mCalendar.getTimeInMillis();
	}

	private void dragExited(View view) {
		changeBackgroundColor(view, Color.GREEN);
	}

	private void dragEntered(View view) {
		changeBackgroundColor(view, Color.BLUE);
	}

	private void recover(View view) {
		changeBackgroundColor(view, Color.TRANSPARENT);
	}

	private void changeBackgroundColor(View view, int color) {
		view.setBackgroundColor(color);
		view.invalidate();
	}

	@Override
	public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
		final int id = seekbar.getId();
		switch (id) {
			case(R.id.editDtStartSeekBar):
				mEvent.setDtStart(minutesOfDayToMillis(progress));
				break;
			case(R.id.editDtEndSeekBar):
				mEvent.setDtEnd(minutesOfDayToMillis(progress));
				break;
		}
		refresh();
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekbar) { }
	@Override
	public void onStopTrackingTouch(SeekBar seekbar) { }

	private Bundle getBundle(String eventId) {
		Bundle bundle = new Bundle();
		bundle.putString(EVENT_ID_KEY, eventId);
		return bundle;
	}

	private void restartLoader(String eventId) {
		getLoaderManager().restartLoader(LOADER_ID, getBundle(eventId), this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new EventsLoaderById(getActivity(), args.getString(EVENT_ID_KEY));
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (cursor.moveToFirst())
			mEvent = getEventFromCursor(cursor);
		else
		 	mEvent = null;
		refresh();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mEvent = null;
		refresh();		
	}

	private Event getEventFromCursor(Cursor cursor) {
		final Event event = Event.newInstance();
		event.setId(cursor.getString(Events.ID_INDEX));
		event.setDtStart(cursor.getLong(Events.DTSTART_INDEX));
		event.setDtEnd(cursor.getLong(Events.DTEND_INDEX));
		event.setTitle(cursor.getString(Events.TITLE_INDEX));
		event.setCalendarId(cursor.getString(Events.CALENDARID_INDEX));
		event.setTimeZone(cursor.getString(Events.TIMEZONE_INDEX));
		return event;
	}
}