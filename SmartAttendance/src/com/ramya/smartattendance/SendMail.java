package com.ramya.smartattendance;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class SendMail extends Activity implements OnClickListener {
	EditText mbody,studid;
	TextView mal;
	ImageButton isend,iok,ihome,iatta;
	private SQLiteDatabase db;
	String mail,mid,attachmentFile,sid;
	Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmail);
        createDatabase();
        mal=(TextView)findViewById(R.id.txtmail);
        studid=(EditText)findViewById(R.id.txtstudid);
        mbody=(EditText)findViewById(R.id.txtmbody);             
        isend=(ImageButton)findViewById(R.id.ibtsend);
        iok=(ImageButton)findViewById(R.id.ibtnok);
        ihome=(ImageButton)findViewById(R.id.ibthome);
        iatta=(ImageButton)findViewById(R.id.ibtatta);      
        iatta.setOnClickListener(this);
        isend.setOnClickListener(this);
        iok.setOnClickListener(this);
        ihome.setOnClickListener(this);     
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NEWSTUDENT(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,studentid VARCHAR,studentname VARCHAR,classname VARCHAR,mobileno VARCHAR,mailid VARCHAR)");
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
    	if(v==isend){
    		
            mail=mbody.getText().toString().trim();
    		mid=mal.getText().toString().trim();
    		try{
              final Intent emailIntent = new Intent(
                            android.content.Intent.ACTION_SEND);
              emailIntent.setType("plain/text");
              emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                            new String[] { mid });
              if (URI != null) {
                     emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
                     emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mail);
              }
              this.startActivity(Intent.createChooser(emailIntent,
                            "Sending email..."));
    	}
         catch (Throwable t) {
              Toast.makeText(this,
                            "Request failed try again: " + t.toString(),
                            Toast.LENGTH_LONG).show();
        }
    		Toast.makeText(getApplicationContext(), "EMAIL SENT", Toast.LENGTH_LONG).show();
    	}
    	if(v==iok){
            sid=studid.getText().toString().trim();

        	Cursor c=db.rawQuery("SELECT * FROM NEWSTUDENT WHERE studentid='"+sid+"' ", null);
        	if(c.getCount()==0)
        	{
        		Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_LONG).show();
        	    return;
        	}
        	StringBuffer buffer=new StringBuffer();
        	while(c.moveToNext())
        	{
        	    buffer.append(c.getString(5)+"\n\n");
        	}
        	mal.setText(buffer.toString());       	
        	}
    	if(v==ihome){
    		Intent intent13=new Intent(SendMail.this,Home.class);
        	startActivity(intent13);
    	}
    	if(v==iatta){
    		openGallery();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }    
}
