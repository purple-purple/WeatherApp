package com.example.weatherapp.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.example.weatherapp.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DatabaseManager {
	private final int BUFFER_SIZE=4000000;
	public static final String NAME_DB="china_city.db";
	public static final String NAME_PACK="com.example.weatherapp";
	public static final String  DB_PATH="/data/data"+"/"+NAME_PACK;
	private SQLiteDatabase database;
	private Context context;
	
	public DatabaseManager(Context context) {
	        this.context = context;
	}

	
	public SQLiteDatabase getDatabase(){return database;}
	
	public void setDatabase(SQLiteDatabase database){this.database=database;}
	
	public void openDatabase(){
		this.database=this.openDatabase(DB_PATH+"/"+NAME_DB);
	}
	
	public void closeDatabase(){
		this.database.close();
	}
	
	public SQLiteDatabase openDatabase(String file_db){
		try{
			if(!(new File(file_db).exists())){
			InputStream is=this.context.getResources().openRawResource(R.raw.china_city);
			FileOutputStream fos=new FileOutputStream(file_db);
			byte[] buffer=new byte[BUFFER_SIZE];
			int count=0;
			while((count =is.read(buffer))>0){
				fos.write(buffer,0,count);
			}
			fos.close();
			is.close();
			}
			return SQLiteDatabase.openOrCreateDatabase(file_db, null);
		}catch(Exception e){
			Log.d("error", "IO error");
			e.printStackTrace();
		}
		return null;
	}


}
