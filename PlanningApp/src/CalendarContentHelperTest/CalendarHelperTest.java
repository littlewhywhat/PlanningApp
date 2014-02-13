package CalendarContentHelperTest;

import java.util.Date;

import android.test.AndroidTestCase;

import CalendarContentHelper.*;
import EventsLib.*;
import junit.framework.Assert;

public class CalendarHelperTest extends AndroidTestCase 
{
	
	public void testInsert() 
	{
		EventsDictionary dic = new EventsDictionary();
		Date date = new Date();
		CalendarHelper.FillIEventsDic(getContext(), dic, date);
		int count = dic.Size() + 1;
		Event event = new Event();
		event.setDTEND(new Date());
		event.setDTSTART(new Date());
		event.setTitle("new Event");
		
		
		CalendarHelper.Insert(getContext(), event);
		dic.Clear();
		CalendarHelper.FillIEventsDic(getContext(), dic, date);
		Assert.assertEquals(count, dic.Size());
		 
	}
	public void testFillIEventsDic()
	{
		EventsDictionary dic = new EventsDictionary();
		Date date = new Date();
		CalendarHelper.FillIEventsDic(getContext(),dic, date);
		Assert.assertTrue(dic.Size() != 0);
		
	}
	
	public void testDelete()
	{
		EventsDictionary dic = new EventsDictionary();
		Date date = new Date();
		CalendarHelper.FillIEventsDic(getContext(), dic, date);
		int count = dic.Size() + 1;
		Event event = new Event();
		event.setDTEND(new Date());
		event.setDTSTART(new Date());
		event.setTitle("new Event");
		
		
		CalendarHelper.Insert(getContext(), event);
		dic.Clear();
		CalendarHelper.FillIEventsDic(getContext(), dic, date);
		Assert.assertEquals(count, dic.Size());

		Assert.assertEquals(1, CalendarHelper.Delete(getContext(), event));
	}
	
	public void testUpdate()
	{
		EventsDictionary dic = new EventsDictionary();
		Date date = new Date();
		CalendarHelper.FillIEventsDic(getContext(), dic, date);
		Event event = dic.GetFirst();
		event.setTitle("UpdatedEvent");
		CalendarHelper.Update(getContext(), event);
	}
	
	
	
		

}
