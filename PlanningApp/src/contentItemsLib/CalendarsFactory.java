package contentItemsLib;

import CalendarContentHelper.CalendarsHelper;
import android.content.Context;

public class CalendarsFactory extends ContentItemsFactory {
	public CalendarsFactory(Context context)
	{
		super(context);
		helper = new CalendarsHelper(context);
	}
	public Context getContext() { return appContext; }
	@Override
	public ContentItem getNewContentItem() {
		return new Calendar(this);
	}

}
