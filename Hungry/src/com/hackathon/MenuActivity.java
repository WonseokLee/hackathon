package com.hackathon;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MenuActivity extends FragmentActivity implements OnClickListener{
	private int want_quantity;
	private int want_price;
	
	private SharedPreferences mPrefs;
	private ImageButton wholeViewBtn;
	private ImageButton refreshBtn;
	MyPagerAdapter pagerAdapter;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        mPrefs = getApplicationContext().getSharedPreferences("hackathon", MODE_PRIVATE);
        
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

}
