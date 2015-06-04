package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.EventsLoaderById;
import com.littlewhywhat.planning.android.data.event.EventsResolver;
import com.littlewhywhat.planning.android.ui.event.OnEventDragListener;
import com.littlewhywhat.planning.android.ui.event.OnEventDragListener.OnEventDragListenerView;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ClipDescription;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditEventFragment extends Fragment 
		implements LoaderManager.LoaderCallbacks<Cursor>, SeekBar.OnSeekBarChangeListener {
	private static final String EVENT_ID_KEY = "EVENT_ID";
	private static final int LOADER_ID = 0;

	private Event mEvent;
	private EventsResolver mEventsResolver;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.edit_event_layout, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState)	{
		super.onActivityCreated(savedInstanceState);	
		mEventsResolver = new EventsResolver(getActivity().getContentResolver());
		getEditDtStartSeekBar().setOnSeekBarChangeListener(this);
		getEditDtEndSeekBar().setOnSeekBarChangeListener(this);
		getEditEventLayout().setOnDragListener(new View.OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
	            final int action = event.getAction();
	            switch(action) {
	                case DragEvent.ACTION_DRAG_STARTED:
	                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
	                    	dragExited(v);
	                    	return true;
	                	}
	                	return false;
	                case DragEvent.ACTION_DRAG_EXITED:
	                	dragExited(v);
	                	return true;
	                case DragEvent.ACTION_DRAG_ENTERED:
	                    dragEntered(v);
	                    return true;
	                case DragEvent.ACTION_DRAG_LOCATION:
	                    return true;
	                case DragEvent.ACTION_DROP:                	
	                    restartLoader((String)event.getClipData().getItemAt(0).getText());
	                	recover(v);
	                	return true;      
	                case DragEvent.ACTION_DRAG_ENDED:
	                	recover(v);
	                	return true;
	                default:
	                    return false;
	            }
		        
			}
		});
		setWithoutEvent();	
	}
	
	private Button getUpdateButton() {
		return (Button) getActivity().findViewById(R.id.updateButton);
	}
	
	private Button getDeleteButton() {
		return (Button) getActivity().findViewById(R.id.deleteButton);
	}
	
	private SeekBar getEditDtStartSeekBar() { 
		return (SeekBar) getActivity().findViewById(R.id.editDtStartSeekBar);
	}

	private SeekBar getEditDtEndSeekBar() {
		return (SeekBar) getActivity().findViewById(R.id.editDtEndSeekBar);
	}
	
	private TextView getDtEndView() {
		return (TextView) getActivity().findViewById(R.id.editDtEndView);
	}

	private TextView getDtStartView() {
		return (TextView) getActivity().findViewById(R.id.editDtStartView);
	}

	private TextView getTitleView() {
		return (TextView) getActivity().findViewById(R.id.editTitleView);		
	}

	private void setWithoutEvent() {
		mEvent = null;
		refreshTextViews();
		refreshSeekBars();
		getUpdateButton().setEnabled(false);
		getDeleteButton().setEnabled(false);
	}

	private void setWithEvent(Event event) {
		mEvent = event;
		refreshTextViews();
		refreshSeekBars();
		getUpdateButton().setEnabled(true);
		getDeleteButton().setEnabled(true);
		getUpdateButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mEventsResolver.Update(mEvent);				
			}			
		});
		getDeleteButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
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

	private void dragExited(View view) {
		changeBackgroundColor(view, Color.GREEN);
	}

	private void dragEntered(View view) {
		changeBackgroundColor(view, Color.BLUE);
	}

    private void recover(View view) {
    	changeBackgroundColor(view, Color.TRANSPARENT);
    }

    private void changeBackgroundColor(View view, int color) {
        view.setBackgroundColor(color);
        view.invalidate();
    }

	private EditEventLayout getEditEventLayout() {
		return (EditEventLayout) getActivity().findViewById(R.id.editEventFragment);
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
	
	private Bundle getBundle(String eventId) {
		Bundle bundle = new Bundle();
		bundle.putString(EVENT_ID_KEY, eventId);
		return bundle;
	}
	
	private void restartLoader(String eventId) {
		getLoaderManager().restartLoader(LOADER_ID, getBundle(eventId), this);
	}

    @Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new EventsLoaderById(getActivity(), args.getString(EVENT_ID_KEY));
	}
	
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			setWithEvent(new Event(cursor));
		} else
			setWithoutEvent();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		setWithoutEvent();		
	}
}
