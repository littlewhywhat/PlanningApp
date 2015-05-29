package com.littlewhywhat.planning.android.ui.event;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.content.ClipData;

public class OnEventDragListener implements OnDragListener {

    public interface OnEventDragListenerView {
        public void dragExited();
        public void dragEntered();
        public void recover();
        public void drop(String eventId);
    }

	@Override
	public boolean onDrag(View v, DragEvent event) {
        if (v instanceof OnEventDragListenerView) {
            final int action = event.getAction();
            final OnEventDragListenerView view = (OnEventDragListenerView)v;
            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    view.dragExited();
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    view.dragEntered();
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    view.dragExited();
                    return true;
                case DragEvent.ACTION_DROP:                	
               		ClipData data = event.getClipData();
                    view.drop((String)data.getItemAt(0).getText());
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
}
