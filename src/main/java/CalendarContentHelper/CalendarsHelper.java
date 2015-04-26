package CalendarContentHelper;

import CalendarContentHelper.IContentItem.IContentItem;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

public class CalendarsHelper extends ContentHelper
{
	public static final String selection = "((" + CalendarContract.Calendars.VISIBLE + " = ?) AND (" 
            + CalendarContract.Calendars.SYNC_EVENTS + " = ?)) ";
	public static final String[] SelectionArgs = new String[] { "1" , "1" };
	public static final String[] CALENDAR_PROJECTION = new String[] {
		CalendarContract.Calendars._ID,                      // 0
		CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,    // 1
	};
	public static final int PROJECTION_ID_INDEX = 0;
	public static final int PROJECTION_NAME_INDEX = 1;
	
	
	public CalendarsHelper(Context context)
	{
		super(context);
	}
	public Cursor getCursor()	{
		return getCursorByArgs(SelectionArgs);
	}	
	@Override
	protected Uri getContentUri() 
	{
		return CalendarContract.Calendars.CONTENT_URI;
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
	@Override
	protected String[] getProjection() {
		return CALENDAR_PROJECTION;
	}
	@Override
	protected String getSelection() {
		return selection;
	}	
}
