package com.littlewhywhat.planning.android.ui.calendar.view;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.calendar.CalendarsLoader;
import com.littlewhywhat.planning.android.data.calendar.Calendars;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CalendarsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
	public interface CalendarChooseListener {
		public void onCalendarChoose(String calendarId);
	}

	private static final String EXCEPTION_MESSAGE = " must implement CalendarChooseListener";
	private static final int LOADER_ID = 0;
	
	private CalendarChooseListener mListener;
	private CalendarsCursorAdapter mCalendarsAdapter;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);		
		try {
			mListener = (CalendarChooseListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + EXCEPTION_MESSAGE);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.calendars_layout, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mCalendarsAdapter = new CalendarsCursorAdapter(getActivity());
		getSpinner().setAdapter(mCalendarsAdapter);
		getSpinner().setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				final Cursor calendar = (Cursor)parent.getItemAtPosition(position);
				mListener.onCalendarChoose(calendar.getString(Calendars.PROJECTION_ID_INDEX));
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});		
		getLoaderManager().initLoader(LOADER_ID, null, this);
	}

	private Spinner getSpinner() {
		return (Spinner)getActivity().findViewById(R.id.calendarSpinner);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CalendarsLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mCalendarsAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mCalendarsAdapter.swapCursor(null);
	}
}
