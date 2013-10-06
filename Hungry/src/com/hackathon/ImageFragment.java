package com.hackathon;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ImageFragment extends Fragment{
	private ImageView imgView;
	
	public static final ImageFragment newInstance(Food food, int id){
		ImageFragment f = new ImageFragment();
		Bundle args = new Bundle();
		args.putInt("file_id", id);
		f.setArguments(args);
		
		return f;
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_image, container, false);
		imgView= (ImageView) rootView.findViewById(R.id.menu_imgView);
		imgView.setImageResource(R.drawable.default_image);
		int temp = getArguments().getInt("file_id");
		Log.i("gn....", ""+temp);
		imgView.setImageResource(temp);
		//imgView.loadUrl("http://rgy.wo.tc/wp-content/uploads/kboard_attached/1/201310/w.png");
		
		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	

}
