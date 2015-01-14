package com.androidmaterialmenu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeListBaseAdapter extends BaseAdapter{

	private String[] images;
	private Context context;
	
	
	public HomeListBaseAdapter(Context mContext,String[] ImageUrls){
		this.context=mContext;
		this.images=ImageUrls;
	}
	
	@Override
	public int getCount() {
		return 0;
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
		return null;
	}

}
