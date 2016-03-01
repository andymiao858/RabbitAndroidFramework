package com.rabbit.framework.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rabbit.framework.R;
import com.rabbit.framework.views.widgets.decoration.DividerDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Glide Image show Demo
 *
 * @author miaohd
 */
public class GlideDemoActivity extends BaseActivity {

	private static List<String> IMAGE_URL = Arrays.asList(new String[]{
			"https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png",
			"http://vignette4.wikia.nocookie.net/robber-penguin-agency/images/6/6e/Small-mario.png/revision/latest?cb=20150107080404",
			"http://vignette3.wikia.nocookie.net/logopedia/images/8/89/Logo_rolling_stones.png/revision/latest?cb=20110725041802",
			"http://vignette1.wikia.nocookie.net/freeciv/images/e/e0/Windows_logo.png/revision/latest?cb=20071014060856",
			"http://vignette4.wikia.nocookie.net/fantendo/images/5/52/Mushroom2.PNG/revision/latest?cb=20111123224555",
			"http://gulin.sg/andrea/maple/CoverFlow/png/img1.png",
			"http://p1.mb5u.com/sucai/200811222012151777801.png",
			"http://www.iconpng.com/png/realvista/android_platform.png"
	});

	@Bind(R.id.glide_list_view)
	RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glide);
		ButterKnife.bind(this);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(new GlideAdapter(this));
		recyclerView.addItemDecoration(new DividerDecoration(this));
	}

	private static class GlideAdapter extends RecyclerView.Adapter<GlideImageVH>{

		private GlideDemoActivity activity;

		public GlideAdapter(GlideDemoActivity activity) {
			this.activity = activity;
		}

		@Override
		public GlideImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
			View convertView = LayoutInflater.from(activity).inflate(R.layout.glide_item, parent, false);
			return new GlideImageVH(convertView);
		}

		@Override
		public void onBindViewHolder(GlideImageVH holder, int position) {
			Glide.with(activity).load(IMAGE_URL.get(position)).centerCrop().into(holder.imageView);
		}

		@Override
		public int getItemCount() {
			return IMAGE_URL.size();
		}
	}

	private static class GlideImageVH extends RecyclerView.ViewHolder{

		ImageView imageView;

		public GlideImageVH(View itemView) {
			super(itemView);
			imageView = (ImageView)itemView.findViewById(R.id.glide_image_item);
		}
	}

}
