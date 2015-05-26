package com.littlewhywhat.planning.android.ui.event;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.ui.event.EventProcessor;
import android.util.Log;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.content.*;
import android.graphics.*;

public class OnEventDragListener implements OnDragListener {

	@Override
	public boolean onDrag(View v, DragEvent event) {
        final int action = event.getAction();
        switch(action) {
            case DragEvent.ACTION_DRAG_STARTED:
            	String eventLabel = event.getClipDescription().getLabel().toString();
                if (eventLabel.equals(Event.class.getName())) {
                	
                    v.setBackgroundColor(Color.BLUE);
                    v.invalidate();
                    return(true);
                    } 
                else {
                    return(false);                    
                    }               
            case DragEvent.ACTION_DRAG_ENTERED: {
                v.setBackgroundColor(Color.GREEN);
                v.invalidate();
                return(true);
            	}
            case DragEvent.ACTION_DRAG_LOCATION:
                return(true);
            case DragEvent.ACTION_DRAG_EXITED: {
                v.setBackgroundColor(Color.BLUE);
                v.invalidate();
                return(true);
            	}
            case DragEvent.ACTION_DROP: {
            	
            	if (v instanceof EventProcessor) {            		
            		ClipData data = event.getClipData();
            		Event dataEvent =  new Event(v.getContext(), data);
            		((EventProcessor)v).processEvent(dataEvent);
            	}
            	v.setBackgroundColor(Color.TRANSPARENT);
            	v.invalidate();
            	return(true);      
            	}
            case DragEvent.ACTION_DRAG_ENDED: {
            	v.setBackgroundColor(Color.TRANSPARENT);
            	v.invalidate();
            	return(true);
                }
            default: {
                Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                return(false);
                }     
        }
	}
}
