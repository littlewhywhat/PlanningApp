package EventsLib;

import java.util.Date;

import CalendarContentHelper.IEvent;

public class Event implements IEvent {
	private String ID = null;
	private Date DTSTART = null;
	private Date DTEND = null;
	private String Title = null;
	@Override
	public String getID() {
		return ID;
	}

	@Override
	public void setID(String id) {
		ID = id;
	}

	@Override
	public Date getDTSTART() {
		// TODO Auto-generated method stub
		return DTSTART;
	}

	@Override
	public void setDTSTART(Date date) {
		// TODO Auto-generated method stub
		DTSTART = date;
	}

	@Override
	public void setDTEND(Date date) {
		// TODO Auto-generated method stub
		DTEND = date;
	}

	@Override
	public Date getDTEND() {
		// TODO Auto-generated method stub
		return DTEND;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		Title = title;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return Title;
	}

}