package com.ramya.smartattendance;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
public class Home extends Activity {
ImageButton newclass,newstud,takatt,mgs,mail,gen,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        newclass=(ImageButton)findViewById(R.id.ibnewclass);
        newstud=(ImageButton)findViewById(R.id.ibnewstud);
        takatt=(ImageButton)findViewById(R.id.ibtakatt);
        mgs=(ImageButton)findViewById(R.id.ibmgs);
        mail=(ImageButton)findViewById(R.id.ibmail);
        gen=(ImageButton)findViewById(R.id.ibgen);
        logout=(ImageButton)findViewById(R.id.iblogout);
        newclass.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent2=new Intent(Home.this,NewClass.class);
		startActivity(intent2);
	}
	});
        newstud.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent3=new Intent(Home.this,NewStudent.class);
		startActivity(intent3);
	}
});
takatt.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent4=new Intent(Home.this,MakeAttendance.class);
		startActivity(intent4);
	}
});

mgs.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent6=new Intent(Home.this,SendMessage.class);
		startActivity(intent6);
	}	
});
mail.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent7=new Intent(Home.this,SendMail.class);
		startActivity(intent7);
	}
});

gen.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent18=new Intent(Home.this,ReportGeneration.class);
		startActivity(intent18);
	}	
});
logout.setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View v) {
		Intent intenty=new Intent(Home.this,Login.class);
		startActivity(intenty);
		finish();
	}
	
});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }    
}
