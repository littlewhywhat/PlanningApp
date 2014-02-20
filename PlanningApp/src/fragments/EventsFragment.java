package fragments;
import CalendarContentHelper.DateInterval;

import com.littlewhywhat.planningapp.R;

import android.app.*;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.*;
import android.widget.*;
import appInterface.EventsCursorAdapter;
import appInterface.EventsLoader;
import appInterface.TimeHelper;

public class EventsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
		
	
	
	private static final String CALENDAR_ID_KEY = "CALENDAR_ID";
	private static final String TIME_KEY = "TIME";
	private String TAG = "EventsFragment";
		private EventsCursorAdapter eventsAdapter;
		private int LOADER_ID = 0;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			return inflater.inflate(R.layout.events_fragment, container, false);
		}
		@Override
		public void onActivityCreated(Bundle savedInstanceState)	{
			super.onActivityCreated(savedInstanceState);	
			Log.i(TAG, "OnActivityCreated()");
			eventsAdapter = new EventsCursorAdapter(getActivity());
			getListView().setAdapter(eventsAdapter);
		}
		private ListView getListView()	{
			return (ListView)getActivity().findViewById(R.id.eventslistView);
		}
		private Bundle getBundle(Time time, String calendarId)
		{
			Bundle bundle = new Bundle();
			bundle.putString(CALENDAR_ID_KEY, calendarId);
			bundle.putLong(TIME_KEY, time.toMillis(true));
			return bundle;
		}
		
		public void restartLoader(Time time, String calendarId)	 {
			Log.i(TAG, "restart Loader");
			getLoaderManager().restartLoader(LOADER_ID, getBundle(time, calendarId), this);
		}
		
		@Override
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			Log.i(TAG, "onCreateLoader()");
			return new EventsLoader(getActivity(), new DateInterval(TimeHelper.GetTime(args.getLong(TIME_KEY))), args.getString(CALENDAR_ID_KEY));
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


