package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.ui.event.OnEventDragListener;
import com.littlewhywhat.planning.android.ui.event.OnEventDragListener.OnEventDragListenerView;
import android.app.Fragment;
import android.content.ClipDescription;
import android.graphics.Color;
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
	            final int action = event.getAction();
	            switch(action) {
	                case DragEvent.ACTION_DRAG_STARTED:
	                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
	                    	dragExited(v);
	                    	return true;
	                	}
	                	return false;
	                case DragEvent.ACTION_DRAG_EXITED:
	                	dragExited(v);
	                	return true;
	                case DragEvent.ACTION_DRAG_ENTERED:
	                    dragEntered(v);
	                    return true;
	                case DragEvent.ACTION_DRAG_LOCATION:
	                    return true;
	                case DragEvent.ACTION_DROP:                	
	                    getEditEventLayout().restartLoader((String)event.getClipData().getItemAt(0).getText());
	                	recover(v);
	                	return true;      
	                case DragEvent.ACTION_DRAG_ENDED:
	                	recover(v);
	                	return true;
	                default:
	                    return false;
	            }
		        
			}
		});
		getEditEventLayout().setViewWithoutEvent();	
	}
	
	public void dragExited(View view) {
		changeBackgroundColor(view, Color.GREEN);
	}

	public void dragEntered(View view) {
		changeBackgroundColor(view, Color.BLUE);
	}

    public void recover(View view) {
    	changeBackgroundColor(view, Color.TRANSPARENT);
    }

    private void changeBackgroundColor(View view, int color) {
        view.setBackgroundColor(color);
        view.invalidate();
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
