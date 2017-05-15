package com.ramya.smartattendance;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class SendMessage extends Activity implements OnClickListener {
EditText stid,mgs;
TextView txtno;
ImageButton imgbtnsend,ibok,ihome;
private SQLiteDatabase db;
String msg="",sid="",numb="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmgs);
        createDatabase();
        txtno=(TextView)findViewById(R.id.editmobno);
        stid=(EditText)findViewById(R.id.txtstid);
        mgs=(EditText)findViewById(R.id.txtmgs);
        
        imgbtnsend=(ImageButton)findViewById(R.id.ibsendmgs);
        ibok=(ImageButton)findViewById(R.id.ibok);
        ihome=(ImageButton)findViewById(R.id.ihome);
        imgbtnsend.setOnClickListener(this);
        ibok.setOnClickListener(this);
        ihome.setOnClickListener(this);
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NEWSTUDENT(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,studentid VARCHAR,studentname VARCHAR,classname VARCHAR,mobileno VARCHAR,mailid VARCHAR)");
    }
    
  
    public void onClick(View v) {
    	if(v==imgbtnsend){
            msg=mgs.getText().toString().trim();
    		numb=txtno.getText().toString().trim();
    		sendMgs(msg,numb);
    		Toast.makeText(getApplicationContext(), "SENDING.", Toast.LENGTH_LONG).show();
    	}
    	if(v==ibok){
    		sid=stid.getText().toString().trim();
        	Cursor c=db.rawQuery("SELECT * FROM NEWSTUDENT WHERE studentid='"+sid+"' ", null);
        	if(c.getCount()==0)
        	{
        		Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_LONG).show();
        	    return;
        	}
        	StringBuffer buffer=new StringBuffer();
        	while(c.moveToNext())
        	{
        	    buffer.append(c.getString(4)+"\n\n");
        	}
        	
        	txtno.setText(buffer.toString());        	
        	}
    	if(v==ihome){
    		Intent intent12=new Intent(SendMessage.this,Home.class);
        	startActivity(intent12);
    	}
    }
   private void sendMgs(String msg, String numb) {
		// TODO Auto-generated method stub
    	SmsManager s=SmsManager.getDefault();
		s.sendTextMessage(numb, null, msg, null, null);
		Toast.makeText(getApplicationContext(), "MESSAGE SENT", Toast.LENGTH_LONG).show();
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }    
}
