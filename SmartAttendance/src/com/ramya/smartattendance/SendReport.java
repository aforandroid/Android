package com.ramya.smartattendance;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
public class SendReport extends Activity implements OnClickListener {
	EditText repmid,repmail,repsid;
	String mid,mail,sid;
	ImageButton repsend,repdone,rephome;
	private SQLiteDatabase db;
	Uri URI = null;
    int columnIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendreport);
        createDbase();
        repsid=(EditText)findViewById(R.id.repsid);
        repmid=(EditText)findViewById(R.id.repmid);
        repmail=(EditText)findViewById(R.id.repmail);              
        repsend=(ImageButton)findViewById(R.id.repsend);
        repdone=(ImageButton)findViewById(R.id.repdone);
        rephome=(ImageButton)findViewById(R.id.rephome);       
        repsend.setOnClickListener(this);
        rephome.setOnClickListener(this);
        repdone.setOnClickListener(this);       
        Bundle boo = getIntent().getExtras();
        repmail.setText(boo.getCharSequence("REPORT"));
    }
    protected void createDbase(){
        db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NEWSTUDENT(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,studentid VARCHAR,studentname VARCHAR,classname VARCHAR,mobileno VARCHAR,mailid VARCHAR)");
    }

    public void onClick(View v) {
    	if(v==repsend){
    		
            mail=repmail.getText().toString().trim();
    		mid=repmid.getText().toString().trim();
    		try{
    			Uri uri = Uri.parse(mid);
              final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
              emailIntent.setType("plain/text");
              emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { mid });
              emailIntent.putExtra(Intent.EXTRA_SUBJECT,"REPORT");
              if (uri != null) {
                     emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
                     emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mail);
              }
              this.startActivity(Intent.createChooser(emailIntent,"Sending email..."));
    	}
         catch (Throwable t) {
              Toast.makeText(this,
                            "Request failed try again: " + t.toString(),
                            Toast.LENGTH_LONG).show();
        }
    		Toast.makeText(getApplicationContext(), "SENDING..", Toast.LENGTH_LONG).show();
    	}
    	if(v==repdone){
            sid=repsid.getText().toString().trim();

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
        	repmid.setText(buffer.toString());
        	}
    	if(v==rephome){
    		Intent intent13=new Intent(SendReport.this,Home.class);
        	startActivity(intent13);
    	}
        } 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }    
}
