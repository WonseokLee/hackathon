package com.hackathon;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ImageFragment extends Fragment{
	private ImageView imgView;
	
	public static final ImageFragment newInstance(){
		ImageFragment f = new ImageFragment();
		
		return f;
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_image, container, false);
		imgView = (ImageView) rootView.findViewById(R.id.menu_Image);
		imgView.setImageDrawable(rootView.getResources().getDrawable(R.drawable.building2));
		
		return rootView;
	}
	
	

}
