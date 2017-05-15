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
public class NewClass extends Activity implements OnClickListener{
ImageButton ins,del,upd,home;
EditText cname,rid,studs;
String classname,rollid,students;
private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newclass);
        createDatabase();
        cname=(EditText)findViewById(R.id.txtcname);
        rid=(EditText)findViewById(R.id.txtrollid);
        studs=(EditText)findViewById(R.id.txtstuds);
        classname=cname.getText().toString().trim();
        rollid=rid.getText().toString().trim();
        students=studs.getText().toString().trim();
        ins=(ImageButton)findViewById(R.id.ibinsert);
        del=(ImageButton)findViewById(R.id.ibdelete);
        upd=(ImageButton)findViewById(R.id.ibupdate);
        home=(ImageButton)findViewById(R.id.ibhome);
        ins.setOnClickListener(this);
        del.setOnClickListener(this);
        upd.setOnClickListener(this);
        home.setOnClickListener(this);
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NEWCLASS(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, classname VARCHAR,rollid VARCHAR,students VARCHAR);");
    }
    protected void insertIntoDB(){   
    	  classname=cname.getText().toString().trim();
          rollid=rid.getText().toString().trim();
          students=studs.getText().toString().trim();
          
        if(cname.equals("") ||rid.equals("") || studs.equals("")){
            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
            return; 
    	}   
        String query = "INSERT INTO NEWCLASS (CLASSNAME,ROLLID,STUDENTS) VALUES('"+classname+"', '"+rollid+"','"+students+"');";
        db.execSQL(query);
    	Toast.makeText(getApplicationContext(), "SAVED..", Toast.LENGTH_LONG).show();
     }
    public void deleteRow()
    {
    	 classname=cname.getText().toString().trim();
         
    db.execSQL("DELETE FROM  NEWCLASS WHERE CLASSNAME='"+classname+"'");
    Toast.makeText(getApplicationContext(), "DELETED..", Toast.LENGTH_LONG).show();
    db.close();
    }
    public void update(){
    	 classname=cname.getText().toString().trim();
         rollid=rid.getText().toString().trim();
         students=studs.getText().toString().trim();

     	db.execSQL("UPDATE NEWCLASS SET ROLLID='"+rollid+"', STUDENTS='"+students+"' WHERE CLASSNAME='"+classname+"'");
        Toast.makeText(getApplicationContext(), "UPDATED..", Toast.LENGTH_LONG).show();
    }
   
    public void onClick(View v) {
        if(v == ins){      	
        	insertIntoDB();
        	}                        	       	
        if(v==del){
        	deleteRow();
        }
        if(v==upd){
        	update();
        }
        if(v==home){
        	Intent intent10=new Intent(NewClass.this,Home.class);
        	startActivity(intent10);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }    
}
