package contentItemsLib;

import CalendarContentHelper.*;
import CalendarContentHelper.IContentItem.IContentItem;

public abstract class ContentItem implements IContentItem {
	private String ID;
	private ContentItemsFactory Factory;
	public ContentItem(ContentItemsFactory factory)
	{
		Factory = factory;
	}
	protected ContentItemsFactory getFactory() { return Factory; }
	protected abstract ContentHelper getHelper();
	@Override
	public String getID() {
		return ID;
	}

	@Override
	public void setID(String id) {
		ID = id;
	}
	
	public void Update()
	{
		getHelper().Update(this);
	}
	
	public void Delete()
	{
		getHelper().Delete(this);
	}

	public void Insert()
	{
		getHelper().Insert(this);
	}
}
