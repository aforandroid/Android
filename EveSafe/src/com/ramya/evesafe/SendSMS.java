package com.ramya.evesafe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SendSMS extends Activity {

	ImageButton sendmgs,back;
    ImageButton getContacts;
    private static final int CONTACT_PICKER_RESULT = 1001;
	EditText en;
    
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendsms);
        en=(EditText)findViewById(R.id.sms_num);
        
        getContacts=(ImageButton)findViewById(R.id.sms_cont);
        getContacts.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(i, CONTACT_PICKER_RESULT);

        }
    });

back=(ImageButton)findViewById(R.id.sms_back);
back.setOnClickListener(new OnClickListener(){
public void onClick(View v) {
	Intent intent=new Intent(SendSMS.this,HomePage.class);
	startActivity(intent);	
}});
    sendmgs=(ImageButton)findViewById(R.id.sms_send);
    sendmgs.setOnClickListener(new OnClickListener(){
	public void onClick(View v){
		sendSms();
	}
	private void sendSms() {
		try{
		  String phone=en.getText().toString(); 
		 
	    	  SmsManager smsManager = SmsManager.getDefault();      
	    	  
				smsManager.sendTextMessage(phone,null,"iam in danger,please save me!", null, null);    
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
        String number = "";
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
        en.setText(number);
      
    }
    }
}
public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.main, menu);
return true;
}
}

