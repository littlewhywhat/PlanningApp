package contentItemsLib;

import CalendarContentHelper.*;

public abstract class ContentItemsDictionary implements IContentItemsDictionary
{
	protected ContentItemsFactory Factory;
	public ContentItemsFactory getFactory() { return Factory; }
	protected void Fill(String[] selectionArgs)
	{
		getFactory().getHelper().FillIContentItemDic(this, selectionArgs);
	}
}
