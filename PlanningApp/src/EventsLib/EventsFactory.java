package EventsLib;

import CalendarContentHelper.IEvent;
import CalendarContentHelper.IEventsFactory;

public class EventsFactory implements IEventsFactory {

	@Override
	public IEvent GetNewEvent() {
		// TODO Auto-generated method stub
		return new Event();
	}

}
