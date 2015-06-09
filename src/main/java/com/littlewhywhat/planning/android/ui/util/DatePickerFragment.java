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
	}

	private static final String YEAR_BUNDLE_KEY = "year";
	private static final String MONTH_BUNDLE_KEY = "month";
	private static final String DAY_OF_MONTH_BUNDLE_KEY = "dayOfMonth";
	private static final String EXCEPTION_MESSAGE = " must implement OnDatePickedListener";
	private boolean mFires = false;
	private OnDatePickedListener mListener;
	
	public static DatePickerFragment newInstance(int year, int monthOfYear, int dayOfMonth) {
		final DatePickerFragment fragment = new DatePickerFragment();
		final Bundle args = new Bundle();
		args.putInt(YEAR_BUNDLE_KEY, year);
		args.putInt(MONTH_BUNDLE_KEY, monthOfYear);
		args.putInt(DAY_OF_MONTH_BUNDLE_KEY, dayOfMonth);	
		fragment.setArguments(args);
		return fragment;
	}

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
		final Bundle args = getArguments();
		final int year = args.getInt(YEAR_BUNDLE_KEY);
		final int month = args.getInt(MONTH_BUNDLE_KEY);
		final int dayOfMonth = args.getInt(DAY_OF_MONTH_BUNDLE_KEY);
		return new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
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
}


