package com.hackathon;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener{
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
	int a[];
	ArrayList<Integer> b;
	ArrayList<Integer> c;
	int quantity;
	int price;
	ViewPager pager;
	ProgressDialog dialog;
	Context myContext = this;
	
	TextView textName;
	TextView textPrice;
	TextView textPlace;
	TextView textPhone;
	TextView textTime;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        mPrefs = getApplicationContext().getSharedPreferences("hackathon", MODE_PRIVATE);
        quantity = mPrefs.getInt("quantity", 0);
        price = mPrefs.getInt("price", 0);
        dialog = new ProgressDialog(this);
        foods2 = getFoods();
        
        want_quantity = mPrefs.getInt(QuantityChoiceActivity.SP_QUANTITY, -1);
        want_price = mPrefs.getInt(PriceChoiceActivity.SP_PRICE, -1);
        
        RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.menu_RL1);
        LinearLayout ll1 = (LinearLayout) findViewById(R.id.menu_LL1);
        LinearLayout ll2 = (LinearLayout) findViewById(R.id.menu_LL2);
        
        refreshBtn = (ImageButton)findViewById(R.id.menu_refreshButton);
        wholeViewBtn = (ImageButton)findViewById(R.id.menu_wholeViewButton);
        
        textName = (TextView) findViewById(R.id.menu_name);
        textPrice = (TextView) findViewById(R.id.menu_price);
        textPlace = (TextView) findViewById(R.id.menu_place);
        textPhone = (TextView) findViewById(R.id.menu_phone_number);
        textTime = (TextView) findViewById(R.id.menu_time);
        
        Display display = getWindowManager().getDefaultDisplay();
        
        rl1.getLayoutParams().height = display.getHeight()/7;
        ll1.getLayoutParams().height = display.getHeight()*3/7;
        ll2.getLayoutParams().height = display.getHeight()*3/7;
        
        refreshBtn.getLayoutParams().width = display.getWidth()/2;
        wholeViewBtn.getLayoutParams().width = display.getWidth()/2;
        
        List<Fragment> fragments = getFragments(foods2);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager)findViewById(R.id.menu_ViewPager);
        pager.setAdapter(pagerAdapter);
        
        wholeViewBtn.setOnClickListener(this);
        
        pager.setOnPageChangeListener(this);
        
        
    }
	
	private List<Fragment> getFragments(ArrayList<Food> foods){
		AsyncTask<Void, Void, List<Fragment>> task = new HeavyTask();
		List<Fragment> fList = new ArrayList<Fragment>();
		try {
			fList = task.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
						 isItemTag = false; //초기화
       
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

	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		if(state == 1){
		textName.setText("...");
		textPrice.setText("...");
		textPlace.setText("...");
		textPhone.setText("...");
		textTime.setText("...");
		}
		else{
			int i = pager.getCurrentItem();
			textName.setText(foods2.get(a[i]).getName());
			textPrice.setText(foods2.get(a[i]).getPrice());
			textPlace.setText(foods2.get(a[i]).getPlace());
			textPhone.setText(foods2.get(a[i]).getPhoneNumber());
			textTime.setText(foods2.get(a[i]).getSTime()+" ~ "+foods2.get(a[i]).getETime());
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int state) {
		// TODO Auto-generated method stub

		textName.setText(foods2.get(a[state]).getName());
		textPrice.setText(foods2.get(a[state]).getPrice());
		textPlace.setText(foods2.get(a[state]).getPlace());
		textPhone.setText(foods2.get(a[state]).getPhoneNumber());
		textTime.setText(foods2.get(a[state]).getSTime()+" ~ "+foods2.get(a[state]).getETime());
		
	}
	
	public class HeavyTask extends AsyncTask<Void, Void, List<Fragment>>{
		
		

		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			dialog.setMessage("처리중입니다");
			dialog.show();
		}

		protected void onPreExecute() {
			// TODO Auto-generated method stub
			if(dialog.isShowing()) dialog.dismiss();
		}

		@Override
		protected List<Fragment> doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			a = new int[foods2.size()];
			b = new ArrayList<Integer>();
			c = new ArrayList<Integer>();
			for(int i = 0; i < foods2.size(); i++){
				a[i] = i;
			}
			for(int i = 0; i < foods2.size(); i++){
				switch(quantity){
				case 0:
					if(foods2.get(i).getQuantity().equals("소\n")){
						b.add(i);
					}break;
				case 1:
					if(foods2.get(i).getQuantity().equals("중\n")){
						b.add(i);
					}break;
				case 2:
					if(foods2.get(i).getQuantity().equals("대\n")){
						b.add(i);
					}break;
				}
				
			}
			Log.i("b size","The size of b is "+b.size());
			for(int i = 0; i < b.size(); i++){
				String[] s = foods2.get(i).getPrice().split("\n");
				int k = Integer.parseInt(s[0]);
				Log.i("integer", "the value is :"+k);
				switch(price){
				case 0:
					if(k<=2000){
						c.add(i);
					}break;
				case 1:
					if(2000 < k && k <= 4000){
						c.add(i);
					}break;
				case 2:
					if(4000 < k && k <= 7000){
						c.add(i);
					}break;
				case 3:
					if(k > 7000){
						c.add(i);
					}break;
					
				}
				
			}

			List<Fragment> fList = new ArrayList<Fragment>();
			Random r = new Random();
			a =  new int[5];
			int k;
			if( c.isEmpty()){
				for(int i = 0; i < 5; i++){
					a[i] = i;
				}
			}
			else{
				for(int i = 0; i < 5; i++){
					if(c.size()!=0)
						k = r.nextInt(c.size());
					else
						k = 0;
					a[i] = c.get(k);
				}
			}
			int id[] = new int[5];
			id[0] = getResources().getIdentifier("picture"+(a[0]+1), "drawable", myContext.getPackageName());
			id[1] = getResources().getIdentifier("picture"+(a[1]+1), "drawable", myContext.getPackageName());
			id[2] = getResources().getIdentifier("picture"+(a[2]+1), "drawable", myContext.getPackageName());
			id[3] = getResources().getIdentifier("picture"+(a[3]+1), "drawable", myContext.getPackageName());
			id[4] = getResources().getIdentifier("picture"+(a[4]+1), "drawable", myContext.getPackageName());
			
			fList.add(ImageFragment.newInstance(foods2.get(a[0]),id[0]));
			fList.add(ImageFragment.newInstance(foods2.get(a[1]),id[1]));
			fList.add(ImageFragment.newInstance(foods2.get(a[2]),id[2]));
			fList.add(ImageFragment.newInstance(foods2.get(a[3]),id[3]));
			fList.add(ImageFragment.newInstance(foods2.get(a[4]),id[4]));
			
			textName.setText(foods2.get(a[0]).getName());
			textPrice.setText(foods2.get(a[0]).getPrice());
			textPlace.setText(foods2.get(a[0]).getPlace());
			textPhone.setText(foods2.get(a[0]).getPhoneNumber());
			textTime.setText(foods2.get(a[0]).getSTime()+" ~ "+foods2.get(a[0]).getETime());
			
			return fList;
		}
		
	}

}
