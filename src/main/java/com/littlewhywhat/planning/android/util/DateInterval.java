package com.littlewhywhat.planning.android.util;

import android.text.format.Time;

public class DateInterval {
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
