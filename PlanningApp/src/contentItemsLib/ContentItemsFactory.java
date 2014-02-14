package contentItemsLib;

import android.content.Context;
import CalendarContentHelper.ContentHelper;
import CalendarContentHelper.IContentItemsFactory;
import CalendarContentHelper.IContentItem.IContentItem;

public abstract class ContentItemsFactory implements IContentItemsFactory {
	ContentHelper helper;
	Context appContext;
	public ContentItemsFactory(Context context)
	{
		appContext = context;
		helper = getHelper(context);
	}
	public ContentHelper getHelper() { return helper; }
	protected abstract ContentHelper getHelper(Context context);
	public Context getContext() { return appContext; }
	@Override
	public IContentItem getNewIContentItem() {
		return getNewContentItem();
	}
	
	public abstract ContentItem getNewContentItem();
}
