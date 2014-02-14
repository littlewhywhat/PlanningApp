package CalendarContentHelper;

import CalendarContentHelper.IContentItem.ICalendar;
import CalendarContentHelper.IContentItem.IContentItem;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

public class CalendarsHelper extends ContentHelper
{
	private final String selection = "((" + CalendarContract.Calendars.VISIBLE + " = ?) AND (" 
            + CalendarContract.Calendars.SYNC_EVENTS + " = ?)) ";
	public static final String[] SelectionArgs = new String[] { "1" , "1" };
	private static final String[] CALENDAR_PROJECTION = new String[] {
		CalendarContract.Calendars._ID,                      // 0
		CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,    // 1
	};
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_NAME_INDEX = 1;
	public CalendarsHelper(Context context)
	{
		super(context);
	}
	@Override
	protected void fillIContentItemOtherFields(Cursor cursor,
			IContentItem contentItem) 
	{
		ICalendar calendar = (ICalendar)contentItem;
		calendar.setName(cursor.getString(PROJECTION_NAME_INDEX));
		
	}
	@Override
	protected int getProjectionIdIndex() 
	{
		return PROJECTION_ID_INDEX;
	}
	@Override
	protected Uri getContentUri() 
	{
		return CalendarContract.Calendars.CONTENT_URI;
	}
	@Override
	protected String[] getProjection() 
	{
		return CALENDAR_PROJECTION;
	}
	@Override
	protected String getSelection() 
	{
		return selection;
	}
	public void FillIContentItemDic(IContentItemsDictionary dic)
	{
		FillIContentItemDic(dic, SelectionArgs);
	}
	@Override
	public void Update(IContentItem item) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Insert(IContentItem item) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Delete(IContentItem item) {
		// TODO Auto-generated method stub
		
	}
	
}
