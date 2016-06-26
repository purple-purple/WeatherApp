package com.example.weatherapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public static final String CREATE_PROVINCE="create table("
			+"id integer primary key autoincrement,"
			+"province_name text"
			+"province_code text";
	public static final String CREATE_CITY="create table("
			+"id integer primary key autoincrement,"
			+"city_name text"
			+"city_code text"
			+"province_id interger";
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion1 ){
		
	}
	
}
