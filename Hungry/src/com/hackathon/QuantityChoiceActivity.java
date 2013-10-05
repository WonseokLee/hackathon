package com.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class QuantityChoiceActivity extends Activity implements OnClickListener{
	public static final String SP_QUANTITY = "quantity";
	
	private int quantity;
	SharedPreferences mPrefs;
	Editor editor;
	ImageButton imgbtn1;
	ImageButton imgbtn2;
	ImageButton imgbtn3;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity_choice);
        
        mPrefs = getApplicationContext().getSharedPreferences("hackathon", MODE_PRIVATE);
        editor = mPrefs.edit();
        
        imgbtn1 = (ImageButton) findViewById(R.id.quantity_choice_imgbtn1);
        imgbtn2 = (ImageButton) findViewById(R.id.quantity_choice_imgbtn2);
        imgbtn3 = (ImageButton) findViewById(R.id.quantity_choice_imgbtn3);
        
        imgbtn1.setOnClickListener(this);
        imgbtn2.setOnClickListener(this);
        imgbtn3.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.quantity_choice_imgbtn1:
			quantity = 0;
			break;
		case R.id.quantity_choice_imgbtn2:
			quantity = 1;
			break;
		case R.id.quantity_choice_imgbtn3:
			quantity = 2;
			break;
		}
		editor.putInt(SP_QUANTITY, quantity);
		editor.commit();
		
		Intent toPriceIntent = new Intent(QuantityChoiceActivity.this,
				PriceChoiceActivity.class);
		startActivity(toPriceIntent);
		
		
	}

}
