package com.ramya.smartattendance;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
public class Login extends Activity {
ImageButton  log;
EditText uname,pass;
String user,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        uname=(EditText)findViewById(R.id.txtuname);
        pass=(EditText)findViewById(R.id.txtpass);
        
        log=(ImageButton)findViewById(R.id.iblogin);
        log.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View a) {	
				user=uname.getText().toString().trim();
		        pwd=pass.getText().toString().trim();
				if(user.equals("smart")&&pwd.equals("ramya")){
						Toast.makeText(getApplicationContext(), "CONGRATS: LOGIN SUCCESSFULL..", Toast.LENGTH_LONG).show();
						Intent intent1=new Intent(Login.this,Home.class);
						startActivity(intent1);
				}
				else if(user.equals("")&&pwd.equals("")){
					Toast.makeText(getApplicationContext(), "PLEASE ENTER USERNAME AND PASSWORD..", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(getApplicationContext(), "INVALID USERNAME OR PASSWORD..", Toast.LENGTH_LONG).show();
				}
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
