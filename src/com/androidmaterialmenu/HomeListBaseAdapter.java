package com.androidmaterialmenu;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class HomeListBaseAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private String[] images;
	private Context context;
	
	private ImageLoader imageLoader=ImageLoader.getInstance();
	private DisplayImageOptions options;

	public HomeListBaseAdapter(Context mContext, String[] ImageUrls) {
		this.context = mContext;
		this.images = ImageUrls;
		this.inflater = LayoutInflater.from(mContext);
		
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).showImageForEmptyUri(R.drawable.ic_profile)
				.considerExifParams(true)
				.showImageOnFail(R.drawable.ic_profile)
				.showImageOnLoading(R.drawable.ic_profile).build();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
	}

	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder mHolder;
		if (convertView == null) {
			mHolder=new ViewHolder();
			convertView = inflater.inflate(R.layout.inflate_homeitem_row, null);
			mHolder.profilepic=(CircleImageView)convertView.findViewById(R.id.circleView);
			mHolder.circle_shape=(ImageView)convertView.findViewById(R.id.circle_shape);
			mHolder.ic_moreoptione=(ImageView)convertView.findViewById(R.id.ic_moreoptione);
			mHolder.txt_name=(TextView)convertView.findViewById(R.id.inf_homerow_txt_name);
			mHolder.txt_date=(TextView)convertView.findViewById(R.id.inf_homerow_txt_date);
			convertView.setTag(mHolder);
		}else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		
		
		try {
			GradientDrawable gradientDrawable = (GradientDrawable) mHolder.circle_shape.getBackground();
			gradientDrawable.setStroke(5, context.getResources().getColor(R.color.primary_dark));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String picURL = images[position];
		imageLoader.displayImage(picURL, mHolder.profilepic, options,null);
		
		
		mHolder.ic_moreoptione.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					PopupMenu popup = new PopupMenu(context, v);
					popup.getMenuInflater().inflate(R.menu.optionmenu, popup.getMenu());
					popup.show();
					popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem item) {

							switch (item.getItemId()) {
							case R.id.hidepost:
								
								break;
							case R.id.spampost:
								
								break;

							default:
								break;
							}

							return true;
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		return convertView;
	}

	private class ViewHolder {
		ImageView circle_shape,ic_moreoptione;
		CircleImageView profilepic;
		TextView txt_name;
		TextView txt_date;
	}

}
