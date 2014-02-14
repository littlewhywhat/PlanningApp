package CalendarContentHelper;

import CalendarContentHelper.IContentItem.IContentItem;

public interface IContentItemsDictionary 
{
	public void AddIContentItem(IContentItem contentItem);
	public IContentItemsFactory GetFactory();
}
