package com.ramya.evesafe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CallSOS extends Activity {
	ImageButton cp,ch,cf,back;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callsos);
		 cp=(ImageButton)findViewById(R.id.sos_polibut);
		cp.setOnClickListener(new OnClickListener(){	
			public void onClick(View v){
				Intent intentp=new Intent(Intent.ACTION_CALL,Uri.parse("tel:100"));
				startActivity(intentp);
			}
		});
		 ch=(ImageButton)findViewById(R.id.sos_ambbut);
		ch.setOnClickListener(new OnClickListener(){
		
			public void onClick(View v){
				Intent intenta=new Intent(Intent.ACTION_CALL,Uri.parse("tel:108"));
				startActivity(intenta);
			}
		});
		 cf=(ImageButton)findViewById(R.id.sos_firbut);
		cf.setOnClickListener(new OnClickListener(){
		
			public void onClick(View v){
				Intent intentf=new Intent(Intent.ACTION_CALL,Uri.parse("tel:102"));
				startActivity(intentf);
			}
		});

back=(ImageButton)findViewById(R.id.sos_bakbut);
back.setOnClickListener(new OnClickListener(){
public void onClick(View v) {
	Intent intent=new Intent(CallSOS.this,HomePage.class);
	startActivity(intent);	
}});
	}

}
