package com.hackathon;

import android.text.format.Time;

public class Food {
	private int _id;
	private String _name;
	private int _price;
	private String _place;
	private String _phone_number;
	private Time _time1;
	private String _time;
	private String _quantity;
	private String _explanation;
	private String _image;
	
	public Food(int id, String name, int price, String place, String phone_number,
			String time, String quantity, String explanation, String image){
		this._id = id;
		this._name = name;
		this._price = price;
		this._place = place;
		this._phone_number = phone_number;
		this._time = time;
		this._quantity = quantity;
		this._explanation = explanation;
		this._image = image;
	}
	
	public String getName(){
		return this._name;
	}
	
	public int getPrice(){
		return this._price;
	}
	
	public String getPlace(){
		return this._place;
	}
	
	public String getPhoneNumber(){
		return this._phone_number;
	}
	
	public Time getTime1(){
		return this._time1;
	}
	
	public String getTime(){
		return this._time;
	}
	
	public String getQuantity(){
		return this._quantity;
	}
	
	public String getExplanation(){
		return this._explanation;
	}
	
	public String getImage(){
		return this._image;
	}
	
	public void setName(String name){
		this._name = name;
	}
	
	public void setPrice(int price){
		this._price = price;
	}
	
	public void setPlace(String place){
		this._place = place;
	}

}
