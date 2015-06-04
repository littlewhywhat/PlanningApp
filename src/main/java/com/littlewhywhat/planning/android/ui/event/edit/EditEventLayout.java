package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.EventsResolver;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;

public class EditEventLayout extends RelativeLayout implements
		SeekBar.OnSeekBarChangeListener {
	private Event mEvent;
	private EventsResolver mEventsResolver;
	public EditEventLayout(Context context) {
		super(context);
		mEventsResolver = new EventsResolver(context.getContentResolver());
	}
	public EditEventLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mEventsResolver = new EventsResolver(context.getContentResolver());
	}
	
	public EditEventLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		mEventsResolver = new EventsResolver(context.getContentResolver());
	}

	public void setViewWithoutEvent() {	
		mEvent = null;
		refreshTextViews();
		refreshSeekBars();
		getUpdateButton().setEnabled(false);
		getDeleteButton().setEnabled(false);	
	}

	public void setViewWithEvent(Event event) {
		mEvent = event;
		refreshTextViews();
		refreshSeekBars();
		getUpdateButton().setEnabled(true);
		getDeleteButton().setEnabled(true);
		getUpdateButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mEventsResolver.Update(mEvent);				
			}			
		});
		getDeleteButton().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mEventsResolver.Delete(mEvent);
			}			
		});
	}
	private void refreshTextViews() {
		if (mEvent != null) {
			getTitleView().setText(mEvent.getTitle());
			getDtStartView().setText(mEvent.getDtStartInString());
			getDtEndView().setText(mEvent.getDtEndInString());	
		} else {
			getTitleView().setText(R.string.drag_here);
			getDtStartView().setText(null);
			getDtEndView().setText(null);
		}
	}

	private void refreshSeekBars() {
		if (mEvent != null) {
			getEditDtStartSeekBar().setEnabled(true);
			getEditDtEndSeekBar().setEnabled(true);
			getEditDtStartSeekBar().setProgress(mEvent.getDtStartinMinutes());
			getEditDtEndSeekBar().setProgress(mEvent.getDtEndinMinutes());
		} else {
			getEditDtStartSeekBar().setProgress(0);
			getEditDtEndSeekBar().setProgress(0);
			getEditDtStartSeekBar().setEnabled(false);
			getEditDtEndSeekBar().setEnabled(false);
		}
	}

	private Button getUpdateButton() {
		return (Button) findViewById(R.id.updateButton);
	}
	
	private Button getDeleteButton() {
		return (Button) findViewById(R.id.deleteButton);
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
		if (mEvent != null) {
			final int id = seekbar.getId();
			switch (id) {
				case(R.id.editDtStartSeekBar):
					mEvent.setDtStartinMinutes(progress);
					break;
				case(R.id.editDtEndSeekBar):
					mEvent.setDtEndinMinutes(progress);
					break;
			}
		}
		refreshTextViews();
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekbar) { }
	@Override
	public void onStopTrackingTouch(SeekBar seekbar) { }
}
