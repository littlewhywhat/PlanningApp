package CalendarContentHelperTest;

import java.util.Date;
import java.util.GregorianCalendar;

import android.test.AndroidTestCase;

import CalendarContentHelper.*;
import EventsLib.*;
import junit.framework.Assert;

public class CalendarHelperTest extends AndroidTestCase 
{
	private int EventsDicSize = 0;
	private Event Event1;
	private Event Event2;
	private Date DTSTART;
	private Date DTEND;
	private EventsDictionary EventsDic = new EventsDictionary();
	private CalendarHelper helper;
	
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
	private Event getEvent1()
	{
		if (Event1 == null)
		{
			Event1 = new Event();
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
			Event2 = new Event();
			Event2.setTitle("Event 2");
			Event2.setDTSTART(DateInterval.TrimHMS(getDTEND()));
			Event2.setDTEND(getDTEND());
		}
		return Event2;
	}
	private void InsertEvent(Event event)
	{
		helper.Insert(event);
	}
	private void DeleteEvent(Event event)
	{
		helper.Delete(event);
	}
	private void UpdateEvent(Event event)
	{
		helper.Update(event);
	}
	private void FillDic()
	{
		if (EventsDic.Size() > 0)
			EventsDic.Clear();
		helper.FillIEventsDic(EventsDic, new DateInterval(getDTSTART(), getDTEND()));
	}
	
	private Date getCustomDate(int year, int month, int day, int hours, int minutes, int seconds)
	{
		GregorianCalendar cal = new GregorianCalendar(year,month,day, hours, minutes, seconds);
		return cal.getTime();
	}
	@Override
	public void setUp()
	{
		helper = new CalendarHelper(getContext());
		FillDic();
		EventsDicSize = EventsDic.Size();
		EventsDic.Clear();		
	}
	
	@Override
	public void testAndroidTestCaseSetupProperly()
	{
		testFillIEventsDic(EventsDicSize);
		testInsert();
		testDelete();
		testFillIEventsDic(EventsDicSize + 1);
		testUpdate();
	}
	
	private void testInsert() 
	{
		InsertEvent(getEvent1());
		Assert.assertTrue(getEvent1().getID() != null);	 
	}

	private void testFillIEventsDic(int size)
	{
		FillDic();
		Assert.assertTrue(EventsDic.Size() == size);		
	}
	
	private void testDelete()
	{
		InsertEvent(getEvent2());
		DeleteEvent(getEvent2());
		Assert.assertEquals(null, getEvent2().getID());		
	}
	
	private final String _NewTitle = "New Title";
	private void testUpdate()
	{
		getEvent1().setTitle(_NewTitle);
		UpdateEvent(getEvent1());
		FillDic();
		Assert.assertEquals(_NewTitle, EventsDic.GetFirst().getTitle());
	}
	
	@Override
	protected void tearDown()
	{		
		FillDic();
		if (EventsDic.Size() != EventsDicSize)
		{
			if (getEvent1().getID() != null)
				DeleteEvent(getEvent1());
			if (getEvent2().getID() != null)
				DeleteEvent(getEvent2());
		}
	}
	
		

}
