package appInterface;


import com.littlewhywhat.planningapp.R;

import contentItemsLib.Calendar;
import contentItemsLib.CalendarsDictionary;
import contentItemsLib.EventsDictionary;

import CalendarContentHelper.DateInterval;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity 
{
	private static final String TIME_FORMAT = "%Y.%m.%d";
	public static TextView timeView;
	public static ListView listView;
	public static Time time;
	public static String calendarId;
	public static EventsDictionary dictionary;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main2);
		timeView = (TextView)findViewById(R.id.timeView);
		listView = getEventsListView();
		setCalendarSpinner();
		dictionary = new EventsDictionary(getApplicationContext(), calendarId);
		setDefaultDate();
		setChooseDateButton();
		attendListAdapter(listView);
	}
	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			showDatePickerDialog();
			
		}
	};
	private OnItemSelectedListener selectedListener = new OnItemSelectedListener()
	{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
		{
			Spinner spinner = (Spinner)parent;
			Calendar calendar = (Calendar)spinner.getItemAtPosition(position);
			calendarId = calendar.getID();
		}
		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{
			
		}
	};
	private static String getTimeText()
	{
		return time.format(TIME_FORMAT);
	}
	
	public static void attendListAdapter(ListView list) {
		
		dictionary.Fill(new DateInterval(time));
		list.setAdapter(new EventsAdapter(dictionary));
	}
	private ListView getEventsListView() {
		return (ListView)findViewById(R.id.eventsListView);
	}
	private void setChooseDateButton() {
		getChooseDateButton().setOnClickListener(listener);		
	}
	
	private Button getChooseDateButton() {
		return (Button)findViewById(R.id.chooseDateButton);
	}
	
	private Spinner getCalendarSpinner() {
		return (Spinner) findViewById(R.id.calendarSpinner);
	} 
	
	private TextView getTimeView()	{
		return (TextView)findViewById(R.id.timeView);
	}
	
	private void setCalendarSpinner() {
		CalendarsDictionary dictionary = new CalendarsDictionary(getApplicationContext());
		dictionary.Fill();
		CalendarsAdapter adapter = new CalendarsAdapter(dictionary);
		getCalendarSpinner().setAdapter(adapter); 
		getCalendarSpinner().setOnItemSelectedListener(selectedListener);
		
	}
	private void setDefaultDate() {
	    time = new Time();
	    time.setToNow();
	    getTimeView().setText(getTimeText());
	}
	
	public static class DatePickerFragment extends DialogFragment implements
	DatePickerDialog.OnDateSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new DatePickerDialog(getActivity(), this, time.year, time.month, time.monthDay);
		}
		

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			time.set(dayOfMonth, monthOfYear, year);
			timeView.setText(getTimeText());			
			dictionary = new EventsDictionary(view.getContext(), calendarId);
			attendListAdapter(listView);
		}
	}
	

	private void showDatePickerDialog() {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

}
