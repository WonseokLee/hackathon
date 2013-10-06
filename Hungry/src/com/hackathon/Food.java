package com.hackathon;

import android.text.format.Time;

public class Food {
	private int _id;
	private String _name;
	private String _price;
	private String _place;
	private String _phone_number;
	private Time _time1;
	private String _stime;
	private String _etime;
	private String _s1time;
	private String _e1time;
	private String _quantity;
	private String _explanation;
	private String _image;
	private String _size;
	
	public Food(int id, String name, String price, String place, String phone_number,
			String stime, String etime, String s1time, String e1time, String quantity, 
			String explanation, String image, String size){
		this._id = id;
		this._name = name;
		this._price = price;
		this._place = place;
		this._phone_number = phone_number;
		this._stime = stime;
		this._etime = etime;
		this._s1time = s1time;
		this._e1time = e1time;
		this._quantity = quantity;
		this._explanation = explanation;
		this._image = image;
		this._size = size;
	}
	
	public String getName(){
		return this._name;
	}
	
	public String getPrice(){
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
	
	public String getSTime(){
		return this._stime;
	}
	
	public String getETime(){
		return this._etime;
	}
	
	public String getS1Time(){
		return this._s1time;
	}
	
	public String getE1Time(){
		return this._e1time;
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
	
	public String getSize(){
		return this._size;
	}
	
	public void setName(String name){
		this._name = name;
	}
	
	public void setPrice(String price){
		this._price = price;
	}
	
	public void setPlace(String place){
		this._place = place;
	}

}
