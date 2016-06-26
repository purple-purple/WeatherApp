package com.example.weatherapp.db;

import java.util.ArrayList;
import java.util.List;

import com.example.weatherapp.model.City;
import com.example.weatherapp.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataOperator {
	public static final String DB_NAME="weather";
	public static final int VERSION=1;
	private static DataOperator dataOperator;
	private SQLiteDatabase db;
	
	private DataOperator(Context context){
		DatabaseHelper dbHelper=new DatabaseHelper(context,DB_NAME,null,VERSION);
		db=dbHelper.getWritableDatabase();
		
	}
	
	public synchronized static DataOperator getInstance(Context context){
		////synchronized 对象锁 一个线程访问一个对象代码块时，其他试图访问该对象的线程将被阻塞。
		if(dataOperator == null){
			dataOperator=new DataOperator(context);
		}
		return dataOperator;
	}
	
	public void saveProvince(Province province){
		if(province != null){
			ContentValues values=new ContentValues();
			values.put("Name_city", province.getProvinceName());
			values.put("Code_province", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	
	
	public List<Province> loadProvince(){
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("Name_province")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("Code_province")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	public void saveCity(City city){
		if(city!=null){
			ContentValues values=new ContentValues();
			values.put("Name_city", city.getCityName());
			values.put("Code_city", city.getCityCode());
			values.put("Id_province", city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	
	public List<City> loadCity(int provinceId){
		List<City> list=new ArrayList<City>();
		Cursor cursor=db.query("City", null, "Id_province", new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city=new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("Name_city")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("Code_city")));
				city.setProvinceId(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
	}
}
