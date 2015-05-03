package com.littlewhywhat.planning.android.ui.fragments;

import android.app.*;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import com.littlewhywhat.planning.android.ui.CalendarsCursorAdapter;
import com.littlewhywhat.planning.android.ui.CalendarsLoader;

import com.littlewhywhat.planning.android.R;

public class CalendarsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
	protected static final int CALENDAR_ID_COLUMN = 0;
	private CalendarChooseListener listener;
	public interface CalendarChooseListener {
		public void onCalendarChoose(String calendarId);
	}
	@Override
	public void onAttach(Activity activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);		
		try {
			listener = (CalendarChooseListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement CalendarChooseListener");
		}
	}
	
	private CalendarsCursorAdapter calendarsAdapter;
	private String TAG = "CalendarsFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "OnCreateView()");
		return inflater.inflate(R.layout.calendars_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "OnActivityCreated()");
		calendarsAdapter = new CalendarsCursorAdapter(getActivity());
		setSpinner();
		getLoaderManager().initLoader(0, null, this);
	}
	private Spinner getSpinner() {
		return (Spinner)getActivity().findViewById(R.id.calendarSpinner);
	}
	
	private void setSpinner() {
		getSpinner().setOnItemSelectedListener(selectedListener);
		getSpinner().setAdapter(calendarsAdapter);
	}
	
	private OnItemSelectedListener selectedListener = new OnItemSelectedListener()
	{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
		{
			Log.i(TAG, "CalendarSelected");
			Spinner spinner = (Spinner)parent;
			Cursor calendar = (Cursor)spinner.getItemAtPosition(position);
			listener.onCalendarChoose(calendar.getString(CALENDAR_ID_COLUMN));
		}
		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{
		}
	};
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CalendarsLoader(getActivity());
	}
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		calendarsAdapter.swapCursor(cursor);
		}
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		calendarsAdapter.swapCursor(null);
		}
}
