package com.ramya.smartattendance;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class MakeAttendance extends Activity implements OnClickListener {
EditText cls;
ImageButton ithome,itok,itdone;
TextView ttrollid,ttstu,ttdate;
private SQLiteDatabase db;
String stroll,ststu,clsna,dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeattend);
        createDatabase();
        Date d = new Date();
    	CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
    	dat=s.toString();
        		cls=(EditText)findViewById(R.id.edcname);
				ttrollid=(TextView)findViewById(R.id.ttrollid);
		ttstu=(TextView)findViewById(R.id.ttstu);
		ttdate=(TextView)findViewById(R.id.ttdate);
				ithome=(ImageButton)findViewById(R.id.ithome);
		itok=(ImageButton)findViewById(R.id.itok);
		itdone=(ImageButton)findViewById(R.id.itdone);
				ithome.setOnClickListener(this);
		itdone.setOnClickListener(this);
		itok.setOnClickListener(this);
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NEWCLASS(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, classname VARCHAR,rollid VARCHAR,students VARCHAR);");
    }

    public void onClick(View v) {
    	if(v==itdone){
    		Intent intent14 = new Intent(getApplicationContext(), TakeAttendance.class);
            Bundle bun = new Bundle();
            bun.putString("rollid", ttrollid.getText().toString());
            bun.putString("studs", ttstu.getText().toString());
            bun.putString("day", ttdate.getText().toString());
            intent14.putExtras(bun);
            startActivity(intent14); 
    	}
    	if(v==itok){
    		clsna=cls.getText().toString().trim();
        	Cursor c=db.rawQuery("SELECT * FROM NEWCLASS WHERE classname='"+clsna+"' ", null);
        	if(c.getCount()==0)
        	{
        		Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_LONG).show();
        	    return;
        	}
        	StringBuffer stub=new StringBuffer();
        	StringBuffer rollb=new StringBuffer();
        	while(c.moveToNext())
        	{
        		rollb.append(c.getString(2));
        	    stub.append(c.getString(3));
        	}
        	ttrollid.setText(rollb.toString());
        	ttstu.setText(stub.toString());       	
        	ttdate.setText(dat);
        	}
    	if(v==ithome){
    		Intent intent13=new Intent(MakeAttendance.this,Home.class);
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
