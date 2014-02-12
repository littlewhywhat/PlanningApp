package CalendarContentHelper;
import java.util.*;

public class CalendarHelper 
{
	public static void FillIEventsDic(IEventsDictionary eventsDic, Date date)
	{
		eventsDic.AddIEvent(eventsDic.GetFactory().GetNewEvent());
	}
	
	public static void Update(IEvent event)
	{
		
	}
	
	public static void Delete(IEvent event)
	{
		
	}
	
	public static void Insert(IEvent event)
	{
		event.setID(1);		
	}
}
