package appInterface;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.TextView;
import android.content.*;
import android.graphics.*;

public class OnEventDragListener implements OnDragListener {
	
	@Override
	public boolean onDrag(View v, DragEvent event) {
        final int action = event.getAction();
        switch(action) {
            case DragEvent.ACTION_DRAG_STARTED:
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                	
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
            	ClipData.Item item = event.getClipData().getItemAt(0);
            	          	
            	((TextView)v).setText(item.getText());
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
