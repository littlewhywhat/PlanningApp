package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.EventsHelper;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.graphics.Color;
import com.littlewhywhat.planning.android.ui.event.EventProcessor;
import com.littlewhywhat.planning.android.ui.event.OnEventDragListener.OnEventDragListenerView;

public class EditEventLayout extends RelativeLayout implements
		EventProcessor, OnEventDragListenerView, SeekBar.OnSeekBarChangeListener {

	private Event mEvent;
	private EventsHelper mEventsHelper;
	public EditEventLayout(Context context) {
		super(context);
		mEventsHelper = new EventsHelper(context);
	}
	public EditEventLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mEventsHelper = new EventsHelper(context);
	}
	
	public EditEventLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		mEventsHelper = new EventsHelper(context);
	}
	@Override
	public void processEvent(Event event) {
		mEvent = event;
		getEditDtStartSeekBar().setEnabled(true);
		getEditDtEndSeekBar().setEnabled(true);
		getUpdateButton().setEnabled(true);
		getDeleteButton().setEnabled(true);
				
		refreshTextViews();
		
		getEditDtStartSeekBar().setProgress(event.getDtStartinMinutes());
		getEditDtEndSeekBar().setProgress(event.getDtEndinMinutes());
		
		getUpdateButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mEventsHelper.Update(mEvent);				
			}			
		});
		getDeleteButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mEventsHelper.Delete(mEvent);	
				setViewWithoutEvent();
			}			
		});
	}
	private void refreshTextViews() {
		getTitleView().setText(mEvent.getTitle());
		getDtStartView().setText(mEvent.getDtStartInString());
		getDtEndView().setText(mEvent.getDtEndInString());		
	}
	private Button getUpdateButton() {
		return (Button) findViewById(R.id.updateButton);
	}
	
	private Button getDeleteButton() {
		return (Button) findViewById(R.id.deleteButton);
	}
	
	public void setViewWithoutEvent() {
		getEditDtStartSeekBar().setProgress(0);
		getEditDtEndSeekBar().setProgress(0);	
		mEvent = null;
		getEditDtStartSeekBar().setEnabled(false);
		getEditDtEndSeekBar().setEnabled(false);
		getUpdateButton().setEnabled(false);
		getDeleteButton().setEnabled(false);
		getTitleView().setText(R.string.drag_here);
		getDtStartView().setText(null);
		getDtEndView().setText(null);
	
	}
	
	private SeekBar getEditDtStartSeekBar() { 
		return (SeekBar) findViewById(R.id.editDtStartSeekBar);
	}
	private SeekBar getEditDtEndSeekBar() {
		return (SeekBar) findViewById(R.id.editDtEndSeekBar);
	}
	
	private TextView getDtEndView() {
		return (TextView) findViewById(R.id.editDtEndView);
	}

	private TextView getDtStartView() {
		return (TextView) findViewById(R.id.editDtStartView);
	}

	private TextView getTitleView() {
		return (TextView) findViewById(R.id.editTitleView);		
	}
	@Override
	public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
		int id = seekbar.getId();
		switch (id) {
			case(R.id.editDtStartSeekBar) :
			Log.i(EditEventLayout.class.getName(), "Start");
			mEvent.setDtStartinMinutes(progress);
			break;
			case(R.id.editDtEndSeekBar) :
			Log.i(EditEventLayout.class.getName(), "End");
			mEvent.setDtEndinMinutes(progress);
			break;
		}
		refreshTextViews();
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekbar) {
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekbar) {
		
	}

	@Override
	public void dragExited() {
		changeBackgroundColor(Color.GREEN);
	}

	public void dragEntered() {
		changeBackgroundColor(Color.BLUE);
	}

    public void recover() {
    	changeBackgroundColor(Color.TRANSPARENT);
    }

    public void drop(String eventId) {
    	getTitleView().setText(eventId);
    }

    private void changeBackgroundColor(int color) {
        this.setBackgroundColor(color);
        this.invalidate();
    }

}
