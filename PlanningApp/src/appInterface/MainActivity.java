package appInterface;


import com.littlewhywhat.planningapp.R;


import CalendarContentHelper.DateInterval;
import android.app.*;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity 
{
	private final static String TAG = "Main";
	private static final String TIME_FORMAT = "%Y.%m.%d";
	public static TextView timeView;
	public static Time time;
	public static String calendarId;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Created");
		this.setContentView(R.layout.main2);
		timeView = (TextView)findViewById(R.id.timeView);
		setDefaultDate();
		setChooseDateButton();
	}
	private void setDefaultDate() {
	    time = new Time();
	    time.setToNow();
	    getTimeView().setText(getTimeText());
	}
	private static String getTimeText()
	{
		return time.format(TIME_FORMAT);
	}
	
	private TextView getTimeView()	{
		return (TextView)findViewById(R.id.timeView);
	}

	private void showDatePickerDialog() {
		DialogFragment newFragment = new DatePickerFragment();
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
	
	public static class DatePickerFragment extends DialogFragment implements
	DatePickerDialog.OnDateSetListener {
		boolean fires = false;
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new DatePickerDialog(getActivity(), this, time.year, time.month, time.monthDay);
		}				
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			if (!fires) {
				Log.i(TAG, "date selected" + year + ":" + monthOfYear + ":" + dayOfMonth );
				time.set(dayOfMonth, monthOfYear, year);
				timeView.setText(getTimeText());
				EventsFragment fragment = (EventsFragment)getFragmentManager().findFragmentById(R.id.eventsFragment);
				fragment.restartLoader();
				fires = true;
			}
			else
				fires = false;
		}
	}
	
	public static class EventsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
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
		
		public void restartLoader()	 {
			Log.i(TAG, "restart Loader");
			if (getLoaderManager().getLoader(LOADER_ID) != null)
				getLoaderManager().restartLoader(LOADER_ID, null, this);
			else
				getLoaderManager().initLoader(LOADER_ID, null, this);
		}
		
		@Override
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			Log.i(TAG, "onCreateLoader()");
			return new EventsLoader(getActivity(), new DateInterval(time), calendarId);
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
	
	public static class CalendarsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
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
				calendarId = calendar.getString(0);
				EventsFragment fragment = (EventsFragment)getFragmentManager().findFragmentById(R.id.eventsFragment);
				fragment.restartLoader();
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
}
