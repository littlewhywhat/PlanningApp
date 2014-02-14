package contentItemsLib;
import CalendarContentHelper.*;
import CalendarContentHelper.IContentItem.*;


public class Calendar extends ContentItem implements ICalendar
{
	public Calendar(CalendarsFactory factory) {
		super(factory);
	}

	private String ID;
	private String Name;
	@Override
	public String getID() {	return ID; }

	@Override
	public void setID(String id) { ID = id; }

	@Override
	public String getName() { return Name; }

	@Override
	public void setName(String name) { Name = name; }

	@Override
	protected ContentHelper getHelper() {
		return new CalendarsHelper(getFactory().getContext());
	}
	
}
