package com.hackathon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private static final String EGG = "Made By Wonseok";
	private static final String KEY_ITEM = "item";
	private static final String KEY_NAME = "name";
	private static final String KEY_PRICE = "price";
	SharedPreferences mPrefs;
	ImageButton imgBtn1;
	ImageButton imgBtn2;
	
	List<Food> foods;
	HttpClient httpClient;
	HttpGet httpGet;
	HttpResponse reponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mPrefs = getApplicationContext().getSharedPreferences("hackathon", MODE_PRIVATE);
        Editor editor = mPrefs.edit();
        editor.putString("EGG", EGG);
        editor.commit();
        
        imgBtn1 = (ImageButton)findViewById(R.id.main_image_btn1);
        imgBtn2 = (ImageButton)findViewById(R.id.main_image_btn2);
        imgBtn1.setOnClickListener(this);
        imgBtn2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.main_image_btn1:
			Intent eatIntent = new Intent(MainActivity.this, 
					QuantityChoiceActivity.class);
			startActivity(eatIntent);
			break;
		case R.id.main_image_btn2:
			connectDB();
			break;
		}
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private void connectDB(){
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
		try {
			
			String url = "http://rgy.wo.tc/xmparse.php";
			httpClient = new DefaultHttpClient();
			httpGet = new HttpGet(url);
			reponse = httpClient.execute(httpGet);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("x","x");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("x","y");
		}
		/*
		foods = new ArrayList<Food>();
		String url = "http://rgy.wo.tc/xmparse.php";
		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(url);
		Document doc = parser.getDomElement(xml);
		
		NodeList nl = doc.getElementsByTagName(KEY_ITEM);
		
		for(int i = 0; i < nl.getLength(); i++){
			Element e = (Element) nl.item(i);
			String name = parser.getValue(e, KEY_NAME);
			int price = Integer.parseInt(parser.getValue(e, KEY_NAME));
			Food food = new Food(0, name, price, "place", 
					"phone_number", "stime", "etime", "s1time", "e1time", 
					"quantity", "explanation", "image", "size");
			foods.add(food);
		}*/

		Toast toast = Toast.makeText(this, "Success", Toast.LENGTH_SHORT);
		toast.show();
	}

    
}
