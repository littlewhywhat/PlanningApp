package EventsLib;

import java.util.Hashtable;

import android.util.*;


import CalendarContentHelper.IEvent;
import CalendarContentHelper.IEventsDictionary;
import CalendarContentHelper.IEventsFactory;

public class EventsDictionary implements IEventsDictionary {

	private EventsFactory Factory = new EventsFactory();
	private Hashtable<String,Event> EventsDic = new Hashtable<String,Event>();
	@Override
	public void AddIEvent(IEvent event) {
		// TODO Auto-generated method stub
		EventsDic.put(event.getID(), (Event)event);
	}

	@Override
	public IEventsFactory GetFactory() {
		// TODO Auto-generated method stub
		return Factory;
	}
	
	public int Size() { return EventsDic.size(); }
	public void Clear() { EventsDic.clear(); }
	public Event GetFirst() { return EventsDic.values().iterator().next(); }
}
