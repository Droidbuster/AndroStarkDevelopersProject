package com.androstarkdevelopers.member.droidbusterxda;

import com.androstarkdevelopers.member.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class fabmain extends ImageButton{
	Uri Myuri;
	String Myfaburi ;
	ImageButton fabimg;
	Animation animshow;
	Animation  slideupfab;

		public fabmain(final Context c, AttributeSet attrs) {
			super(c, attrs);
			// TODO Auto-generated constructor stub
			fabimg = (this);
			 SharedPreferences sharedPreferences = c.getSharedPreferences("Droidbuster",Context.MODE_PRIVATE);    
			 final Animation rotatefab = AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.fabrotation);
			 final Animation rotateback = AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.fabrotationback);
			 slideupfab = AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.visitmainfabinlayout);
			 fabimg.startAnimation(slideupfab);
			 Boolean Netvisiblity= sharedPreferences.getBoolean("gettriggered",false);
		
			   if (Netvisiblity == false){
				   fabimg.setSelected(false);
			     }
			     else{
			    	 fabimg.setSelected(true); 
			     }

			   
			   
			   fabimg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if (v.isSelected()){
			            v.setSelected(false);
						Intent intent = new Intent();
						intent.setAction("com.droidbuster.hide.fabs");
						c.sendBroadcast(intent);
		                SharedPreferences sharedPreferences = c.getSharedPreferences("Droidbuster",Context.MODE_PRIVATE);
		                SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		                editor.putBoolean("gettriggered", true); //true or false
		         	   fabimg.startAnimation(rotateback);
		         	   
		                editor.commit();
			        } else {
			            v.setSelected(true);
						Intent intent = new Intent();
						intent.setAction("com.droidbuster.show.fabs");
						c.sendBroadcast(intent);
						fabimg.startAnimation(rotatefab);
		                SharedPreferences sharedPreferences = c.getSharedPreferences("Droidbuster",Context.MODE_PRIVATE);
		                SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		                editor.putBoolean("gettriggered", false); //true or false
		                editor.commit();
			        }		};
			});
			   
			 
		
		}
		
		
		

	}


