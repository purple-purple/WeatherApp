package com.example.weatherapp.util;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class Weather_Util {
	public static String data_today;
	public static String tmp_max_today;
	public static String tmp_min_today;
	public static String txt_d;
	public static String txt_n;
	
	public static void handle_Weather_Response(Context context,String response){
		try{
			JSONObject jsonObject=new JSONObject(response);
			JSONObject all_information_obj=jsonObject.getJSONObject("HeWeather data service 3.0");
			String all_information_str=all_information_obj.toString();
			JSONArray  jsonArray=new JSONArray(all_information_str);
			JSONObject weather_inf=jsonArray.getJSONObject(2);
			data_today=weather_inf.getString("data");
			tmp_max_today=weather_inf.getJSONObject("tmp").getString("max");
			tmp_min_today=weather_inf.getJSONObject("tmp").getString("min");
			txt_d=weather_inf.getJSONObject("cond").getString("txt_d");
			txt_n=weather_inf.getJSONObject("cond").getString("txd_n");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
