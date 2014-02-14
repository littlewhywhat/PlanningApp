package contentItemsLib;
import CalendarContentHelper.*;
import CalendarContentHelper.IContentItem.*;


public class Calendar extends ContentItem implements ICalendar
{
	public Calendar(CalendarsFactory factory) {
		super(factory);
	}

	private String Name;

	@Override
	public String getName() { return Name; }

	@Override
	public void setName(String name) { Name = name; }

	@Override
	protected ContentHelper getHelper() {
		return new CalendarsHelper(getFactory().getContext());
	}
	
}
