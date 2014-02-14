package contentItemsLibTest;

import java.util.Date;
import java.util.GregorianCalendar;

import contentItemsLib.CalendarsDictionary;
import contentItemsLib.Event;
import contentItemsLib.EventsDictionary;

import android.test.AndroidTestCase;
import android.util.Log;

import CalendarContentHelper.*;
import junit.framework.Assert;

public class ContentItemsLibText extends AndroidTestCase 
{
	private final String TAG = "ContentItemsLibTest";
	private int EventsDicSize = 0;
	private Event Event1;
	private Event Event2;
	private Date DTSTART;
	private Date DTEND;
	private EventsDictionary EventsDic;
	private CalendarsDictionary CalendarsDic;
	
	private Date getDTSTART()
	{
		if (DTSTART == null)
			DTSTART = getCustomDate(1691,1,1,16,0,0);
		return DTSTART;
	}
	private Date getDTEND()
	{
		if (DTEND == null)
			DTEND = getCustomDate(1691,1,2,13,1,1);
		return DTEND;
	}
	private Event getEmptyEvent()
	{
		return (Event)EventsDic.getFactory().getNewContentItem();
	}
	private Event getEvent1()
	{
		if (Event1 == null)
		{
			Event1 = getEmptyEvent();
			Event1.setTitle("Event 1");
			Event1.setDTSTART(getDTSTART());
			Event1.setDTEND(DateInterval.AdvHMS(getDTSTART()));
		}
		return Event1;
	}
	private Event getEvent2()
	{
		if (Event2 == null)
		{
			Event2 = getEmptyEvent();
			Event2.setTitle("Event 2");
			Event2.setDTSTART(DateInterval.TrimHMS(getDTEND()));
			Event2.setDTEND(getDTEND());
		}
		return Event2;
	}

	private void fillEventsDic()
	{
		if (EventsDic.Size() > 0)
			EventsDic.Clear();
		EventsDic.Fill(new DateInterval(getDTSTART(), getDTEND()));
	}
	private void fillCalendarsDic()
	{
		if (CalendarsDic.size() > 0)
			CalendarsDic.clear();
		CalendarsDic.Fill();
	}
	
	private Date getCustomDate(int year, int month, int day, int hours, int minutes, int seconds)
	{
		GregorianCalendar cal = new GregorianCalendar(year,month,day, hours, minutes, seconds);
		return cal.getTime();
	}
	@Override
	public void setUp()
	{		
		CalendarsDic = new CalendarsDictionary(getContext());
		fillCalendarsDic();
		EventsDic = new EventsDictionary(getContext(), CalendarsDic.getFirst().getID());
		fillEventsDic();
		EventsDicSize = EventsDic.Size();
		EventsDic.Clear();		
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
		fillCalendarsDic();
		Assert.assertTrue(0 != CalendarsDic.size());
	}
	
	private void testInsert() 
	{
		Log.i(TAG, "testInsertEvent started");
		getEvent1().Insert();
		Assert.assertTrue(getEvent1().getID() != null);	 
	}

	private void testFillIEventsDic(int size)
	{
		Log.i(TAG, "testFillIEventsDic");
		fillEventsDic();
		Assert.assertTrue(EventsDic.Size() == size);		
	}
	
	private void testDelete()
	{
		Log.i(TAG, "testDeleteEvent");
		getEvent2().Insert();
		getEvent2().Delete();
		Assert.assertEquals(null, getEvent2().getID());		
	}
	
	private final String _NewTitle = "New Title";
	private void testUpdate()
	{
		Log.i(TAG, "testUpdateEvent");
		getEvent1().setTitle(_NewTitle);
		getEvent1().Update();
		fillEventsDic();
		Assert.assertEquals(_NewTitle, EventsDic.GetFirst().getTitle());
	}
	
	@Override
	protected void tearDown()
	{		
		fillEventsDic();
		if (EventsDic.Size() != EventsDicSize)
		{
			if (getEvent1().getID() != null)
				getEvent1().Delete();
			if (getEvent2().getID() != null)
				getEvent2().Delete();
		}
	}
	
		

}
