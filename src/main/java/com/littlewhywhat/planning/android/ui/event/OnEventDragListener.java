package com.littlewhywhat.planning.android.ui.event;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.ui.event.EventProcessor;
import android.util.Log;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.content.ClipData;
import android.graphics.Color;

public class OnEventDragListener implements OnDragListener {

	@Override
	public boolean onDrag(View v, DragEvent event) {
        final int action = event.getAction();
        switch(action) {
            case DragEvent.ACTION_DRAG_STARTED:
                final String eventLabel = event.getClipDescription().getLabel().toString();
                if (eventLabel.equals(Event.class.getName())) {        	
                    highlightExit(v);
                    return true;
                } else
                    return false;   
            case DragEvent.ACTION_DRAG_ENTERED:
                highlightEnter(v);
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                highlightExit(v);
                return true;
            case DragEvent.ACTION_DROP:                	
            	if (v instanceof EventProcessor) {            		
            		ClipData data = event.getClipData();
            		Event dataEvent =  new Event(data);
            		((EventProcessor)v).processEvent(dataEvent);
            	}
            	recover(v);
            	return true;      
            case DragEvent.ACTION_DRAG_ENDED:
            	recover(v);
            	return true;
            default:
                return false;
        }
    }

    private void changeBackgroundColor(View v, int color) {
        v.setBackgroundColor(color);
        v.invalidate();
    }

    private void highlightEnter(View v) {
        changeBackgroundColor(v, Color.GREEN);
    }

    private void highlightExit(View v) {
        changeBackgroundColor(v, Color.BLUE);
    }

    private void recover(View v) {
        changeBackgroundColor(v, Color.TRANSPARENT);
    }
}
