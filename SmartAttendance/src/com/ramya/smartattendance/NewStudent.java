package com.ramya.smartattendance;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
public class NewStudent extends Activity implements OnClickListener {
	ImageButton sins,sdel,supd,shome;
	EditText sid,sname,smobno,smail,scname;
	String stuid,stuname,clsname,stuno,stumail;
	private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newstudent);
        createDatabase();
        sname=(EditText)findViewById(R.id.txtstuname);
        scname=(EditText)findViewById(R.id.txtclsname);
        smobno=(EditText)findViewById(R.id.txtstumob);
        sid=(EditText)findViewById(R.id.txtstuid);
        smail=(EditText)findViewById(R.id.txtstumail);                
                sins=(ImageButton)findViewById(R.id.imginsert);
        sdel=(ImageButton)findViewById(R.id.imgdelete);
        supd=(ImageButton)findViewById(R.id.imgupdate);
        shome=(ImageButton)findViewById(R.id.imghome);   
        sins.setOnClickListener(this);
        sdel.setOnClickListener(this);
        supd.setOnClickListener(this);
        shome.setOnClickListener(this);
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NEWSTUDENT(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,studentid VARCHAR,studentname VARCHAR,classname VARCHAR,mobileno VARCHAR,mailid VARCHAR)");
    }
    protected void insertIntoDB(){   
    	clsname=scname.getText().toString().trim();
        stuname=sname.getText().toString().trim();
        stuid=sid.getText().toString().trim();
        stumail=smail.getText().toString().trim();
        stuno=smobno.getText().toString().trim();          
        if(stuid.equals("") ||stuname.equals("") || stuid.equals("")|| stuno.equals("")|| stumail.equals("")){
            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
            return; 
    	}        
        String query = "INSERT INTO NEWSTUDENT (STUDENTID,STUDENTNAME,CLASSNAME,MOBILENO,MAILID) VALUES( '"+stuid+"','"+stuname+"','"+clsname+"','"+stuno+"','"+stumail+"')";
        db.execSQL(query);
    	Toast.makeText(getApplicationContext(), "SAVED..", Toast.LENGTH_LONG).show();
     }
    public void deleteRow()
    {
        stuid=sid.getText().toString().trim();         
    db.execSQL("DELETE FROM NEWSTUDENT WHERE STUDENTID='"+stuid+"'");
    Toast.makeText(getApplicationContext(), "DELETED..", Toast.LENGTH_LONG).show();
    db.close();
    }
    public void update(){
    	clsname=scname.getText().toString().trim();
        stuname=sname.getText().toString().trim();
        stuid=sid.getText().toString().trim();
        stumail=smail.getText().toString().trim();
        stuno=smobno.getText().toString().trim();

     	db.execSQL("UPDATE NEWSTUDENT SET STUDENTNAME='"+stuname+"', CLASSNAME='"+clsname+"', MOBILENO='"+stuno+"',MAILID='"+stumail+"' WHERE STUDENTID='"+stuid+"' ");
        Toast.makeText(getApplicationContext(), "UPDATED..", Toast.LENGTH_LONG).show();
    }
   
    public void onClick(View v) {
        if(v == sins){      	
        	insertIntoDB();
        	}                        	        	
        if(v==sdel){
        	deleteRow();
        }
        if(v==supd){
        	update();
        }
        if(v==shome){
        	Intent intent11=new Intent(NewStudent.this,Home.class);
        	startActivity(intent11);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }    
}
