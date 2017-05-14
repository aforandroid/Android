package com.ramya.evesafe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomePage extends Activity {
	ImageButton sendmgs,sendmgslo,callcontacts,emergcalls,sendemail,stip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        sendmgs=(ImageButton)findViewById(R.id.home_sendsms);
        sendmgs.setOnClickListener(new OnClickListener(){
    	public void onClick(View v) {
    		Intent intent=new Intent(HomePage.this,SendSMS.class);
    		startActivity(intent);	
    	}});
        sendmgslo=(ImageButton)findViewById(R.id.home_location);
        sendmgslo.setOnClickListener(new OnClickListener(){
    	public void onClick(View v) {
    		Intent intent=new Intent(HomePage.this,SendLocation.class);
    		startActivity(intent);	
    	}});
        callcontacts=(ImageButton)findViewById(R.id.home_call);
        callcontacts.setOnClickListener(new OnClickListener(){
    	public void onClick(View v) {
    		Intent intent=new Intent(HomePage.this,Call.class);
    		startActivity(intent);	
    	}});
        
        emergcalls=(ImageButton)findViewById(R.id.home_sos);
        emergcalls.setOnClickListener(new OnClickListener(){
    	public void onClick(View v) {
    		Intent intent=new Intent(HomePage.this,CallSOS.class);
    		startActivity(intent);	
    	}});
        
        sendemail=(ImageButton)findViewById(R.id.home_sendmail);
        sendemail.setOnClickListener(new OnClickListener(){
    	public void onClick(View v) {
    		Intent intent=new Intent(HomePage.this,SendMail.class);
    		startActivity(intent);	
    	}});
        
        stip=(ImageButton)findViewById(R.id.home_tips);
        stip.setOnClickListener(new OnClickListener(){
    	public void onClick(View v) {
    		Intent intent=new Intent(HomePage.this,Tips.class);
    		startActivity(intent);	
    	}});
      
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
            case R.id.dark:  
                setTheme(R.style.BlackTheme);
            return true;     
           case R.id.light:  
        	    setTheme(R.style.LightTheme);
              return true;         
              default:  
                return super.onOptionsItemSelected(item);  
        }   
}
}
