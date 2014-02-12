package CalendarContentHelperTest;

import java.util.Date;

import CalendarContentHelper.*;
import EventsLib.*;
import junit.framework.Assert;
import junit.framework.TestCase;

public class CalendarHelperTest extends TestCase 
{
	
	public void testInsert() 
	{
		Event event = new Event();
		CalendarHelper.Insert(event);
		Assert.assertEquals(1, event.getID());		
	}
	public void testFillIEventsDic()
	{
		EventsDictionary dic = new EventsDictionary();
		Date date = new Date();
		CalendarHelper.FillIEventsDic(dic, date);
		Assert.assertTrue(dic.Size() != 0);
	}
	
	
	
		

}
