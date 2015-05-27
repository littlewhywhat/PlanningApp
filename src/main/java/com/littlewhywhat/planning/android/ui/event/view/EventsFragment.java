package com.littlewhywhat.planning.android.ui.event.view;

import com.littlewhywhat.planning.android.R;
import com.littlewhywhat.planning.android.data.event.EventsLoader;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ListView;

public class EventsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {	
	private static final String TAG = "EventsFragment";

	private static final String CALENDAR_ID_KEY = "CALENDAR_ID";
	private static final String DTSTART_KEY = "DTSTART";
	private static final String DTEND_KEY = "DTEND";
	private static final int LOADER_ID = 0;

	private EventsCursorAdapter eventsAdapter;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.events_layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);	
		Log.i(TAG, "OnActivityCreated()");
		eventsAdapter = new EventsCursorAdapter(getActivity());
		getListView().setAdapter(eventsAdapter);
	}

	private ListView getListView() {
		return (ListView)getActivity().findViewById(R.id.eventslistView);
	}

	private Bundle getBundle(long start, long end, String calendarId) {
		Bundle bundle = new Bundle();
		bundle.putString(CALENDAR_ID_KEY, calendarId);
		bundle.putString(DTSTART_KEY, String.valueOf(start));
		bundle.putString(DTEND_KEY, String.valueOf(end));
		return bundle;
	}
	
	public void restartLoader(long start, long end, String calendarId) {
		Log.i(TAG, "restart Loader");
		getLoaderManager().restartLoader(LOADER_ID, getBundle(start, end, calendarId), this);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Log.i(TAG, "onCreateLoader()");
		return new EventsLoader(getActivity(), args.getString(DTSTART_KEY), 
				args.getString(DTEND_KEY), args.getString(CALENDAR_ID_KEY));
	}
	
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		Log.i(TAG, "onLoadFinished()");
		eventsAdapter.swapCursor(cursor);			
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		Log.i(TAG, "onLoadReset()");
		eventsAdapter.swapCursor(null);		
	}
}


