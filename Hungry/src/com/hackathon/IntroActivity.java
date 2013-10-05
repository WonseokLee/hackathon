package com.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IntroActivity extends Activity{
	private Handler handler;
	private static final int DELAYED_TIME = 3000;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        
        handler = new Handler();
        handler.postDelayed(irun, DELAYED_TIME);
    }
	
	Runnable irun = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(IntroActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			
			overridePendingTransition(android.R.anim.fade_in,
					android.R.anim.fade_out);
		}
		
	};
	
	public void onBackPressed(){
		super.onBackPressed();
		handler.removeCallbacks(irun);
	}

}
