package com.ramya.evesafe;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
//import android.widget.TextView;
import android.widget.Toast;

public class SendLocation extends Activity {
	 
	ImageButton getnum,getloc,sndlo,back;

	 EditText ed,ed1;
	LocationManager location_manager;
	String getLatitude;
	String getLongitude;
	 String number="";
	double x;
	double y;
	private static final int CONTACT_PICKER_RESULT = 1001;
	Geocoder geocoder;
	List<Address> addresses;
	Location loc;
	 
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.location);
	
	ed=(EditText)findViewById(R.id.loc_ednum);
	ed1=(EditText)findViewById(R.id.loc_loc);
	getloc = (ImageButton) findViewById(R.id.loc_getlo);
	//lati = (TextView) findViewById(R.id.latitude);
	//longi = (TextView) findViewById(R.id.longitude);
	//address = (TextView) findViewById(R.id.address);
	location_manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

back=(ImageButton)findViewById(R.id.loc_back);
back.setOnClickListener(new OnClickListener(){
public void onClick(View v) {
	Intent intent=new Intent(SendLocation.this,HomePage.class);
	startActivity(intent);	
}});
	getloc.setOnClickListener(new OnClickListener() {
	 
	public void onClick(View arg0) {	 
	LocationListener listner = new MyLocationListner();
	location_manager.requestLocationUpdates(
	LocationManager.GPS_PROVIDER, 0, 0, listner);
	 
	}
	});
	getnum = (ImageButton) findViewById(R.id.loc_cont);
	getnum.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {
	        Intent i = new Intent(Intent.ACTION_PICK,
	                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
	        startActivityForResult(i, CONTACT_PICKER_RESULT);

	    }
	});

	sndlo=(ImageButton)findViewById(R.id.loc_send);
	sndlo.setOnClickListener(new OnClickListener(){
	public void onClick(View v){
		sendSms();
	}
	private void sendSms() {
		try{
		  String phone=ed.getText().toString(); 
			String info=ed1.getText().toString();
	    	  SmsManager smsManager = SmsManager.getDefault();      
	    	  
				smsManager.sendTextMessage(phone,null,"my location is "+info, null, null);    
	    	     Toast.makeText(getApplicationContext(),"SMS sent.",Toast.LENGTH_LONG).show();  			    
	      }  
		catch(Exception e){
			Toast.makeText(getApplicationContext(), "sms not sent.", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

	}
	});
	}
	protected void onActivityResult(int reqCode, int resultCode, Intent data) {
	super.onActivityResult(reqCode, resultCode, data);
	if (resultCode == RESULT_OK) {
	switch (reqCode) {
	case CONTACT_PICKER_RESULT:
	    Cursor cursor = null;
	    try {

	        Uri result = data.getData();
	        String id = result.getLastPathSegment();
	        cursor = getContentResolver().query(
	                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
	                null,
	                ContactsContract.CommonDataKinds.Phone._ID
	                        + " = ? ", new String[] { id }, null);

	        int numberIdx = cursor.getColumnIndex(Phone.DATA);

	        if (cursor.moveToFirst()) {
	            number = cursor.getString(numberIdx);
	        } else {
	        }
	    } catch (Exception e) {
	    } finally {
	        if (cursor != null) {
	            cursor.close();
	        } else {
	        }
	    }
	    ed.setText(number);
	}
	}
	}
	 
	public class MyLocationListner implements LocationListener {
	 
	@SuppressWarnings("static-access")
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onLocationChanged(Location location) {
	 
	getLatitude = "" + location.getLatitude();
	getLongitude = "" + location.getLongitude();
	 
//	lati.setText(getLatitude);
	//longi.setText(getLongitude);
	 
	x = location.getLatitude();
	y = location.getLongitude();
	
	try {
	geocoder = new Geocoder(SendLocation.this, Locale.ENGLISH);
	addresses = geocoder.getFromLocation(x, y, 1);
	StringBuilder str = new StringBuilder();
	if (geocoder.isPresent()) {

	Address returnAddress = addresses.get(0);
	 
	String localityString = returnAddress.getLocality();
	String city = returnAddress.getCountryName();
	String region_code = returnAddress.getCountryCode();
	String zipcode = returnAddress.getPostalCode();
	 
	str.append(localityString + " ");
	str.append(city + " " + region_code + " ");
	str.append(zipcode + " ");
	 
	//address.setText(str);
	ed1.setText("My Current Locations is: "+str+"Latitude point is: "+getLatitude+"Longitude point is: "+getLongitude);
	
	} else {
	}
	} catch (IOException e) {
	 	}	 
	}
	 
	public void onProviderDisabled(String arg0) {	 
	}
	 
	public void onProviderEnabled(String arg0) {
	 
	}
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {	 
	}	 
	}		 
	}