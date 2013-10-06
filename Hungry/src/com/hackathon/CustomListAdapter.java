package com.hackathon;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<Food> foods = null;

	public CustomListAdapter(Context context, ArrayList<Food> foods){
		super();
		this.context = context;
		this.foods = foods;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(foods == null)
			return 0;
		else
			return foods.size();
	}

	@Override
	public Food getItem(int position) {
		// TODO Auto-generated method stub
		return foods.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_row, parent, false);
		
		TextView listTitle = (TextView) rowView.findViewById(R.id.list_title);
		TextView listArtist = (TextView) rowView.findViewById(R.id.list_artist);
		
		listTitle.setText(foods.get(position).getName());
		listArtist.setText(foods.get(position).getPhoneNumber());
		
		return rowView;
	}

}
