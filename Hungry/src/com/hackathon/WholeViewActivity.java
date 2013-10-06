package com.hackathon;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;

public class WholeViewActivity extends Activity{
	private static final String KEY_ITEM = "item";
	private static final String KEY_NAME = "name";
	private static final String KEY_PRICE = "price";
	private static final String KEY_PLACE = "place";
	private static final String KEY_PHONENUMBER = "phone_number";
	private static final String KEY_STIME = "stime";
	private static final String KEY_ETIME = "etime";
	private static final String KEY_S1TIME = "s1time";
	private static final String KEY_E1TIME = "e1time";
	private static final String KEY_QUANTITY = "quantity";
	private static final String KEY_EXPLANATION = "explanation";
	private static final String KEY_IMAGE = "image";
	private static final String KEY_SIZE = "size";
	private ListView listView;
	ArrayList<Food> foods2;
	ProgressDialog dialog;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_view);
        foods2 = new ArrayList<Food>();
        foods2 = getFoods();
        
        CustomListAdapter adapter = new CustomListAdapter(this, foods2);
        
        listView = (ListView) findViewById(R.id.whole_view_list);
        listView.setAdapter(adapter);
        
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
	private ArrayList<Food> getFoods(){
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
                    	 Food food = new Food(0, name, 0,
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
	/*
	public class ParsingTask extends AsyncTask<Integer,Void,Void>{
		private Context mCtx;
		
		public ParsingTask(Context ctx){
			this.mCtx = ctx;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog = new ProgressDialog(mCtx);
			dialog.setTitle("목록 다운로드 중");
			dialog.setMessage("잠시만 기다리세요");
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.show();
			Log.i("This?","This");
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			Log.i("maybe","maybe");
			if(dialog.isShowing()) dialog.dismiss();
		}

		@Override
		protected Void doInBackground(Integer... arg0) {
			// TODO Auto-generated method stub
			Log.i("what","what");
			foods2 = getFoods();
			Log.i("is","is");
			return null;
		}
		
	}*/

}
