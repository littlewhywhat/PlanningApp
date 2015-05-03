package com.littlewhywhat.planning.android.data;

import android.content.Context;

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
