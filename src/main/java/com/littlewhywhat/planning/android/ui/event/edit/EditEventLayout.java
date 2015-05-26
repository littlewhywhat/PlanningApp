package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.littlewhywhat.planning.android.ui.event.OnEventDragListener.EventProcessor;

public class EditEventLayout extends RelativeLayout implements
		EventProcessor, SeekBar.OnSeekBarChangeListener {

	private Event mEvent;
	public EditEventLayout(Context context) {
		super(context);
	}
	public EditEventLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public EditEventLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
	}
	@Override
	public void processEvent(Event event) {
		mEvent = event;
		getEditDtStartSeekBar().setEnabled(true);
		getEditDtEndSeekBar().setEnabled(true);
		getUpdateButton().setEnabled(true);
		getDeleteButton().setEnabled(true);
				
		refreshTextViews();
		
		getEditDtStartSeekBar().setProgress(event.getDTSTARTinMinutes());
		getEditDtEndSeekBar().setProgress(event.getDTENDinMinutes());
		
		getUpdateButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mEvent.Update();				
			}			
		});
		getDeleteButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mEvent.Delete();	
				setViewWithoutEvent();
			}			
		});
	}
	private void refreshTextViews() {
		getTitleView().setText(mEvent.getTitle());
		getDtStartView().setText(mEvent.getDTSTARTinString());
		getDtEndView().setText(mEvent.getDTENDinString());		
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
			mEvent.setDTSTARTinMinutes(progress);
			break;
			case(R.id.editDtEndSeekBar) :
			Log.i(EditEventLayout.class.getName(), "End");
			mEvent.setDTENDinMinutes(progress);
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

}
