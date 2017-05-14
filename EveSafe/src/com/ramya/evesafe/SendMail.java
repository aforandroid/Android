
package com.ramya.evesafe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SendMail extends Activity implements OnClickListener {

	EditText emailto;
    ImageButton btSnd,back;
    ImageButton atta;
    String email, attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmail);
        emailto = (EditText) findViewById(R.id.email_id);
        btSnd= (ImageButton) findViewById(R.id.email_send);
         atta = (ImageButton)findViewById(R.id.email_gall);

back=(ImageButton)findViewById(R.id.email_back);
back.setOnClickListener(new OnClickListener(){
public void onClick(View v) {
	Intent intent=new Intent(SendMail.this,HomePage.class);
	startActivity(intent);	
}});
         btSnd.setOnClickListener(this);
         atta.setOnClickListener(this);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
               Uri selectedImage = data.getData();
               String[] filePathColumn = { MediaStore.Images.Media.DATA };

               Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
               cursor.moveToFirst();
               columnIndex = cursor.getColumnIndex(filePathColumn[0]);
               attachmentFile = cursor.getString(columnIndex);
               Log.e("Attachment Path:", attachmentFile);
               URI = Uri.parse("file://" + attachmentFile);
               cursor.close();
        }
 }
	public void onClick(View v) {
		if (v == atta) {
            openGallery();

     }
     if (v == btSnd) {
            try {
                  email = emailto.getText().toString();

                  final Intent emailIntent = new Intent(
                                android.content.Intent.ACTION_SEND);
                  emailIntent.setType("plain/text");
                  emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                                new String[] { email });
                  if (URI != null) {
                         emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
                  }
                  this.startActivity(Intent.createChooser(emailIntent,
                                "Sending email..."));
            } catch (Throwable t) {
                  Toast.makeText(this,
                                "Request failed try again: " + t.toString(),
                                Toast.LENGTH_LONG).show();
            }
     }     
     }

public void openGallery() {
     Intent intent = new Intent();
     intent.setType("image/*");
     intent.setAction(Intent.ACTION_GET_CONTENT);
     intent.putExtra("return-data", true);
     startActivityForResult(
                  Intent.createChooser(intent, "Complete action using"),
                  PICK_FROM_GALLERY);
}
    }
   