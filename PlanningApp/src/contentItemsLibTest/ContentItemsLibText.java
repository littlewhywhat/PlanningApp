package contentItemsLibTest;


import contentItemsLib.*;

import android.test.AndroidTestCase;
import android.text.format.Time;
import android.util.Log;

import CalendarContentHelper.*;
import junit.framework.Assert;

public class ContentItemsLibText extends AndroidTestCase 
{
	private final String TAG = "ContentItemsLibTest";
	private int EventsDicSize = 0;
	private Event Event1;
	private Event Event2;
	private Time DTSTART;
	private Time DTEND;
	private EventsDictionary EventsDic;
	private CalendarsDictionary CalendarsDic;
	
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
		return (Event)EventsDic.getFactory().getNewContentItem();
	}
	private void initEvent1()
	{
		Event1 = getEmptyEvent();
		Event1.setTitle("Event 1");
		Event1.setDTSTART(getDTSTART().toMillis(true));
		Event1.setDTEND(DateInterval.AdvHMS(getDTSTART()).toMillis(true));
	}
	private void initEvent2()
	{
		Event2 = getEmptyEvent();
		Event2.setTitle("Event 2");
		Event2.setDTSTART(DateInterval.TrimHMS(getDTEND()).toMillis(true));
		Event2.setDTEND(getDTEND().toMillis(true));
	}

	private void fillEventsDic()
	{
		if (EventsDic.getList().size() > 0)
			EventsDic.getList().clear();
		EventsDic.Fill(new DateInterval(getDTSTART(), getDTEND()));
	}
	private void fillCalendarsDic()
	{
		if (CalendarsDic.getList().size() > 0)
			CalendarsDic.getList().clear();
		CalendarsDic.Fill();
	}
	
	@Override
	public void setUp()
	{		
		CalendarsDic = new CalendarsDictionary(getContext());
		fillCalendarsDic();
		EventsDic = new EventsDictionary(getContext(), CalendarsDic.getList().get(0).getID());
		initEvent1();
		initEvent2();
		fillEventsDic();
		EventsDicSize = EventsDic.getList().size();
		EventsDic.getList().clear();
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
		Assert.assertTrue(0 != CalendarsDic.getList().size());
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
		fillEventsDic();
		Assert.assertTrue(EventsDic.getList().size() == size);		
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
		fillEventsDic();
		Assert.assertEquals(_NewTitle, ((Event)EventsDic.findItemById(Event1.getID())).getTitle());
	}
	
	@Override
	protected void tearDown()
	{		
		fillEventsDic();
		if (EventsDic.getList().size() != EventsDicSize)
		{
			if (Event1.getID() != null)
				Event1.Delete();
			if (Event2.getID() != null)
				Event2.Delete();
		}
	}
	
		

}
