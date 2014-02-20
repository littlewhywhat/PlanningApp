package appInterface;


import com.littlewhywhat.planningapp.R;

import fragments.DatePickerFragment;
import fragments.EventsFragment;
import fragments.CalendarsFragment.CalendarChooseListener;
import fragments.DatePickerFragment.DatePickerListener;
import android.app.*;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity implements CalendarChooseListener, DatePickerListener
{
	private final String TAG = "Main";
	private final String TIME_FORMAT = "%Y.%m.%d";
	private final String DATE_KEY = "Date";
	private Time time;
	private String calendarId;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Created");
		this.setContentView(R.layout.main);
		setDefaultDate();
		setChooseDateButton();
	}
	@Override
	public void onCalendarChoose(String CalendarId) {		
		calendarId = CalendarId;
		refreshEvents();
	}
	@Override
	public void onDateChanged(Time Time) {
		time = Time;
		setTimeViewText();
		refreshEvents();		
	}
	@Override
	public String getDateKey() {
		return DATE_KEY;
	}
	private void setDefaultDate() {
	    time = new Time();
	    time.setToNow();
	    setTimeViewText();
	}
	private String getTimeText()
	{
		return time.format(TIME_FORMAT);
	}
	
	private TextView getTimeView()	{
		return (TextView)findViewById(R.id.timeView);
	}
	
	private void setTimeViewText(){
		getTimeView().setText(getTimeText());
	}
	private void showDatePickerDialog() {
		DialogFragment newFragment = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putLong(DATE_KEY, time.toMillis(true));
		newFragment.setArguments(args);
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
	
	private void refreshEvents() {
		EventsFragment fragment = (EventsFragment)getFragmentManager().findFragmentById(R.id.eventsFragment);
		fragment.restartLoader(time, calendarId);
	}
}
