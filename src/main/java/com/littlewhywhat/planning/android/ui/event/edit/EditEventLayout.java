package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.EventsResolver;
import com.littlewhywhat.planning.android.data.event.EventsLoaderById;
import com.littlewhywhat.planning.android.ui.event.OnEventDragListener.OnEventDragListenerView;

import android.app.LoaderManager;
import android.app.Activity;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.graphics.Color;

public class EditEventLayout extends RelativeLayout implements LoaderManager.LoaderCallbacks<Cursor>,
		SeekBar.OnSeekBarChangeListener {

	private static String EVENT_ID_KEY = "EVENT_ID";
	private Activity mContext;
	private Event mEvent;
	private EventsResolver mEventsResolver;
	public EditEventLayout(Context context) {
		super(context);
		mContext = (Activity)context;
		mEventsResolver = new EventsResolver(context.getContentResolver());
	}
	public EditEventLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = (Activity)context;
		mEventsResolver = new EventsResolver(context.getContentResolver());
	}
	
	public EditEventLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		mContext = (Activity)context;
		mEventsResolver = new EventsResolver(context.getContentResolver());
	}

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
				mEventsResolver.Update(mEvent);				
			}			
		});
		getDeleteButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mEventsResolver.Delete(mEvent);	
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

	private Bundle getBundle(String eventId) {
		Bundle bundle = new Bundle();
		bundle.putString(EVENT_ID_KEY, eventId);
		return bundle;
	}
	
	public void restartLoader(String eventId) {
		mContext.getLoaderManager().restartLoader(0, getBundle(eventId), this);
	}

    @Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new EventsLoaderById(mContext, args.getString(EVENT_ID_KEY));
	}
	
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		Log.i(EditEventLayout.class.getName(), "onLoadFinished");
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			mEvent = new Event(cursor);
			processEvent(mEvent);
		} else
			setViewWithoutEvent();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		setViewWithoutEvent();		
	}
}
