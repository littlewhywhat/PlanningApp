package contentItemsLib;

import android.content.Context;
import CalendarContentHelper.ContentHelper;
import CalendarContentHelper.IContentItemsFactory;
import CalendarContentHelper.IContentItem.IContentItem;

public abstract class ContentItemsFactory implements IContentItemsFactory {
	ContentHelper helper;
	Context appContext;
	public ContentItemsFactory(Context context)	{
		appContext = context;		
	}
	public ContentHelper getHelper() { return helper; }
	public Context getContext() { return appContext; }
	@Override
	public IContentItem getNewIContentItem() {
		return getNewContentItem();
	}
	
	public abstract ContentItem getNewContentItem();
}
