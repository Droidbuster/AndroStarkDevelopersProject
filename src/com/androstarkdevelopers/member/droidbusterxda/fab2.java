package com.androstarkdevelopers.member.droidbusterxda;



import com.androstarkdevelopers.member.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;



public class fab2 extends ImageButton {
	private String Myfaburi;
	 private ImageButton	fabimg1;
		Animation	subhide  ;
		Animation subshow;
		private BroadcastReceiver munhidefab;

		private BroadcastReceiver mhidefab;
	public fab2 (final Context c, AttributeSet attrs) {
		super(c, attrs);
		// TODO Auto-generated constructor stub
		fabimg1 = (this);
		this.subhide  = AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.subfabshowhide);
	   this.subshow =AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.subfabshow);
		fabimg1.setVisibility(GONE);
		SharedPreferences  sharedPreferences = c.getSharedPreferences("Droidbuster", c.MODE_PRIVATE);
		
		Boolean gettriggered = sharedPreferences.getBoolean("gettriggered", false);
	
		fabimg1.setVisibility(GONE);
		if (gettriggered  == false)	{
			fabimg1.setVisibility(GONE);
			fabimg1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 Intent i = new Intent();
						i.setAction(Intent.ACTION_CALL_BUTTON);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
				getContext().startActivity(i);	
				}
			});
		}
		else {
			fabimg1.setVisibility(INVISIBLE);
		}
		
		 
		 
		 
		munhidefab = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context arg0, Intent i) {
				// TODO Auto-generated method stub
				fabimg1.setVisibility(VISIBLE);
				
				   SharedPreferences sharedPreferences = c.getSharedPreferences("Droidbuster",Context.MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		            editor.putBoolean("gettriggered", true); //true or false
		            fabimg1.startAnimation(subshow);
		            editor.commit();
			}
		};
				
		
		
		
		mhidefab = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				fabimg1.setVisibility(GONE);
				fabimg1.startAnimation(subhide);
				   SharedPreferences sharedPreferences = c.getSharedPreferences("Droidbuster",Context.MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		            editor.putBoolean("gettriggered", false); //true or false
		            editor.commit();
			}
		};
		
		
		
		
		  
	}	
		
	   protected void onAttachedToWindow()
	    {
	        super.onAttachedToWindow();
	        getContext().registerReceiver(mhidefab, new IntentFilter("com.droidbuster.hide.fabs"));
	        getContext().registerReceiver(munhidefab, new IntentFilter("com.droidbuster.show.fabs"));

	    }
	    
	    protected void onDetachedFromWindow()
	    {
	        super.onDetachedFromWindow();
	        getContext().unregisterReceiver(mhidefab);
	        getContext().unregisterReceiver(munhidefab);

	    }
	}
