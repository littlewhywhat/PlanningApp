package com.littlewhywhat.planning.android.ui.util;

import android.app.*;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements
DatePickerDialog.OnDateSetListener {
	private static final String TAG = "DatePickerFragment";
	boolean fires = false;
	DatePickerListener mListener;
	public interface DatePickerListener {
		public void onDateChanged(long millis);
		public String getDateKey();
	}
	@Override
	public void onAttach(Activity activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);
		
		try {
			mListener = (DatePickerListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnArticleSelectedListener");
		}
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(getArguments().getLong(mListener.getDateKey()));
		return new DatePickerDialog(getActivity(), this, 
									calendar.get(Calendar.YEAR), 
									calendar.get(Calendar.MONTH),
									calendar.get(Calendar.DAY_OF_MONTH));
	}				
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		if (!fires) {
			Log.i(TAG, "date selected" + year + ":" + monthOfYear + ":" + dayOfMonth );			
			fires = true;
			GregorianCalendar calendar = new GregorianCalendar(year,monthOfYear,dayOfMonth);
			mListener.onDateChanged(calendar.getTimeInMillis());
		}
		else
			fires = false;
	}
}


