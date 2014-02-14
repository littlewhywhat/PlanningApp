package contentItemsLib;

import CalendarContentHelper.CalendarsHelper;
import CalendarContentHelper.ContentHelper;
import android.content.Context;

public class CalendarsFactory extends ContentItemsFactory {
	public CalendarsFactory(Context context)
	{
		super(context);
	}
	public Context getContext() { return appContext; }

	@Override
	public ContentItem getNewContentItem() {
		return new Calendar(this);
	}
	@Override
	protected ContentHelper getHelper(Context context) {
		return new CalendarsHelper(context);
	}

}
