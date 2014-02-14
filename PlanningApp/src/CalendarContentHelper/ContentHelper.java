package CalendarContentHelper;

import CalendarContentHelper.IContentItem.IContentItem;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public abstract class ContentHelper 
{
	private Context appContext;	
	public ContentHelper(Context context)
	{
		appContext = context;
	}
	private Cursor getCursorByArgs(String[] selectionArgs)
	{
		return getAppContext().getContentResolver()
				.query(getContentUri(), getProjection(), getSelection(), selectionArgs , null); 
	}
	private IContentItem fillIContentItemByCursor(Cursor cursor, IContentItem contentItem)
	{
		contentItem.setID(cursor.getString(getProjectionIdIndex()));
		fillIContentItemOtherFields(cursor, contentItem);
		return contentItem;
	}
	
	protected abstract void fillIContentItemOtherFields(Cursor cursor, IContentItem contentItem);
	protected abstract int getProjectionIdIndex();
	protected abstract Uri getContentUri();
	protected abstract String[] getProjection();
	protected abstract String getSelection();
	protected Context getAppContext() { return appContext; }
	
	public void FillIContentItemDic(IContentItemsDictionary dic, String[] selectionArgs)
	{
		Cursor cursor = getCursorByArgs(selectionArgs);
		while (cursor.moveToNext())
			dic.AddIContentItem(fillIContentItemByCursor(cursor, dic.GetFactory().getNewIContentItem()));
	}
	public abstract void Update(IContentItem item);
	public abstract void Insert(IContentItem item);
	public abstract void Delete(IContentItem item);
}
