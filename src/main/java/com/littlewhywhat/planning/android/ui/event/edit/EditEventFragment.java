package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.ui.event.OnEventDragListener;
import com.littlewhywhat.planning.android.ui.event.OnEventDragListener.OnEventDragListenerView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
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
		getEditEventLayout().setOnDragListener(new View.OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
		        if (v instanceof OnEventDragListenerView) {
		            final int action = event.getAction();
		            final OnEventDragListenerView view = (OnEventDragListenerView)v;
		            switch(action) {
		                case DragEvent.ACTION_DRAG_STARTED: case DragEvent.ACTION_DRAG_EXITED:
		                    view.dragExited();
		                    return true;
		                case DragEvent.ACTION_DRAG_ENTERED:
		                    view.dragEntered();
		                    return true;
		                case DragEvent.ACTION_DRAG_LOCATION:
		                    return true;
		                case DragEvent.ACTION_DROP:                	
		                    view.drop((String)event.getClipData().getItemAt(0).getText());
		                	view.recover();
		                	return true;      
		                case DragEvent.ACTION_DRAG_ENDED:
		                	view.recover();
		                	return true;
		                default:
		                    return false;
		            }
		        } else
		            return false;
			}
		});
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
