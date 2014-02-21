package appInterface;

import contentItemsLib.Event;
import android.content.ClipDescription;


public class EventClipDescription extends ClipDescription {

	public EventClipDescription(CharSequence label, String[] mimeTypes) {
		super(label, mimeTypes);
	}
	@Override
	public boolean hasMimeType(String mimeType) {
		if (mimeType.equals(Event.class.getName()))
			return true;
		return false;
	}
	

}
