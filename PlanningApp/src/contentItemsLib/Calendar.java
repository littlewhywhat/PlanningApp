package contentItemsLib;
import android.content.Context;
import CalendarContentHelper.*;
import CalendarContentHelper.IContentItem.*;


public class Calendar extends ContentItem implements ICalendar
{
	public Calendar(Context context) {
		super(context);
	}
	
	@Override
	protected ContentHelper getHelper() {
		return new CalendarsHelper(getContext());
	}
	
}
