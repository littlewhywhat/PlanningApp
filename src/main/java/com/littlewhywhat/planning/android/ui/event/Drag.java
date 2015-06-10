package com.littlewhywhat.planning.android.ui.event;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.content.ClipData;
import android.content.ClipDescription;

public class Drag {
    private static final String[] DRAG_CLIP_DESCRIPTION = new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN };
    private static final int DRAG_CLIP_ID_INDEX = 0;

    public static boolean checkEvent(DragEvent event) {
        return event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
    }

    public static ClipData getClipData(String eventId) {
        return new ClipData(Drag.class.getName(), DRAG_CLIP_DESCRIPTION, new ClipData.Item(eventId));
    }
    
    public static String getEventId(ClipData data) {
        return (String)data.getItemAt(DRAG_CLIP_ID_INDEX).getText();
    }
}
