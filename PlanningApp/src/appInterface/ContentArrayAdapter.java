package appInterface;
import java.util.ArrayList;

import contentItemsLib.ContentItem;
import contentItemsLib.ContentItemsDictionary;
import android.widget.BaseAdapter;


public abstract class ContentArrayAdapter extends BaseAdapter {

	private ArrayList<ContentItem> ContentItemsList;
	public ContentArrayAdapter(ContentItemsDictionary dic)
	{
		ContentItemsList = dic.getList();
	}
	@Override
	public int getCount() {
		return ContentItemsList.size();
	}

	@Override
	public Object getItem(int position) {
		return ContentItemsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}



	
	
}
