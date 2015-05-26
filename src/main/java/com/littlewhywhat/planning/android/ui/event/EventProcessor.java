package com.littlewhywhat.planning.android.ui.event;

import com.littlewhywhat.planning.android.data.event.Event;

public interface EventProcessor {
	public void processEvent(Event event);
}