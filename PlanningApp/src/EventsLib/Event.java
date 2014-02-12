package EventsLib;

import java.util.Date;

import CalendarContentHelper.IEvent;

public class Event implements IEvent {
	private int ID = 0;
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void setID(int id) {
		ID = id;
	}

	@Override
	public Date getDTSTART() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDTSTART(Date date) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDURATION(String duration) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDURATION() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
