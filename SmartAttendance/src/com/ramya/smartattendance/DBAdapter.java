package com.ramya.smartattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter 
{
		static final String DATABASE_NAME = "classroom.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"NEWCLASS"+
		                             "( " +"ID"+" integer primary key autoincrement,"+ "CLASSNAME  text,MOBILENO text,MAILID text); ";
		static final String DATABASE_CREATE1 = "create table "+"NEWSTUDENT"+
                "( " +"ID"+" integer primary key autoincrement,"+ "STUDENTID  text,STUDENTNAME text,CLASSNAME text,ROLLID text,STUDENTS text); ";
		static final String DATABASE_CREATE2 = "create table "+"ATTENDANCE"+
                "( " +"ID"+" integer primary key autoincrement,"+ "DAY  text,ROLLID text,NAME text,ABSENT text); ";
		// Variable to hold the database instance
		public static  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private Database dbHelper;
		public  DBAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new Database(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  DBAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public static void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String classname,String rollid,String students)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("CLASSNAME", classname);
			newValues.put("ROLLID",rollid);
			newValues.put("STUDENTS", students);
			// Insert the row into your table
			db.insert("NEWCLASS", null, newValues);
		}
		public void insertEntryAtt(String day,String rollid,String name,String absent)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("DAY", day);
			newValues.put("ROLLID",rollid);
			newValues.put("NAME", name);
			newValues.put("ABSENT", absent);

			// Insert the row into your table
			db.insert("ATTENDANCE", null, newValues);
		}
		public int deleteEntry(String classname)
		{
			//String id=String.valueOf(ID);
		    String where="CLASSNAME=?";
		    int numberOFEntriesDeleted= db.delete("NEWCLASS", where, new String[]{classname}) ;
	        return numberOFEntriesDeleted;
		}	
		public void  updateEntry(String classname,String rollid,String students)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("CLASSNAME", classname);
			updatedValues.put("ROLLID",rollid);
			updatedValues.put("STUDENTS", students);
	        String where="CLASSNAME = ?";
		    db.update("NEWCLASS",updatedValues, where, new String[]{classname});			   
		}	
		public void  updateEntryStu(String studentid,String studentname,String classname,String mobileno,String mailid)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("STUDENTID",studentid);
			updatedValues.put("STUDENTNAME",studentname);
			updatedValues.put("CLASSNAME", classname);
			updatedValues.put("MOBILENO",mobileno);
			updatedValues.put("MAILID", mailid);
	        String where="STUDENTID = ?";
		    db.update("NEWSTUDENT",updatedValues, where, new String[]{studentid});			   
		}	
		public void insertEntryStu(String studentid,String studentname,String classname,String mobileno,String mailid)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
	       newValues.put("STUDENTID",studentid);
	       newValues.put("STUDENTNAME", studentname);
			newValues.put("CLASSNAME", classname);
			newValues.put("MOBILENO",mobileno);
			newValues.put("MAILID", mailid);
			// Insert the row into your table
			db.insert("NEWSTUDENT", null, newValues);
		}
		public int deleteEntryStu(String studentid)
		{
			//String id=String.valueOf(ID);
		    String where="STUDENTID=?";
		    int numberOFEntriesDeleted= db.delete("NEWSTUDENT", where, new String[]{studentid}) ;
	        return numberOFEntriesDeleted;
		}	
		public static String getSinlgeEntryStu(String studentid)
		{
			Cursor cursor=db.query("NEWSTUDENT", null, " STUDENTID=?", new String[]{studentid}, null, null, null);
	        if(cursor.getCount()<1) // Name Not Exist
	        { 
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String stuno= cursor.getString(cursor.getColumnIndex("MOBILENO"));
			cursor.close();
			return stuno;				
		}	
		public static String getSinlgeEntry(String classname)
		{
			Cursor cursor=db.query("NEWCLASS", null, " CLASSNAME=?", new String[]{classname}, null, null, null);
	        if(cursor.getCount()<1) // Name Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String rollid= cursor.getString(cursor.getColumnIndex("ROLLID"));
			cursor.close();
			return rollid;				
		}	
		public static String getSinlgeEntryAtt(String day)
		{
			Cursor cursor=db.query("ATTENDANCE", null, " DAY=?", new String[]{day}, null, null, null);
	        if(cursor.getCount()<1) // Name Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String rollid= cursor.getString(cursor.getColumnIndex("ROLLID"));
			cursor.close();
			return rollid;				
		}	
}

