package com.hackathon;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;

public class WholeViewActivity extends Activity{
	private static final String KEY_ITEM = "item";
	private static final String KEY_NAME = "name";
	private static final String KEY_PRICE = "price";
	private ListView listView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_view);
        
        CustomListAdapter adapter = new CustomListAdapter(this, getFoods());
        
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
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
		ArrayList<Food> foods = new ArrayList<Food>();
		String tagName = "";
		String name = "";
		
		
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance(); 
			factory.setNamespaceAware(true); 
			XmlPullParser xpp = factory.newPullParser(); 
			InputStream stream = getInputStream("http://rgy.wo.tc/connection/data/client.xml");
			xpp.setInput(stream, "euc-kr"); 
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
			     break; 
			     
			    case XmlPullParser.END_TAG:
			    	tagName=xpp.getName();
	                   
                    if(tagName.equals("item")){
                    	 Food food = new Food(0, name, 0, "place", 
					    		  "phone_number", "stime", "etime", "s1time", "e1time", 
					    		  "quantity", "explanation", "image", "size");
					     foods.add(food);
					     
                        name=""; //초기화
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

}
