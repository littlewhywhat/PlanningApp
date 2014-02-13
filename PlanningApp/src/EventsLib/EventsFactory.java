package EventsLib;

import CalendarContentHelper.IEvent;
import CalendarContentHelper.IEventsFactory;

public class EventsFactory implements IEventsFactory {

	@Override
	public IEvent GetNewEvent() {
		return new Event();
	}

}
