package com.ramya.evesafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
 
public class MainActivity extends Activity 
{
  
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 2000; 
    private Handler myhandler;
  
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        myhandler = new Handler();
  
        myhandler.postDelayed(new Runnable()
        {
            public void run() 
            {
  
               finish();
                 
               if (!mIsBackButtonPressed)
               {
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    MainActivity.this.startActivity(intent);
               }
                  
            }
  
        }, SPLASH_DURATION); 
    }
     
    public void onBackPressed() 
    {
        mIsBackButtonPressed = true;
        super.onBackPressed();
    }
        
}