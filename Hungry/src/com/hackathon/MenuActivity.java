package com.hackathon;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuActivity extends FragmentActivity implements OnClickListener{
	private static final String KEY_ITEM = "item";
	private static final String KEY_NAME = "name";
	private static final String KEY_PRICE = "price";
	private static final String KEY_PLACE = "place";
	private static final String KEY_PHONENUMBER = "phone";
	private static final String KEY_STIME = "stime";
	private static final String KEY_ETIME = "etime";
	private static final String KEY_S1TIME = "s1time";
	private static final String KEY_E1TIME = "e1time";
	private static final String KEY_QUANTITY = "quantity";
	private static final String KEY_EXPLANATION = "explanation";
	private static final String KEY_IMAGE = "image";
	private static final String KEY_SIZE = "size";
	private int want_quantity;
	private int want_price;
	
	private SharedPreferences mPrefs;
	private ImageButton wholeViewBtn;
	private ImageButton refreshBtn;
	MyPagerAdapter pagerAdapter;
	ArrayList<Food> foods2;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        mPrefs = getApplicationContext().getSharedPreferences("hackathon", MODE_PRIVATE);
        foods2 = getFoods();
        
        want_quantity = mPrefs.getInt(QuantityChoiceActivity.SP_QUANTITY, -1);
        want_price = mPrefs.getInt(PriceChoiceActivity.SP_PRICE, -1);
        
        RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.menu_RL1);
        LinearLayout ll1 = (LinearLayout) findViewById(R.id.menu_LL1);
        LinearLayout ll2 = (LinearLayout) findViewById(R.id.menu_LL2);
        
        refreshBtn = (ImageButton)findViewById(R.id.menu_refreshButton);
        wholeViewBtn = (ImageButton)findViewById(R.id.menu_wholeViewButton);
        
        Display display = getWindowManager().getDefaultDisplay();
        
        rl1.getLayoutParams().height = display.getHeight()/7;
        ll1.getLayoutParams().height = display.getHeight()*3/7;
        ll2.getLayoutParams().height = display.getHeight()*3/7;
        
        refreshBtn.getLayoutParams().width = display.getWidth()/2;
        wholeViewBtn.getLayoutParams().width = display.getWidth()/2;
        
        List<Fragment> fragments = getFragments();
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.menu_ViewPager);
        pager.setAdapter(pagerAdapter);
        
        wholeViewBtn.setOnClickListener(this);
    }
	
	private List<Fragment> getFragments(){
		
		List<Fragment> fList = new ArrayList<Fragment>();
		
		fList.add(ImageFragment.newInstance());
		fList.add(ImageFragment.newInstance());
		fList.add(ImageFragment.newInstance());
		fList.add(ImageFragment.newInstance());
		fList.add(ImageFragment.newInstance());
		
		return fList;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.menu_wholeViewButton:
			Intent wholeViewIntent = new Intent(MenuActivity.this, WholeViewActivity.class);
			startActivity(wholeViewIntent);
			break;
		}
		
	}
	
	private InputStream getInputStream(String para_url){
		  while(true){
		   try{
		    URL url = new URL(para_url);
		    URLConnection con= url.openConnection();
		    InputStream is = con.getInputStream();
		    return is;
		   } catch (Exception e) {
		    Log.d("mytag", e.getMessage());
		   }
		  }
		 }
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public ArrayList<Food> getFoods(){
		Log.i("Hello","Hello");
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
		ArrayList<Food> foods = new ArrayList<Food>();
		String tagName = "";
		String name = "";
		String price = "";
		String place = "";
		String phone_number = "";
		String stime = "";
		String etime = "";
		String s1time = "";
		String e1time = "";
		String quantity = "";
		String explanation = "";
		String image = "";
		String size = "";
		
		
		try {
			Log.i("Hi","Hi");
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance(); 
			factory.setNamespaceAware(true); 
			XmlPullParser xpp = factory.newPullParser(); 
			InputStream stream1 = getInputStream("http://rgy.wo.tc/xmparse.php");
			InputStream stream = getInputStream("http://rgy.wo.tc/connection/data/client.xml");
			xpp.setInput(stream, "euc-kr"); 
			Log.i("works","works");
			int eventType = xpp.getEventType(); 
			boolean isItemTag = false;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch(eventType){
			    case XmlPullParser.START_DOCUMENT:
			     break;
			    case XmlPullParser.END_DOCUMENT:
			     break;
			     
			    case XmlPullParser.START_TAG:
			    	tagName = xpp.getName();
			    	if(tagName.equals("item")) isItemTag = true;
			     break;
			     
			     
			    case XmlPullParser.TEXT:
			    	if(isItemTag && tagName.equals(KEY_NAME)){
			    		name += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_PRICE)){
			    		price += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_PLACE)){
			    		place += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_PHONENUMBER)){
			    		phone_number += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_STIME)){
			    		stime += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_ETIME)){
			    		etime += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_S1TIME)){
			    		s1time += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_E1TIME)){
			    		e1time += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_QUANTITY)){
			    		quantity += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_EXPLANATION)){
			    		explanation += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_IMAGE)){
			    		image += xpp.getText();
			    	}
			    	if(isItemTag && tagName.equals(KEY_SIZE)){
			    		size += xpp.getText();
			    	}
			     break; 
			     
			    case XmlPullParser.END_TAG:
			    	tagName=xpp.getName();
	                   
                    if(tagName.equals("item")){
                    	 Food food = new Food(0, name, price,
                    			 //Integer.parseInt(price), 
                    			 place, phone_number, stime, etime, s1time, e1time, 
					    		  quantity, explanation, image, size);
					     foods.add(food);
					     
					     tagName = "";
					     name = "";
						 price = "";
						 place = "";
						 phone_number = "";
						 stime = "";
						 etime = "";
						 s1time = "";
						 e1time = "";
						 quantity = "";
						 explanation = "";
						 image = "";
						 size = "";
						 isItemTag = false; //�ʱ�ȭ
       
                    }
			     break;
			    }
			    eventType = xpp.next();
			   }

			  }
			  catch(Exception e){
			   Log.d("mytag", e.getMessage());
			   return null;
			  }
		return foods;
	}

}
