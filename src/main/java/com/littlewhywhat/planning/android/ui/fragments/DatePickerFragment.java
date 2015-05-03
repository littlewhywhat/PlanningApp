package com.littlewhywhat.planning.android.ui.fragments;
import android.app.*;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.*;
import com.littlewhywhat.planning.android.ui.TimeHelper;

public class DatePickerFragment extends DialogFragment implements
DatePickerDialog.OnDateSetListener {
	private static final String TAG = "DatePickerFragment";
	boolean fires = false;
	DatePickerListener mListener;
	public interface DatePickerListener {
		public void onDateChanged(Time time);
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
		Time time = TimeHelper.GetTime(getArguments().getLong(mListener.getDateKey()));
		return new DatePickerDialog(getActivity(), this, time.year, time.month, time.monthDay);
	}				
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		if (!fires) {
			Log.i(TAG, "date selected" + year + ":" + monthOfYear + ":" + dayOfMonth );			
			fires = true;
			mListener.onDateChanged(TimeHelper.GetTime(year, monthOfYear, dayOfMonth));
		}
		else
			fires = false;
	}
}


