package contentItemsLib;

import java.util.ArrayList;

import CalendarContentHelper.*;
import CalendarContentHelper.IContentItem.IContentItem;


public abstract class ContentItemsDictionary implements IContentItemsDictionary
{
	protected ContentItemsFactory Factory;
	public ContentItemsFactory getFactory() { return Factory; }
	protected void Fill(String[] selectionArgs)
	{
		getFactory().getHelper().FillIContentItemDic(this, selectionArgs);
	}
	protected ArrayList<ContentItem> ContentItemsDic = new ArrayList<ContentItem>();
	
	@Override
	public void AddIContentItem(IContentItem contentItem) {
		ContentItemsDic.add((ContentItem)contentItem);		
	}
	public ArrayList<ContentItem> getList()
	{
		return ContentItemsDic;
	}
	
	public ContentItem findItemById(String id)
	{
		for(ContentItem item : ContentItemsDic)
			if (item.getID().equals(id))
				return item;
		return null;
	}
}
