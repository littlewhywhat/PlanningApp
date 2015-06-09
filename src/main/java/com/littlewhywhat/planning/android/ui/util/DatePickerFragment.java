package com.littlewhywhat.planning.android.ui.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	public interface OnDatePickedListener {
		public void onDatePicked(int year, int monthOfYear, int dayOfMonth);
		public void onDatePickerStop();
		public int getYear();
		public int getMonth();
		public int getDayOfMonth();
	}

	private static final String EXCEPTION_MESSAGE = " must implement OnDatePickedListener";
	private boolean mFires = false;
	private OnDatePickedListener mListener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnDatePickedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + EXCEPTION_MESSAGE);
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new DatePickerDialog(getActivity(), this, mListener.getYear(), mListener.getMonth(), 
			mListener.getDayOfMonth());
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		if (!mFires) {
			mFires = true;
			mListener.onDatePicked(year, monthOfYear, dayOfMonth);
		}
		else
			mFires = false;
	}

	@Override
	public void onStop() {
		super.onStop();
		mListener.onDatePickerStop();
	}
}


