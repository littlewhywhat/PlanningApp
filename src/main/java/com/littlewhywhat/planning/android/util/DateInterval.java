package com.littlewhywhat.planning.android.util;

import android.text.format.Time;

public class DateInterval 
{
	private Time DTSTART;
	private Time DTEND;
	
	public Time getDTSTART() { return DTSTART; }
	public String getDTSTARTString() { return String.valueOf(getDTSTART().toMillis(true)); }
	public String getDTENDString() { return String.valueOf(getDTEND().toMillis(true)); }

	private void setDTSTART(Time time) { DTSTART = time; }
	public Time getDTEND() { return DTEND; }
	private void setDTEND(Time time) { DTEND = time; }
	
	public DateInterval(Time time)
	{		
		setDTSTART(TrimHMS(time));		
		setDTEND(AdvHMS(time));
	}
	public DateInterval(Time dTSTART, Time dTEND)
	{
		setDTSTART(dTSTART);
		setDTEND(dTEND);
	}
	
	public static Time TrimHMS(Time time)
	{
		Time newTime = new Time();
		newTime.set(0, 0, 0, time.monthDay, time.month, time.year);		
		return newTime;
	}
	
	public static Time AdvHMS(Time time)
	{
		Time newTime = new Time();
		newTime.set(0, 0, 0, time.monthDay+1, time.month, time.year);
		return newTime;
	}
	
}