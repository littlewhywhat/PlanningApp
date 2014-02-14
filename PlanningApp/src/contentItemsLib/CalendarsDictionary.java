package contentItemsLib;

import java.util.Hashtable;

import android.content.Context;

import CalendarContentHelper.*;
import CalendarContentHelper.IContentItem.IContentItem;

public class CalendarsDictionary extends ContentItemsDictionary {

	public CalendarsDictionary(Context context) {
		Factory = new CalendarsFactory(context);
	}
	public void Fill()
	{
		Fill(null);
	}
	
	private Hashtable<String,Calendar> CalendarsDic = new Hashtable<String,Calendar>();
	@Override
	public void AddIContentItem(IContentItem contentItem) {
		CalendarsDic.put(contentItem.getID(), (Calendar)contentItem);		
	}

	@Override
	public IContentItemsFactory GetFactory() {
		return Factory;
	}

	public int size() {
		return CalendarsDic.size();
	}

	public void clear() {
		CalendarsDic.clear();
		
	}

	public Calendar getFirst() {
		return CalendarsDic.values().iterator().next();
	}
	
}
