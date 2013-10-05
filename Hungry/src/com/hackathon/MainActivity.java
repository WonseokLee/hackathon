package com.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {
	private static final String EGG = "Made By Wonseok";
	SharedPreferences mPrefs;
	ImageButton imgBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mPrefs = getApplicationContext().getSharedPreferences("hackathon", MODE_PRIVATE);
        Editor editor = mPrefs.edit();
        editor.putString("EGG", EGG);
        editor.commit();
        
        imgBtn1 = (ImageButton)findViewById(R.id.main_image_btn1);
        imgBtn1.setOnClickListener(this);
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
		}
	}
    
}
