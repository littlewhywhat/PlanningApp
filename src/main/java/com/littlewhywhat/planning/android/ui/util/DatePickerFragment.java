package com.littlewhywhat.planning.android.ui.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	public interface DatePickerListener {
		public Calendar getCalendar();
		public void refresh();
	}

	private static String EXCEPTION_MESSAGE = " must implement OnArticleSelectedListener";
	private boolean mFires = false;
	private DatePickerListener mListener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (DatePickerListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + EXCEPTION_MESSAGE);
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar calendar = mListener.getCalendar();
		return new DatePickerDialog(getActivity(), this, 
									calendar.get(Calendar.YEAR), 
									calendar.get(Calendar.MONTH),
									calendar.get(Calendar.DAY_OF_MONTH));
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		if (!mFires) {
			mFires = true;
			final Calendar calendar = mListener.getCalendar();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, monthOfYear);
			calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		}
		else
			mFires = false;
	}

	@Override
	public void onStop() {
		super.onStop();
		mListener.refresh();
	}
}


