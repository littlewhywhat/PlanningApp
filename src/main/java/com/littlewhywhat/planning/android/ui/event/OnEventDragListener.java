package com.littlewhywhat.planning.android.ui.event;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.content.ClipData;
import android.content.ClipDescription;

public class OnEventDragListener implements OnDragListener {
    private static final String[] DRAG_CLIP_DESCRIPTION = new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN };
    private static final int DRAG_CLIP_ID_INDEX = 0;

    public interface OnEventDragListenerView {
        public void dragExited();
        public void dragEntered();
        public void recover();
        public void drop(String eventId);
    }

    public static ClipData getDragClipData(String eventId) {
        return new ClipData(OnEventDragListener.class.getName(), DRAG_CLIP_DESCRIPTION, new ClipData.Item(String.valueOf(eventId)));
    }
    
    private String getEventId(ClipData data) {
        return (String)data.getItemAt(DRAG_CLIP_ID_INDEX).getText();
    }

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
                    view.drop(getEventId(event.getClipData()));
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
