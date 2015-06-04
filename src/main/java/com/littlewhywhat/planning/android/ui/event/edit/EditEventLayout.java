package com.littlewhywhat.planning.android.ui.event.edit;

import com.littlewhywhat.planning.android.R;

import com.littlewhywhat.planning.android.data.event.Event;
import com.littlewhywhat.planning.android.data.event.EventsResolver;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Button;

public class EditEventLayout extends RelativeLayout {
	public EditEventLayout(Context context) {
		super(context);
	}
	public EditEventLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public EditEventLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
	}
}
