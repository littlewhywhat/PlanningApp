package com.littlewhywhat.planning.android.data;

import android.content.ContentResolver;
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
	protected ContentResolver getResolver()
	{
		return appContext.getContentResolver();
	}
	protected abstract Uri getContentUri();
	protected abstract String[] getProjection();
	protected abstract String getSelection();

	public Cursor getCursorByArgs(String[] selectionArgs) {
		return getResolver().query(getContentUri(), getProjection(), getSelection(), selectionArgs , null); 
	}
	public abstract void Update(IContentItem item);
	public abstract void Insert(IContentItem item);
	public abstract void Delete(IContentItem item);
}
