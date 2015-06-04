package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.ui.event.OnEventDragListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.SeekBar;

public class EditEventFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.edit_event_layout, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState)	{
		super.onActivityCreated(savedInstanceState);	
		getEditDtStartSeekBar().setOnSeekBarChangeListener(getEditEventLayout());
		getEditDtEndSeekBar().setOnSeekBarChangeListener(getEditEventLayout());
		getEditEventLayout().setOnDragListener(new OnEventDragListener());
		getEditEventLayout().setViewWithoutEvent();	
	}
	
	private EditEventLayout getEditEventLayout() {
		return (EditEventLayout) getActivity().findViewById(R.id.editEventFragment);
	}
	private SeekBar getEditDtStartSeekBar() { 
		return (SeekBar) getActivity().findViewById(R.id.editDtStartSeekBar);
	}
	private SeekBar getEditDtEndSeekBar() {
		return (SeekBar) getActivity().findViewById(R.id.editDtEndSeekBar);
	}
	
}
