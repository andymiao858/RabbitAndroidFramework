package com.rabbit.framework.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rabbit.framework.R;
import com.rabbit.framework.handler.IEventBusHandler;
import com.rabbit.framework.models.event.IncrementMessageEvent;
import com.rabbit.framework.models.event.MessageEvent;
import com.rabbit.framework.utils.RLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author miaohd
 */
public class EventbusSecondFragment extends BaseEventFragment implements IEventBusHandler{

	@NonNull
	@Bind(R.id.eventbus_frag1_text2)
	TextView textView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_eventbus_frag2, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onDestroyView() {
		ButterKnife.unbind(this);
		super.onDestroyView();
	}

	@Override
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(MessageEvent messageEvent) {
		RLog.d("EventbusSecondFragment received messageEvent data --> " + messageEvent.getData().toString());
		if (messageEvent instanceof IncrementMessageEvent){
			int number = ((IncrementMessageEvent)messageEvent).getData();
			textView.setText(String.valueOf(number));
		}
	}
}
