package org.milot.androidbootcamp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	TextView txtTitle;
	TextView txtDate;
	ImageView thumbnailImage;
	
	View base;
	
	public ViewHolder(View base) {
		this.base = base;
	}
	
	
	public TextView getTitleView() {
		if(txtTitle == null) {
			txtTitle = (TextView) base.findViewById(R.id.txtTitle);
		}
		
		return txtTitle;
	}
	
	public ImageView getThumbnail()
	{
		if(thumbnailImage == null) {
			thumbnailImage = (ImageView) base.findViewById(R.id.thumbnailView);
		}
		
		return thumbnailImage;
	}
	
	public TextView getDateView() {
		if(txtDate == null) {
			txtDate = (TextView) base.findViewById(R.id.txtDate);
		}
		
		return txtDate;
	}
	
}
