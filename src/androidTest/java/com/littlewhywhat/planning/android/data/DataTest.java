package com.littlewhywhat.planning.android.data;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.text.format.Time;
import android.util.Log;
import android.provider.CalendarContract;

import junit.framework.Assert;

public class DataTest extends AndroidTestCase 
{
	private final String TAG = "ContentItemsLibTest";
	private int EventsDicSize = 0;
	private Event Event1;
	private Event Event2;
	private Time DTSTART;
	private Time DTEND;
	private Cursor EventsCursor;
	private Cursor CalendarsCursor;
	private EventsHelper eventsHelper;
	private String CalendarId;
	
	private Time getDTSTART()
	{
		
		if (DTSTART == null) {
			DTSTART = new Time();
			DTSTART.set(0, 0, 16, 1, 1, 1971);
		}
		return DTSTART;
	}
	private Time getDTEND()
	{
		if (DTEND == null) {
			DTEND = new Time();
			DTEND.set(1, 1, 13, 2, 1, 1971);
		}
		return DTEND;
	}
	private Event getEmptyEvent()
	{
		return new Event(getContext());
	}
	private void initEvent1()
	{
		Event1 = getEmptyEvent();
		Event1.setTitle("Event 1");
		Event1.setDTSTART(getDTSTART().toMillis(true));
		Event1.setDTEND(DateInterval.AdvHMS(getDTSTART()).toMillis(true));
		Event1.setCalendarId(CalendarId);
	}
	private void initEvent2()
	{
		Event2 = getEmptyEvent();
		Event2.setTitle("Event 2");
		Event2.setDTSTART(DateInterval.TrimHMS(getDTEND()).toMillis(true));
		Event2.setDTEND(getDTEND().toMillis(true));
		Event2.setCalendarId(CalendarId);
	}

	private void fillEventsCursor()
	{
		DateInterval interval = new DateInterval(getDTSTART(), getDTEND());
		EventsCursor = eventsHelper.getCursor(interval.getDTSTARTString(), 
				interval.getDTENDString(), CalendarId);
	}
	private void fillCalendarsCursor() {
		CalendarsCursor = getContext().getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, CalendarsHelper.CALENDAR_PROJECTION, CalendarsHelper.selection, CalendarsHelper.SelectionArgs,null);
	}
	
	@Override
	public void setUp()
	{		
		eventsHelper = new EventsHelper(getContext());	
		fillCalendarsCursor();
		CalendarsCursor.moveToFirst();
		CalendarId = CalendarsCursor.getString(CalendarsHelper.PROJECTION_ID_INDEX);
		initEvent1();
		initEvent2();
		fillEventsCursor();
		EventsDicSize = EventsCursor.getCount();
	}
	
	@Override
	public void testAndroidTestCaseSetupProperly()
	{
		testFillICalendarsDic();
		testFillIEventsDic(EventsDicSize);
		testInsert();
		testDelete();
		testFillIEventsDic(EventsDicSize + 1);
		testUpdate();
	}
	
	private void testFillICalendarsDic()
	{
		Log.i(TAG, "FillingICalendarsDic");
		fillCalendarsCursor();
		Assert.assertTrue(0 != CalendarsCursor.getCount());
	}
	
	private void testInsert() 
	{
		Log.i(TAG, "testInsertEvent started");
		Event1.Insert();
		Assert.assertTrue(Event1.getID() != null);	 
	}

	private void testFillIEventsDic(int size)
	{
		Log.i(TAG, "testFillIEventsDic");
		fillEventsCursor();
		Assert.assertTrue(EventsCursor.getCount() == size);		
	}
	
	private void testDelete()
	{
		Log.i(TAG, "testDeleteEvent");
		Event2.Insert();
		Event2.Delete();
		Assert.assertEquals(null, Event2.getID());		
	}
	
	private final String _NewTitle = "New Title";
	private void testUpdate()
	{
		Log.i(TAG, "testUpdateEvent");
		Event1.setTitle(_NewTitle);
		Event1.Update();
		Assert.assertEquals(_NewTitle, findTitleOfItemById(Event1.getID()));
	}
	
	private String findTitleOfItemById(String Id) {
		fillEventsCursor();
		while (EventsCursor.moveToNext())
			if (EventsCursor.getString(EventsHelper.PROJECTION_ID_INDEX).equals(Id))
				return EventsCursor.getString(EventsHelper.PROJECTION_TITLE_INDEX);
		return null;
	}
	
	
	@Override
	protected void tearDown()
	{		
		fillEventsCursor();
		if (EventsCursor.getCount() != EventsDicSize)
		{
			if (Event1.getID() != null)
				Event1.Delete();
			if (Event2.getID() != null)
				Event2.Delete();
		}
	}
	
		

}
