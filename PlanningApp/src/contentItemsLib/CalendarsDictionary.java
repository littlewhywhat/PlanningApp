package contentItemsLib;

import android.content.Context;
import CalendarContentHelper.*;

public class CalendarsDictionary extends ContentItemsDictionary {

	public CalendarsDictionary(Context context) {
		Factory = new CalendarsFactory(context);
	}
	public void Fill()
	{
		Fill(CalendarsHelper.SelectionArgs);
	}

	@Override
	public IContentItemsFactory GetFactory() {
		return Factory;
	}

	
}
