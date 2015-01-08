package com.androidmaterialmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hp1 on 28-12-2014.
 */
public class SlideMenuAdapter extends RecyclerView.Adapter<SlideMenuAdapter.ViewHolder> {

	private static final int TYPE_HEADER = 0;
	private static final int TYPE_ITEM = 1;

	private String mNavTitles[];
	private int mIcons[];

	private String name;
	private int profile;
	private String email;
    
	private Context context;

	
	public SlideMenuAdapter(Context context,String Titles[], int Icons[], String Name,String Email, int Profile) {
		
		this.context=context;
		mNavTitles = Titles;
		mIcons = Icons;
		name = Name;
		email = Email;
		profile = Profile;
	}


	public static class ViewHolder extends RecyclerView.ViewHolder {
		int Holderid;

		TextView textView;
		ImageView imageView;
		ImageView profile;
		TextView Name;
		TextView email;

		public ViewHolder(View itemView, int ViewType) {
			super(itemView);

			if (ViewType == TYPE_ITEM) {
				textView = (TextView) itemView.findViewById(R.id.rowText);
				imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
				Holderid = 1;
			} else {
				Name = (TextView) itemView.findViewById(R.id.name);
				email = (TextView) itemView.findViewById(R.id.email);
				profile = (ImageView) itemView.findViewById(R.id.circleView);
				Holderid = 0;
			}
		}
	}


    @Override
    public SlideMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_row,parent,false); 
            ViewHolder vhItem = new ViewHolder(v,viewType); 
            return vhItem; 

        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slidemenu_pfpic_header,parent,false);
            ViewHolder vhHeader = new ViewHolder(v,viewType); 
            return vhHeader; 
        }
        return null;

    }


	@Override
	public void onBindViewHolder(SlideMenuAdapter.ViewHolder holder,final int position) {
		if (holder.Holderid == 1) {
			holder.textView.setText(mNavTitles[position - 1]);
			holder.imageView.setImageResource(mIcons[position - 1]);
			
			holder.textView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (viewclickevent!=null) {
						viewclickevent.OnViewItemClick(position);
					}
				}
			});
			holder.imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (viewclickevent!=null) {
						viewclickevent.OnViewItemClick(position);
					}
				}
			});

		} else {
			holder.profile.setImageResource(profile);
			holder.Name.setText(name);
			holder.email.setText(email);
		}
	}

	@Override
	public int getItemCount() {
		return mNavTitles.length + 1;
	}

	@Override
	public int getItemViewType(int position) {
		if (isPositionHeader(position))
			return TYPE_HEADER;
		
		return TYPE_ITEM;
	}

	private boolean isPositionHeader(int position) {
		return position == 0;
	}

	public viewCLickEvent viewclickevent;

	public viewCLickEvent getViewclickevent() {
		return viewclickevent;
	}

	public void setViewclickevent(viewCLickEvent viewclickevent) {
		this.viewclickevent = viewclickevent;
	}

	public interface viewCLickEvent {
		public void OnViewItemClick(int position);
	}

}
