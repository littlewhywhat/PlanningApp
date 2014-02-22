package fragments;

import com.littlewhywhat.planningapp.R;


import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.SeekBar;

public class EditEventFragment extends Fragment  {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.edit_event_fragment, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState)	{
		super.onActivityCreated(savedInstanceState);	
		getEditDtStartSeekBar().setOnSeekBarChangeListener(getEditEventViewGroup());
		getEditDtEndSeekBar().setOnSeekBarChangeListener(getEditEventViewGroup());
		getEditEventViewGroup().setViewWithoutEvent();	
	}
	
	private EditEventViewGroup getEditEventViewGroup() {
		return (EditEventViewGroup) getActivity().findViewById(R.id.editEventFragment);
	}
	private SeekBar getEditDtStartSeekBar() { 
		return (SeekBar) getActivity().findViewById(R.id.editDtStartSeekBar);
	}
	private SeekBar getEditDtEndSeekBar() {
		return (SeekBar) getActivity().findViewById(R.id.editDtEndSeekBar);
	}
	
}
