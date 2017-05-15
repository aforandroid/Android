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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class TakeAttendance extends Activity implements OnClickListener {
CheckBox cb ;
ImageButton btnbak;
TextView date;
String roll,stu,day,absent,rollid,name,phone,mgs;
int s;
private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takeattend);
        createDatabase();
        createDB();
        TextView date = (TextView) findViewById(R.id.txtday);
        btnbak=(ImageButton)findViewById(R.id.btnbak);
        btnbak.setOnClickListener(this);
        LinearLayout ll = (LinearLayout)findViewById(R.id.linear);
        Bundle bun = getIntent().getExtras();
        date.setText(bun.getCharSequence("day"));
        roll=(String)bun.getCharSequence("rollid");
        stu=(String) bun.getCharSequence("studs");
        day=date.getText().toString();
                s=Integer.parseInt(stu.trim());
        for(int x = 1; x < s+1; x++) {
             cb = new CheckBox(this);
            cb.setText(roll+"" + x);
            cb.setId(x);
     	   cb.setOnClickListener(getOnClickDoSomething(cb));
            ll.addView(cb);
        }
    }
    View.OnClickListener getOnClickDoSomething(final Button button) {
    	return new View.OnClickListener() {
    	public void onClick(View v) {
    		//Toast.makeText(getApplicationContext(), "Id  " + button.getId(), Toast.LENGTH_LONG).show();
    		//Toast.makeText(getApplicationContext(), "Text  " + button.getText().toString(), Toast.LENGTH_LONG).show();
    	  	absent="ABSENT";
    	  	rollid=button.getText().toString();
    	  	//Toast.makeText(getApplicationContext(), absent+"\n"+rollid+"\n"+day, Toast.LENGTH_LONG).show();
    	  	Cursor c=db.rawQuery("SELECT * FROM NEWSTUDENT WHERE studentid='"+rollid+"' ", null);
        	if(c.getCount()==0)
        	{
        		Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_LONG).show();
        	    return;
        	}
        	StringBuffer buffer=new StringBuffer();
        	StringBuffer buff=new StringBuffer();
        	while(c.moveToNext())
        	{
        	    buffer.append(c.getString(2));
        	    buff.append(c.getString(4));
        	}
        	name=buffer.toString();
        	phone=buff.toString();
        	Toast.makeText(getApplicationContext(),"NAME : "+name+"\n"+"ROLLID : "+phone+"\n"+"DATE : "+day+"\n"+"STATUS : "+absent,Toast.LENGTH_LONG).show();
        	 String query = "INSERT INTO ATTENDANCE (DAY,ROLLID,NAME,ABSENT) VALUES('"+day+"', '"+rollid+"','"+name+"','"+absent+"');";
             db.execSQL(query);
         	Toast.makeText(getApplicationContext(), "SAVED..", Toast.LENGTH_LONG).show();
         	mgs="YOUR DAUGHTER IS ABSENT TODAY ON "+day;
         	sendMgs(mgs,phone);
    		Toast.makeText(getApplicationContext(), "SENDING..", Toast.LENGTH_LONG).show();
    	}    	
    	};    	
    	}
    private void sendMgs(String mgs, String phone) {
		// TODO Auto-generated method stub
    	SmsManager s=SmsManager.getDefault();
		s.sendTextMessage(phone, null, mgs, null, null);
		Toast.makeText(getApplicationContext(), "MESSAGE SENT", Toast.LENGTH_LONG).show();
	}
    protected void createDatabase(){
        db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NEWSTUDENT(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,studentid VARCHAR,studentname VARCHAR,classname VARCHAR,mobileno VARCHAR,mailid VARCHAR)");
    }
    protected void createDB(){
        db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS ATTENDANCE(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, DAY  VARCHAR,ROLLID VARCHAR,NAME VARCHAR,ABSENT VARCHAR);");
    }
    public void onClick(View v) {
    	if(v==btnbak){
    		Intent intent15=new Intent(TakeAttendance.this,MakeAttendance.class);
        	startActivity(intent15); 
    	}    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }    
}


