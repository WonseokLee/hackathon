package com.hackathon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MenuActivity extends Activity implements OnClickListener{
	SharedPreferences mPrefs;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        
        mPrefs = getApplicationContext().getSharedPreferences("hackathon", MODE_PRIVATE);

    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
