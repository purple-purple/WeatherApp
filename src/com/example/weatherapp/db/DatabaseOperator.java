package com.example.weatherapp.db;

import java.util.ArrayList;
import java.util.List;

import com.example.weatherapp.model.City;
import com.example.weatherapp.model.Province;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOperator {
	public static List<Province> loadProvince(SQLiteDatabase db){
		List<Province> list=new ArrayList<>();
		Cursor cursor=db.query("T_Province", null, null, null, null, null, null);
		if(cursor.moveToFirst())
			do{
				Province province=new Province();
				province.ID_Province=cursor.getInt(cursor.getColumnIndex("ProSort"));
				province.Name_province=cursor.getString(cursor.getColumnIndex("ProName"));
				list.add(province);
				
			}while(cursor.moveToNext());
		return list;
	}
	public static List<City> loadCity(SQLiteDatabase db,int ProID){
		List<City> list=new ArrayList<>();
		Cursor cursor=db.query("T_City", null, "ProID=?", new String[]{String.valueOf(ProID)},null,null, null);
		if(cursor.moveToFirst()){
			do{
				City city=new City();
				city.Name_city=cursor.getString(cursor.getColumnIndex("CityName"));
				city.ID_province=ProID;
				city.ID_City=cursor.getInt(cursor.getColumnIndex("CitySort"));
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
	}
}
