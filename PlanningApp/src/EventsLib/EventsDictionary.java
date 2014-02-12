package EventsLib;

import android.util.*;


import CalendarContentHelper.IEvent;
import CalendarContentHelper.IEventsDictionary;
import CalendarContentHelper.IEventsFactory;

public class EventsDictionary implements IEventsDictionary {

	private EventsFactory Factory = new EventsFactory();
	private SparseArray<Event> EventsDic = new SparseArray<Event>();
	@Override
	public void AddIEvent(IEvent event) {
		// TODO Auto-generated method stub
		EventsDic.append(event.getID(), (Event)event);
	}

	@Override
	public IEventsFactory GetFactory() {
		// TODO Auto-generated method stub
		return Factory;
	}
	
	public int Size() { return EventsDic.size(); }
}
