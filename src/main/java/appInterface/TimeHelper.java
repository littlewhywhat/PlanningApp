package appInterface;

import android.text.format.Time;

public class TimeHelper {
	public static Time GetTime(long millis)
	{
		Time time = new Time();
		time.set(millis);
		return time;
	}
	public static Time GetTime(int year, int month, int day) {
		Time time = new Time();
		time.set(day, month, year);
		return time;
	}
}
