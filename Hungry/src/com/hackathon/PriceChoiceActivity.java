package com.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PriceChoiceActivity extends Activity implements OnClickListener{
	public static final String SP_PRICE = "price";
	
	private int price;
	SharedPreferences mPrefs;
	Editor editor;
	ImageButton imgbtn1;
	ImageButton imgbtn2;
	ImageButton imgbtn3;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_choice);
        
        mPrefs = getApplicationContext().getSharedPreferences("hackathon", MODE_PRIVATE);
        editor = mPrefs.edit();
        
        imgbtn1 = (ImageButton) findViewById(R.id.price_choice_imgbtn1);
        imgbtn2 = (ImageButton) findViewById(R.id.price_choice_imgbtn2);
        imgbtn3 = (ImageButton) findViewById(R.id.price_choice_imgbtn3);
        
        imgbtn1.setOnClickListener(this);
        imgbtn2.setOnClickListener(this);
        imgbtn3.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.price_choice_imgbtn1:
			price = 0;
			break;
		case R.id.price_choice_imgbtn2:
			price = 1;
			break;
		case R.id.price_choice_imgbtn3:
			price = 2;
			break;
		case R.id.price_choice_imgbtn4:
			price = 3;
			break;
		}
		
		editor.putInt(SP_PRICE, price);
		editor.commit();
		
		Intent menuIntent = new Intent(PriceChoiceActivity.this,
				MenuActivity.class);
		startActivity(menuIntent);
		
	}

}
