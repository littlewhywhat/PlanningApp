package com.littlewhywhat.planning.android.ui.event.view;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.EventsLoader;
import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.ui.event.Drag;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ListView;

public class EventsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {	
	private static final String CALENDAR_ID_BUNDLE_KEY = "CALENDAR_ID";
	private static final String DTSTART_BUNDLE_KEY = "DTSTART";
	private static final String DTEND_BUNDLE_KEY = "DTEND";
	private static final int LOADER_ID = 0;

	private EventsCursorAdapter mEventsAdapter;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.events_layout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);	
		mEventsAdapter = new EventsCursorAdapter(getActivity());
		getListView().setAdapter(mEventsAdapter);
		getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				final View.DragShadowBuilder builder = new View.DragShadowBuilder(view);
				view.startDrag(Drag.getClipData(String.valueOf(id)), builder, null, 0);
				return true;
			}
		});
	}

	private ListView getListView() {
		return (ListView)getActivity().findViewById(R.id.eventslistView);
	}
	
	public void restartLoader(long dtStart, long dtEnd, String calendarId) {
		getLoaderManager().restartLoader(LOADER_ID, getBundle(dtStart, dtEnd, calendarId), this);
	}
	
	private Bundle getBundle(long dtStart, long dtEnd, String calendarId) {
		Bundle bundle = new Bundle();
		bundle.putString(CALENDAR_ID_BUNDLE_KEY, calendarId);
		bundle.putString(DTSTART_BUNDLE_KEY, String.valueOf(dtStart));
		bundle.putString(DTEND_BUNDLE_KEY, String.valueOf(dtEnd));
		return bundle;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		final String dtStart = args.getString(DTSTART_BUNDLE_KEY);
		final String dtEnd = args.getString(DTEND_BUNDLE_KEY);
		final String calendarId = args.getString(CALENDAR_ID_BUNDLE_KEY);
		return new EventsLoader(getActivity(), dtStart, dtEnd, calendarId);
	}
	
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mEventsAdapter.swapCursor(cursor);			
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mEventsAdapter.swapCursor(null);		
	}
}


