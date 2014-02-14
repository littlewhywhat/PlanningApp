package CalendarContentHelper;
import java.util.*;

public class DateInterval 
{
	private Date DTSTART;
	private Date DTEND;
	
	public Date getDTSTART() { return DTSTART; }
	public void setDTSTART(Date date) { DTSTART = date; }
	public Date getDTEND() { return DTEND; }
	public void setDTEND(Date date) { DTEND = date; }
	public DateInterval(Date date)
	{		
		setDTSTART(TrimHMS(date));		
		setDTEND(AdvHMS(date));
	}
	public DateInterval(Date dTSTART, Date dTEND)
	{
		setDTSTART(dTSTART);
		setDTEND(dTEND);
	}
	
	public static Date TrimHMS(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date AdvHMS(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	public String[] ConvertToStringArgs()
	{
		return new String[] { String.valueOf(getDTSTART().getTime()), 
	  		      String.valueOf(getDTEND().getTime()) };
	}
}
