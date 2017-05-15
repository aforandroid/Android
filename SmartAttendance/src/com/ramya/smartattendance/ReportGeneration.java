package com.ramya.smartattendance;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class ReportGeneration extends Activity implements OnClickListener {
ImageButton ghome,gok,gen;
TextView gdate;
private SQLiteDatabase db;
DatePicker picker;
String report,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        createDB();
        picker=(DatePicker)findViewById(R.id.genDatePicker);  
		gdate=(TextView)findViewById(R.id.gendate);	
		ghome=(ImageButton)findViewById(R.id.genhome);
		gok=(ImageButton)findViewById(R.id.genok);
		gen=(ImageButton)findViewById(R.id.gen);
		ghome.setOnClickListener(this);
		gok.setOnClickListener(this);
		gen.setOnClickListener(this);
		gdate.setText(getCurrentDate());
    }

     protected void createDB(){
         db=openOrCreateDatabase("CLASSROOM", Context.MODE_PRIVATE, null);
         db.execSQL("CREATE TABLE IF NOT EXISTS ATTENDANCE(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, DAY  VARCHAR,ROLLID VARCHAR,NAME VARCHAR,ABSENT VARCHAR);");
     }
    public void onClick(View v) {
    	if(v==gok){
    		gdate.setText(getCurrentDate());
    	}
    	if(v==gen){
    		day=gdate.getText().toString();
        	Cursor c=db.rawQuery("SELECT * FROM ATTENDANCE WHERE DAY='"+day+"' ", null);
        	if(c.getCount()==0)
        	{
        		Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_LONG).show();
        	    return;
        	}
        	StringBuffer stub=new StringBuffer();
        	while(c.moveToNext())
        	{
        		stub.append("DAY : "+c.getString(1)+"\n"+"ROLLID : "+c.getString(2)+"\n"+"NAME : "+c.getString(3)+"\n"+"STATUS : "+c.getString(4)+"\n");        	    
        	}
        	report=stub.toString();
        	Toast.makeText(getApplicationContext(), report, Toast.LENGTH_LONG).show();
            Intent intent17 = new Intent(getApplicationContext(), SendReport.class);
            Bundle boo = new Bundle();
            boo.putString("REPORT", report);
            intent17.putExtras(boo);
            startActivity(intent17); 
        	}
    	if(v==ghome){
    		Intent intent13=new Intent(ReportGeneration.this,Home.class);
        	startActivity(intent13);
    	}
    }
    public String getCurrentDate(){  
        StringBuilder builder=new StringBuilder();  
        builder.append((picker.getMonth() + 1)+"/"); 
        builder.append(picker.getDayOfMonth()+"/");  
        builder.append(picker.getYear());  
        return builder.toString();
            }  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }    
}
