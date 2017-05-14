package com.ramya.evesafe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
public class Call extends Activity {
	EditText num;
	ImageButton getContacts;
	ImageButton cal,back;
	private static final int CONTACT_PICKER_RESULT = 1001;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call);
num=(EditText)findViewById(R.id.call_num);

   ImageButton getContacts = (ImageButton) findViewById(R.id.call_contacts);
    getContacts.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
        	Intent i = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(i, CONTACT_PICKER_RESULT);

        }
     });

back=(ImageButton)findViewById(R.id.call_back);
back.setOnClickListener(new OnClickListener(){
public void onClick(View v) {
	Intent intent=new Intent(Call.this,HomePage.class);
	startActivity(intent);	
}});
ImageButton call=(ImageButton)findViewById(R.id.call_call);
    call.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		String uri="tel:"+num.getText().toString();
		Intent calint =new Intent(Intent.ACTION_CALL,Uri.parse(uri));
	   startActivity(calint);
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
            //String lastName = "";
            try {

                Uri result = data.getData();

                // get the id from the uri
                String id = result.getLastPathSegment();

                // query
                cursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone._ID
                                + " = ? ", new String[] { id }, null);

                // cursor = getContentResolver().query(Phone.CONTENT_URI,
                // null, Phone.CONTACT_ID + "=?", new String[] { id },
                // null);

                int numberIdx = cursor.getColumnIndex(Phone.DATA);

                if (cursor.moveToFirst()) {
                    number = cursor.getString(numberIdx);
                    // lastName =
                    // cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
                } else {
                    // WE FAILED
                }
            } catch (Exception e) {
                // failed
            } finally {
                if (cursor != null) {
                    cursor.close();
                } else {
                }
            }
            num.setText(number);

        }

    }
}
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    	}
}

