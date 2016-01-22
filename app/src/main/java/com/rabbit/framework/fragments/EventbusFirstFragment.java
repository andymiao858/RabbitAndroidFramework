package com.rabbit.framework.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rabbit.framework.R;
import com.rabbit.framework.models.event.IncrementMessageEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * @author miaohd
 */
public class EventbusFirstFragment extends BaseFragment{

	@NonNull
	@Bind(R.id.eventbus_frag1_text1)
	TextView text;

	@NonNull
	@Bind(R.id.eventbus_frag1_btn)
	Button btn;

	int number = 0;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().post(new IncrementMessageEvent(number));
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_eventbus_frag1, container, false);
		ButterKnife.bind(this, view);
		text.setText(String.valueOf(number));
		return view;
	}

	@Override
	public void onDestroyView() {
		ButterKnife.unbind(this);
		super.onDestroyView();
	}

	@OnClick(R.id.eventbus_frag1_btn)
	void increment(){
		number++;
		text.setText(String.valueOf(number));
		EventBus.getDefault().post(new IncrementMessageEvent(number));
	}

}
