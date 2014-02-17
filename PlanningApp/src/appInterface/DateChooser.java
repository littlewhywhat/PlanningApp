package appInterface;
import java.util.Calendar;
import java.util.Date;

import com.littlewhywhat.planningapp.R;
import contentItemsLib.CalendarsDictionary;
import CalendarContentHelper.DateInterval;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.app.*;

public class DateChooser extends Activity 
{
	DateInterval interval;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		CalendarsDictionary dictionary = new CalendarsDictionary(getApplicationContext());
		dictionary.Fill();
		CalendarsAdapter adapter = new CalendarsAdapter(dictionary);
		Spinner calendarsSpinner = (Spinner) findViewById(R.id.spinner);
		calendarsSpinner.setAdapter(adapter);
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(listener);
	}
	
	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			Intent intent = new Intent(view.getContext(), EventsActivity.class);
		}
	};
	
	private Spinner getSpinner() 
	{
		return (Spinner) findViewById(R.id.spinner);
	}

	public static class DatePickerFragment extends DialogFragment implements
	DatePickerDialog.OnDateSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

	// Use the current date as the default date in the picker

			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

	// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

		}
	}
	

	private void showDatePickerDialog() {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}
}
